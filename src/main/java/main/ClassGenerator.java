package main;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class ClassGenerator {

    public Expression generate(String expression) throws Exception {
        try {
            // create an empty source file
            File sourceFile = File.createTempFile("DynamicClass", ".java");
            sourceFile.deleteOnExit();

            // generate the source code, using the source filename as the class name
            String classname = sourceFile.getName().split("\\.")[0];
            String sourceCode = "public class " + classname + " implements main.Expression { public double calc(double... args) {return "+ expression +";}}";

            // write the source code into the source file
            FileWriter writer = new FileWriter(sourceFile);
            writer.write(sourceCode);
            writer.close();

            // compile the source file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            File parentDirectory = sourceFile.getParentFile();
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
            compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            fileManager.close();

            // load the compiled class
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
            return  (Expression) classLoader.loadClass(classname).newInstance();
        } catch (Exception e) {
            throw new Exception("can't create");
        }
    }

}
