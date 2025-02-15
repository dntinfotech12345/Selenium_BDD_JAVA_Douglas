package Org.douglas.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {
    static Logger logger;
    public static Logger getLogger(Class cls) {
        logger = LogManager.getLogger(cls); //Log4j
        return logger;
    }

    public static void main(String[] args) {
        Logger log = LoggerHelper.getLogger(LoggerHelper.class);

    }
}
