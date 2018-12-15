package main.generator;

public interface Expression {

    double calc(double... args);
    String getFunction();

}
