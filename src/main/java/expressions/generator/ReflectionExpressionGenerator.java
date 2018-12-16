package expressions.generator;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

/**
 * Generate expression using reflection
 */
public class ReflectionExpressionGenerator implements ExpressionGenerator{

    private String interfaces;
    private String returnedType;
    private String params;

    public ReflectionExpressionGenerator(String interfaces, String returnedType, String params) {
        this.interfaces = interfaces;
        this.returnedType = returnedType;
        this.params = params;
    }


    public Expression generate(String expression) throws Exception {

        File sourceFile = File.createTempFile("DynamicClass", ".java");
        sourceFile.deleteOnExit();

        String classname = sourceFile.getName().split("\\.")[0];
        String sourceCode = "public class " + classname + " implements " + this.interfaces +
                " { public String getFunction(){return \"" + expression +"\";} "+
                " public " + this.returnedType +" calc(" + this.params + ") {return "+ expression +";}}";

        FileWriter writer = new FileWriter(sourceFile);
        writer.write(sourceCode);
        writer.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        Iterable<File> dir = Collections.singletonList(parentDirectory);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, dir);
        Iterable<File> files = Collections.singletonList(sourceFile);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(files);
        compiler.getTask(null, fileManager, null, null, null, compilationUnits)
                .call();
        fileManager.close();

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
        return  (Expression) classLoader.loadClass(classname).newInstance();

    }

}
