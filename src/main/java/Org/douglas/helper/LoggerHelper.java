package Org.douglas.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {

    public static Logger getLogger(Class<?> cls) {
        try {
            return LogManager.getLogger(cls);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception to the console for troubleshooting
            throw new RuntimeException("Logger initialization failed for class: " + cls.getName(), e);
        }
    }
}
