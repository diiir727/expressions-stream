package expressions;

import expressions.generator.ReflectionExpressionGenerator;
import expressions.observer.Observable;
import expressions.observer.ObservableFile;
import expressions.util.JSONParser;
import expressions.util.LogWriter;
import expressions.util.Parser;
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
        ReflectionExpressionGenerator gen = new ReflectionExpressionGenerator(
                "expressions.generator.Expression",
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
        System.out.println("Not valid params.");
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
