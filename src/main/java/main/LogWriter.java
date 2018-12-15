package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LogWriter implements Writer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void write(String function, double res, double... args) {
        logger.info("(" + function + ") " + Arrays.toString(args) + " => " + res);
    }
}
