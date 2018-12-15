package main;

public class NumberGenerator {

    private int generateArraySize;

    public NumberGenerator(int generateArraySize) {
        this.generateArraySize = generateArraySize;
    }

    public double[] generate(){
        double[] res = new double[generateArraySize];
        for (int i = 0; i < generateArraySize; i++) {
            res[i] = Math.random() * 10;
        }
        return res;
    }
}
