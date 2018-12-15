package main;

import main.generator.Expression;
import main.generator.ReflectionClassGenerator;
import main.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Facade implements Observer {

    private ReflectionClassGenerator gen;
    private Parser parser;
    private AtomicReference<List<Expression>> calcExpressions = new AtomicReference<>();

    public Facade(ReflectionClassGenerator gen, Parser parser) {
        this.gen = gen;
        this.parser = parser;
        handleEvent();
    }

    public void run() {
        int i = 0;
        double a1 = 4;
        double b1 = 2;

//        long start = System.currentTimeMillis();
        while(true) {
            for (Expression exp: calcExpressions.get()){
                try {
                    exp.calc(a1,b1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//                exp.calc(a1,b1);
        }
//        long finish = System.currentTimeMillis();
//        System.out.println(finish - start);
    }

    @Override
    public void handleEvent() {
        try {
            List<Expression> res = new ArrayList<>();
            List<String> expressions = parser.parseExpressions();
            for(String v : expressions) {
                try {
                    res.add(gen.generate(v));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            calcExpressions.set(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
