package Org.douglas.util;

import Org.douglas.helper.LoggerHelper;
import Org.douglas.pageObjects.BasePage;
import Org.douglas.pageObjects.ParfumPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtil extends BasePage {

    private static final org.apache.logging.log4j.Logger logger = LoggerHelper.getLogger(ParfumPage.class);
    private WebDriverWait wait;

    public ActionUtil(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Element clicked successfully: {}", element);
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable for click: {}", element, e);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be clickable: {}", element, e);
        } catch (StaleElementReferenceException e) {
            logger.error("Element became stale, attempting to click again: {}", element, e);
            try {
                element = driver.findElement(((By) element));
                element.click();
                logger.info("Element clicked successfully after stale reference: {}", element);
            } catch (Exception retryException) {
                logger.error("Failed to click the element after retry: {}", element, retryException);
            }
        } catch (Exception e) {
            logger.error("Unexpected exception while clicking element: {}", element, e);
        }
    }
}
