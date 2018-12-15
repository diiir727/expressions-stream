package main;

import main.generator.ReflectionClassGenerator;
import main.generator.Expression;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Run {
///home/den/expressions
    public static void main(String[] args) {
        Run r = new Run();
        r.run();
    }

    public void run(){
        int i = 0;
        double a1 = 4;
        double b1 = 2;

        try {
            List<Expression> calcExpressions = new ArrayList<>();
            ReflectionClassGenerator gen = new ReflectionClassGenerator("main.generator.Expression", "double", "double... args");
            Parser parser = new JSONParser(new File("/home/den/expressions"));
            List<String> expressions = parser.parseExpressions();
            for(String v : expressions) {
                calcExpressions.add(gen.generate(v));
            }


            long start = System.currentTimeMillis();
            while(i++ < 1) {
                for (Expression exp: calcExpressions)
                    System.out.println(exp.calc(a1,b1));
//                    exp.calc(a1,b1);

            }
            long finish = System.currentTimeMillis();
            System.out.println(finish - start);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
