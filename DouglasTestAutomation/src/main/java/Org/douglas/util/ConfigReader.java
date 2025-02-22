package Org.douglas.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;

    public static Properties getProperties() throws IOException {
        FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        properties = new Properties();
        properties.load(file);
        return properties;
    }
}