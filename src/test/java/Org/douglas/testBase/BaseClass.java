package Org.douglas.testBase;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static Org.douglas.util.ConfigReader.getProperties;

public class BaseClass {

    static WebDriver driver;
    static Properties properties;


    public static WebDriver initilizeBrowser() throws IOException {
        properties = getProperties();
        String executionEnv = properties.getProperty("execution_env");
        String browser = properties.getProperty("browser").toLowerCase();
        String os = properties.getProperty("os").toLowerCase();

        if (executionEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os
            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    System.out.println("No matching OS");
                    return null;
            }

            //browser
            switch (browser) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No matching browser");
                    return null;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (executionEnv.equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("No matching browser");
                    driver = new ChromeDriver();;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        return driver;

    }

    public static WebDriver getDriver() {
        return driver;
    }


}
