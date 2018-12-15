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
    private int generationPeriod;

    public Facade(ReflectionClassGenerator gen, Parser parser, Writer resultWriter, int generationPeriod) {
        this.gen = gen;
        this.parser = parser;
        this.resultWriter = resultWriter;
        this.generationPeriod = generationPeriod;
        calcExpressions.set(new ArrayList<>());
    }

    public void run() {
        NumberGenerator generator = new NumberGenerator(2);
        while(true) {
            double[] ar = generator.generate();
            for (Expression exp: calcExpressions.get())
                tryCalc(ar, exp);

            trySleep();
        }
    }

    private void tryCalc(double[] ar, Expression exp){
        try {
            this.resultWriter.write(exp.getFunction(), exp.calc(ar), ar);
        } catch (Exception e) {
            logger.warn("Expression calc error: ", e);
        }
    }

    private void trySleep() {
        if(generationPeriod >= 1){
            try {
                Thread.sleep(generationPeriod);
            } catch (InterruptedException e) {
                logger.warn("Thread sleep error: ", e);
            }
        }
    }

    @Override
    public void handleEvent() {
        try {
            updateExpressions();
        } catch (Exception e) {
            logger.warn("JSON parse error: ", e);
        }
    }

    private void updateExpressions() throws Exception {
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
    }
}
