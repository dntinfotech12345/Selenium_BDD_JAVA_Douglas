package Org.douglas.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        try {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            logger.error("Error initializing BasePage: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize BasePage", e);
        }
    }
}
