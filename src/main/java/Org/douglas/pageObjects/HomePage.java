package Org.douglas.pageObjects;

import Org.douglas.helper.LoggerHelper;
import Org.douglas.util.ActionUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public By shadowRoot = By.id("usercentrics-root");
    public By acceptAllButton = By.cssSelector(".sc-dcJsrY.eIFzaz");
    Logger logger = LoggerHelper.getLogger(HomePage.class);
    ActionUtil actionUtil;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actionUtil = new ActionUtil(driver);
    }

    public void acceptCookies() {
        try {
            Thread.sleep(5000);
            WebElement shadowHostElement = driver.findElement(shadowRoot);
            SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

            WebElement acceptButton = shadowRootContext.findElement(acceptAllButton);
            actionUtil.safeClick(acceptButton);
            logger.info("Cookies accepted successfully.");
        } catch (NoSuchElementException e) {
            logger.error("Failed to locate shadow root or accept button: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("An error occurred while accepting cookies: {}", e.getMessage(), e);
        }
    }

    public By headingTabName(String tabName) {
        return By.xpath("//a[@type='nav-heading' and text()='" + tabName + "']");
    }

    public void clickHomePageTab(String tabName) {
        try {
            WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(headingTabName(tabName)));
            actionUtil.safeClick(tab);
            logger.info("Tab '" + tabName + "' clicked successfully.");
        } catch (Exception e) {
            logger.error("Failed to click on tab '{}': {}", tabName, e.getMessage(), e);
        }
    }
}