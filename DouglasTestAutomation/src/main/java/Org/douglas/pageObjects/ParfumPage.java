package Org.douglas.pageObjects;

import Org.douglas.helper.LoggerHelper;
import Org.douglas.util.ActionUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ParfumPage extends BasePage {

    private static final org.apache.logging.log4j.Logger logger = LoggerHelper.getLogger(ParfumPage.class);
    ActionUtil actionUtil;
    private WebDriverWait wait;
    @FindBy(xpath = "//div[@class='facet__title' and text()= '{dropdownOption}']")
    private WebElement parfumPageDropdown;

    @FindBy(xpath = "//button[@class='selected-facets__value']")
    private WebElement appliedFilters;

    @FindBy(xpath = "//a[@data-testid='pagination-arrow-right']")
    private WebElement nextPageArrow;

    @FindBy(xpath = "//div[contains(@data-testid,'product-eyecatcher') and text()='{filterTag}']")
    private WebElement filterTag;

    @FindBy(xpath = "//div[@class='facet-option__label']//div[text()='{filterOption}']")
    private WebElement highlightFilterOption;

    @FindBy(xpath = "//div[@data-testid='pagination-title-dropdown']")
    private WebElement pageInfoLocator;

    @FindBy(xpath = "//*[@id='main-content']/div/div/div[1]/div/div[2]/div[3]/div[1]/div[1]/div[8]")
    private WebElement aktionenTitle;

    @FindBy(xpath = "//div[@class='survey-modal__header']//button")
    private WebElement randonPopupOnPerfumePage;

    @FindBy(xpath = "//*[@id='main-content']/div/div/div/h1")
    private WebElement markenPageTitle;

    @FindBy(xpath = "//*[@id='main-content']/div/div/div[1]/div/div[2]/div[3]/div[1]/div[2]/button[2]")
    private WebElement selectedFilterValue;

    public ParfumPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actionUtil = new ActionUtil(driver);
    }

    public String getParfumPageUrl() {
        return driver.getCurrentUrl();
    }


    public void selectParfumPageDropdown(String dropdownOption) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Deine Meinung ist gefragt']")));
            randonPopupOnPerfumePage.click();


            logger.info("Dropdown filter option '" + dropdownOption + "' selected.");
        } catch (Exception e) {
            logger.error("Failed to select dropdown option '" + dropdownOption + "': " + e.getMessage(), e);
        }
        WebElement dropdown = driver.findElement(By.xpath("//div[@class='facet__title' and text()='" + dropdownOption + "']"));
        actionUtil.safeClick(dropdown);
    }

    public void selectDropdownOption(String filterOption) {
        WebElement filterOpt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='facet-option__label']//div[text()='" + filterOption + "']")));
        actionUtil.safeClick(filterOpt);  // Use safeClick here for handling exceptions
    }

    public void getTheFilterTextAndVerify(String actualFilterText) {
        wait.until(ExpectedConditions.visibilityOf(appliedFilters));

        List<WebElement> filters = driver.findElements(By.xpath("//button[@class='selected-facets__value']"));

        for (WebElement filter : filters) {
            String filterText = filter.getText();
            if (filterText.contains(actualFilterText)) {
                return;
            }
        }
    }

    public void scrollToFacetTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(aktionenTitle));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", aktionenTitle);
    }

    public String getTitle() {
        return markenPageTitle.getText();
    }

    public String getFilteredValue() {
        return selectedFilterValue.getText();
    }
}