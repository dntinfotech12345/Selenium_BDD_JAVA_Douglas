package Org.douglas.testBase;

import Org.douglas.enums.BrowserEnum;
import Org.douglas.enums.OSEnum;
import Org.douglas.pageObjects.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(BaseClass.class);
    public static WebDriver driver;
    private static Properties properties;

    public BaseClass(WebDriver driver) {
        super(driver);
    }

    public static WebDriver initializeBrowser() throws IOException {
        try {

            properties = getProperties();
            String executionEnv = properties.getProperty("execution_env", "local").toLowerCase();
            String browser = properties.getProperty("browser", "chrome").toLowerCase();
            String os = properties.getProperty("os", "windows").toLowerCase();


            BrowserEnum browserEnum = BrowserEnum.fromString(browser);
            OSEnum osEnum = OSEnum.fromString(os);


            if ("remote".equalsIgnoreCase(executionEnv)) {
                driver = initializeRemoteDriver(browserEnum, osEnum);
            } else if ("local".equalsIgnoreCase(executionEnv)) {
                driver = initializeLocalDriver(browserEnum);
            } else {
                logger.error("Unknown execution environment: {}", executionEnv);
                throw new IllegalArgumentException("Unknown execution environment: " + executionEnv);
            }


            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            logger.info("Browser initialized successfully: " + driver);

            return driver;
        } catch (Exception e) {
            logger.error("Error during browser initialization: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize browser", e);
        }
    }


    private static WebDriver initializeRemoteDriver(BrowserEnum browserEnum, OSEnum osEnum) throws IOException {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(getPlatformForOS(osEnum));

            capabilities.setBrowserName(browserEnum.name().toLowerCase());
            logger.info("Setting up RemoteWebDriver with capabilities: {}", capabilities);

            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (Exception e) {
            logger.error("Error initializing remote driver: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize remote driver", e);
        }
    }


    private static WebDriver initializeLocalDriver(BrowserEnum browserEnum) {
        try {
            WebDriver localDriver;
            switch (browserEnum) {
                case CHROME:
                    localDriver = new ChromeDriver();
                    break;
                case EDGE:
                    localDriver = new EdgeDriver();
                    break;
                case FIREFOX:
                    localDriver = new FirefoxDriver();
                    break;
                default:
                    localDriver = new ChromeDriver(); // Default to Chrome if browser is unknown
            }
            logger.info("Setting up LocalWebDriver: {}", localDriver);
            return localDriver;
        } catch (Exception e) {
            logger.error("Error initializing local driver: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize local driver", e);
        }
    }


    private static Platform getPlatformForOS(OSEnum osEnum) {
        switch (osEnum) {
            case WINDOWS:
                return Platform.WINDOWS;
            case MAC:
                return Platform.MAC;
            case LINUX:
                return Platform.LINUX;
            default:
                logger.error("Unsupported OS: " + osEnum);
                throw new IllegalArgumentException("Unsupported OS: " + osEnum);
        }
    }
}
