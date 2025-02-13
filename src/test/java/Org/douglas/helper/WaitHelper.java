package Org.douglas.helper;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    Logger logger = LoggerHelper.getLogger(WaitHelper.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public long timeOutInSeconds;

    public WaitHelper(WebDriver driver, long timeOutInSeconds) {
        this.driver = driver;
        this.timeOutInSeconds = timeOutInSeconds;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
    }

    public WebElement waitForElement(WebElement element) {
        logger.info("waiting for element visibility Of..{}", element);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
            logger.info("{}is visible..", element);
        } catch (Exception e) {
            logger.info("{}is not visible..", element);
        }
        return element;
    }
}
