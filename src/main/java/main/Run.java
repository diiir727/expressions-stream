package main;

import main.generator.ReflectionClassGenerator;
import main.generator.Expression;
import main.observer.Observable;
import main.observer.ObservableFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Run {
///home/den/expressions
    public static void main(String[] args) {

        File fileWithExpressions = new File("/home/den/expressions.json");
        ReflectionClassGenerator gen = new ReflectionClassGenerator("main.generator.Expression", "double", "double... args");
        Parser parser = new JSONParser(fileWithExpressions);
        Facade f = new Facade(gen, parser);
        Observable observable = new ObservableFile(fileWithExpressions, 10);

        try {
            observable.start();
            observable.add(f);
            f.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            observable.stop();
        }

    }
}
