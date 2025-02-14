package Org.douglas.pageObjects;

import Org.douglas.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {
    WaitHelper waitHelper = new WaitHelper(driver, 15);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public By shadowRoot = By.id("usercentrics-root");

    public By acceptAllButton = By.cssSelector(".sc-dcJsrY.eIFzaz");

    public void acceptCookies() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to accept cookies.");

        }
        WebElement shadowHostElement = driver.findElement(shadowRoot);
        SearchContext shadowRoot = shadowHostElement.getShadowRoot();
        WebElement elementInsideShadow = shadowRoot.findElement(acceptAllButton);
        elementInsideShadow.click();

    }

    private By headingTabName(String tabName) {
        return By.xpath("//a[@type='nav-heading' and text()='" + tabName + "']");
    }

    public void clickHomePageTab(String tabName) {
        driver.findElement(headingTabName(tabName)).click();
    }



}
