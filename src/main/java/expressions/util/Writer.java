package expressions.util;


public interface Writer {

    /**
     * write expressions results
     * @param function calculated function
     * @param res expression result
     * @param args input expression args
     */
    void write(String function, double res, double... args);

}
