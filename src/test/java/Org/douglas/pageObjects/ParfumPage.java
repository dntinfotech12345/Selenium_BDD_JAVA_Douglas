package Org.douglas.pageObjects;

<<<<<<< HEAD
import Org.douglas.helper.WaitHelper;
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
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public ParfumPage(WebDriver driver) {
        super(driver);
    }

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

    @FindBy (xpath = "//*[@id='main-content']/div/div/div[1]/div/div[2]/div[3]/div[1]/div[2]/button[2]")
    private WebElement selectedFilterValue;


    public String getParfumPageUrl() {
        return driver.getCurrentUrl();
    }

    public void selectParfumPageDropdown(String dropdownOption) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Deine Meinung ist gefragt']")));
            randonPopupOnPerfumePage.click();
        } catch (Exception e) {
            System.out.println("Pop-Up handled successfully");        }
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='facet__title' and text()='" + dropdownOption + "']")));
        dropdown.click();
        System.out.println("Dropdown filter option '" + dropdownOption + "' selected");
    }

    public void selectDropdownOption(String filterOption) {
        WebElement filterOpt = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='facet-option__label']//div[text()='" + filterOption + "']")
        ));
        filterOpt.click();
       }

    public void getTheFilterTextAndVerify(String actualFilterText) {

        wait.until(ExpectedConditions.visibilityOf(appliedFilters));

        List<WebElement> filters = driver.findElements(By.xpath("//button[@class='selected-facets__value']"));

        for (WebElement filter : filters) {
            String filterText = filter.getText();
            if (filterText.contains(actualFilterText)) {
                System.out.println("The filter '" + actualFilterText + "' is applied.");
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

    public String getFilteredValue(){
        return selectedFilterValue.getText();
    }
=======
import java.util.List;

import Org.douglas.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParfumPage extends BasePage {
	WebDriverWait wait;
	WaitHelper waitHelper = new WaitHelper(driver,15);

	public ParfumPage(WebDriver driver) {
		super(driver);
	}

	  private By parfumPageDropdown(String dropdownOption) {
	        return By.xpath("//div[@class='facet__title' and text()= '" + dropdownOption + "']");
	    }

	    private By highlightFilterOption(String filterOption) {
	        return By.xpath("//div[@class='facet-option__label']//div[text()='" + filterOption + "']");
	    }
	    
	    
	    private By searchBar = By.xpath("//input[@data-testid='typeAhead-input']");
	    private By appliedFilters = By.xpath("//button[@class='selected-facets__value']");
	    private By pageInfoLocator = By.xpath("//div[@data-testid='pagination-title-dropdown']");
	    private By nextPageArrow = By.xpath("//a[@data-testid='pagination-arrow-right']");
	    private By filterTag(String filterTag) {
	        return By.xpath("//div[contains(@data-testid,'product-eyecatcher') and text()='" + filterTag + "']");
	    }

	 // Method to get the Parfum page title
	    public String getParfumPageTitle() {
	        System.out.println("Waiting for the parfum page to fully load");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));  // Ensure page is loaded

	        System.out.println("Fetching the parfum page title");
	        String title = driver.getTitle();

	        System.out.println("Parfum page title is: " + title);
	        return title;
	    }

	    // Method to select a dropdown filter option on the Parfum page
	    public void selectParfumPageDropdown(String filterOption) {
	        System.out.println("Hovering over the search bar to ensure dropdown visibility");
	        WebElement searchElement = waitHelper.waitForElement((WebElement) searchBar);
	        searchElement.click(); // Clicking search bar to make dropdown visible

	        System.out.println("Selecting dropdown filter option: " + filterOption);
	        WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(parfumPageDropdown(filterOption)));
	        dropdownOption.click();

	        System.out.println("Dropdown filter option '" + filterOption + "' selected");
	    }

	    // Method to select a specific filter option from the dropdown
	    public void selectDropdownOption(String filterOption) {
	        System.out.println("Selecting filter option: " + filterOption + " from the dropdown");
	        WebElement filterOpt = wait.until(ExpectedConditions.elementToBeClickable(highlightFilterOption(filterOption)));

	        // Click the filter option
	        filterOpt.click();
	        System.out.println("Filter option '" + filterOption + "' selected");
	    }

	    // Method to get the applied filter text and verify it
	    public void getTheFilterTextAndVerify(String actualFilterText) {
	        // Wait for applied filters to be visible
	        wait.until(ExpectedConditions.visibilityOfElementLocated(appliedFilters));

	        // Get list of applied filter elements
	        List<WebElement> filters = driver.findElements(appliedFilters);

	        // Extract the text from all filters
	        for (WebElement filter : filters) {
	            String filterText = filter.getText();
	            // Assertion can be added here based on your framework (e.g., using JUnit or TestNG assertions)
	            if (filterText.contains(actualFilterText)) {
	                System.out.println("The filter '" + actualFilterText + "' is applied.");
	                return;
	            }
	        }
	        System.out.println("The filter '" + actualFilterText + "' was not found in the applied filters.");
	    }

	    // Method to verify the filter tag across all pages (pagination)
	    public void verifyTheFilterTagAcrossPages(String actualFilterText) {
	        int currentPage = 1;
	        int totalPages = 1;

	        // Get total pages from the page info
	        WebElement pageInfoText = wait.until(ExpectedConditions.visibilityOfElementLocated(pageInfoLocator));
	        String pageInfo = pageInfoText.getText();

	        if (pageInfo.matches("Seite (\\d+) von (\\d+)")) {
	            String[] pageNumbers = pageInfo.split(" von ");
	            currentPage = Integer.parseInt(pageNumbers[0].replaceAll("[^0-9]", ""));
	            totalPages = Integer.parseInt(pageNumbers[1].replaceAll("[^0-9]", ""));
	        }

	        System.out.println("Total pages to validate: " + totalPages);

	        // Loop through all pages until the last one
	        while (currentPage <= totalPages) {
	            System.out.println("Validating filter tag on page " + currentPage + " of " + totalPages);

	            // Wait for filter tag to become visible on the current page
	            wait.until(ExpectedConditions.visibilityOfElementLocated(filterTag(actualFilterText)));

	            // Extract filter text(s) from the current page
	            List<WebElement> filters = driver.findElements(filterTag(actualFilterText));

	            // Verify the filter text
	            for (WebElement filter : filters) {
	                String filterText = filter.getText();
	                if (filterText.contains(actualFilterText)) {
	                    System.out.println("Filter verification successful on page " + currentPage);
	                    break;
	                }
	            }

	            // If we are not on the last page, click the "Next" button to go to the next page
	            if (currentPage < totalPages) {
	                System.out.println("Navigating to page " + (currentPage + 1));
	                WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(nextPageArrow));
	                nextPageButton.click();

	                // Wait for page load to complete
	                wait.until(ExpectedConditions.visibilityOfElementLocated(pageInfoLocator));
	                currentPage++;
	            } else {
	                System.out.println("Reached the last page, stopping pagination.");
	                break;
	            }
	        }
	    }
>>>>>>> c5445263ed58008cab96613dbeac0fe03bb9d2e1
}
