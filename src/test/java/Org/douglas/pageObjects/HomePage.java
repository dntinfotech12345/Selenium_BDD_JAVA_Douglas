package Org.douglas.pageObjects;

import Org.douglas.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {
    WaitHelper waitHelper = new WaitHelper(driver, 15);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Elements
    public By acceptAllButton = By.cssSelector("div > div.sc-aXZVg.jmAsbz > #focus-lock-id > #focus-lock-id > div.sc-dAlyuH.gRvRiD > #uc-center-container > div.sc-eBMEME.dRvQzh > div.sc-dCFHLb.eNfaCm > div.sc-jsJBEP.iXSECa > div.sc-eeDRCY.fnyekb > button.sc-dcJsrY.eIFzaz");
    WebElement perfumeLink = driver.findElement(By.xpath("//li[@data-uid='FragrancesNavNode_DE']"));

    // Method to accept cookies on the home page
    public void acceptCookies() {
        try {
            WebElement acceptButton = waitHelper.waitForElement((WebElement) acceptAllButton);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0]. click();", acceptButton);
            //acceptButton.click();
            //  new Actions(driver).moveToElement(acceptButton).click().perform();

        } catch (Exception e) {
            System.err.println("Failed to accept cookies via the UI. Clearing cookies programmatically.");

            System.out.println("Cleared cookies programmatically after failure.");
        }
    }

    // Method to click on a specific tab on the homepage
    private By headingTabName(String tabName) {
        return By.xpath("//a[@type='nav-heading' and text()='" + tabName + "']");
    }

    public void clickHomePageTab(String tabName) {
        driver.findElement(headingTabName(tabName)).click();


    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

}
