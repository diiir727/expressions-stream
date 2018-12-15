import main.*;
import main.generator.ReflectionClassGenerator;
import main.observer.Observable;
import main.observer.ObservableFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Run {

    private static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        if(checkArgs(args)) {
            run(args);
        } else {
            printHelp();
        }
    }

    private static void run(String[] args) {
        String observableFilePath = args[0];
        int generationPeriod = Integer.parseInt(args[1]);

        File fileWithExpressions = new File(observableFilePath);
        ReflectionClassGenerator gen = new ReflectionClassGenerator(
                "main.generator.Expression",
                "double",
                "double... args"
        );
        Parser parser = new JSONParser(fileWithExpressions);
        Facade f = new Facade(gen, parser, new LogWriter(), generationPeriod);
        Observable observable = new ObservableFile(fileWithExpressions, 10);

        try {
            observable.add(f);
            observable.start();
            f.run();
        } catch (Exception e) {
            logger.error("fatal error: ", e);
        } finally {
            observable.stop();
        }
    }

    private static void printHelp() {
        System.out.println("usage: [expression_file_path, number_generate_duration]");
    }

    private static boolean checkArgs(String[] args){
        try {
            return (args.length == 2) && (Integer.parseInt(args[1]) >= 0) && new File(args[0]).isFile();
        } catch (Exception e) {
            return false;
        }
    }
}
