package main;

import main.generator.Expression;
import main.generator.ReflectionClassGenerator;
import main.observer.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Facade implements Observer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ReflectionClassGenerator gen;
    private Parser parser;
    private AtomicReference<List<Expression>> calcExpressions = new AtomicReference<>();
    private Writer resultWriter;

    public Facade(ReflectionClassGenerator gen, Parser parser, Writer resultWriter) {
        this.gen = gen;
        this.parser = parser;
        this.resultWriter = resultWriter;
        handleEvent();
    }

    public void run() {
        double a1 = 4;
        double b1 = 2;

        while(true) {
            for (Expression exp: calcExpressions.get()){
                try {
                    this.resultWriter.write(exp.calc(a1,b1), a1, b1);
                } catch (Exception e) {
                    logger.warn("Expression calc error: ", e);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warn("Thread sleep error: ", e);
            }

        }
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
                    logger.warn("Generate expression error: ", e);
                }
            }
            calcExpressions.set(res);
        } catch (Exception e) {
            logger.warn("Expression parse error: ", e);
        }
    }
}
