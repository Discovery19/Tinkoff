package edu.hw2.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Methods {
    private Methods() {

    }
    private static final Logger LOGGER = LogManager.getLogger();

    public static void methodOne() {
        LOGGER.info("methodOne work");
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
    }
}
