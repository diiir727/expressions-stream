package main;

import java.util.Arrays;

public class LogWriter implements Writer {

    @Override
    public void write(double res, double... args) {
        System.out.println(Arrays.toString(args) + " => " + res);
    }
}
