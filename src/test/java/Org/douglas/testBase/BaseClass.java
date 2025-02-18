package Org.douglas.testBase;

import Org.douglas.enums.BrowserEnum;
import Org.douglas.enums.OSEnum;
import Org.douglas.pageObjects.BasePage;
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

public class BaseClass extends BasePage {

    public static WebDriver driver;
    public static Properties properties;

    public BaseClass(WebDriver driver) {
        super(driver);
    }

    public static WebDriver initializeBrowser() throws IOException {
        properties = getProperties();
        String executionEnv = properties.getProperty("execution_env");
        String browser = properties.getProperty("browser").toLowerCase();
        String os = properties.getProperty("os").toLowerCase();

        // Use Enum to get Browser and OS
        BrowserEnum browserEnum = BrowserEnum.fromString(browser);
        OSEnum osEnum = OSEnum.fromString(os);

        if (executionEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (osEnum) {
                case WINDOWS:
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case MAC:
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case LINUX:
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    return null;
            }

            switch (browserEnum) {
                case CHROME:
                    capabilities.setBrowserName("chrome");
                    break;
                case EDGE:
                    capabilities.setBrowserName("edge");
                    break;
                case FIREFOX:
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    return null;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (executionEnv.equalsIgnoreCase("local")) {
            switch (browserEnum) {
                case CHROME:
                    driver = new ChromeDriver();
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
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
