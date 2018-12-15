package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LogWriter implements Writer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void write(double res, double... args) {
        logger.info(Arrays.toString(args) + " => " + res);
    }
}
