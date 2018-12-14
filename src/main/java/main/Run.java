package main;

public class Run {

    public static void main(String[] args) {
        int i = 0;
        double a1 = 10;
        double b1 = 2;

        try {
            ClassGenerator gen = new ClassGenerator();
            Expression exp = gen.generate("args[0] - args[1]");
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

    private static double calc(int a1, int b1) {
        return a1 + b1;
    }



}
