package main;

import main.generator.ReflectionClassGenerator;
import main.generator.Expression;

public class Run {

    public static void main(String[] args) {
        int i = 0;
        double a1 = 9;
        double b1 = 2;

        try {
            ReflectionClassGenerator gen = new ReflectionClassGenerator("main.generator.Expression", "double", "double... args");
            Expression exp = gen.generate("args[0] * args[1]");
            long start = System.currentTimeMillis();

            while(i++ < 3) {
                System.out.println(exp.calc(a1,b1));
            }
            long finish = System.currentTimeMillis();
            System.out.println(finish - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
