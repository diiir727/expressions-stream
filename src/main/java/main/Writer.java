package main;


public interface Writer {

    /**
     * write expressions results
     * @param res expression result
     * @param args input expression args
     */
    void write(double res, double... args);

}
