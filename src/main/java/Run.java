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

        String observableFilePath = "/home/den/expressions.json";
        int generationPeriod = 500;

        File fileWithExpressions = new File(observableFilePath);
        ReflectionClassGenerator gen = new ReflectionClassGenerator("main.generator.Expression", "double", "double... args");
        Parser parser = new JSONParser(fileWithExpressions);
//        Facade f = new Facade(gen, parser, new FileWriter(new File(logFilePath)));
        Facade f = new Facade(gen, parser, new LogWriter());
        Observable observable = new ObservableFile(fileWithExpressions, 10);

        try {
            observable.start();
            observable.add(f);
            f.run();
        } catch (Exception e) {
            logger.error("Unhandled error: ", e);
        } finally {
            observable.stop();
        }

    }
}
