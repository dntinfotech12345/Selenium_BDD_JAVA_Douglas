package Org.douglas.stepDefinitions;

import Org.douglas.helper.LoggerHelper;
import Org.douglas.pageObjects.HomePage;
import Org.douglas.pageObjects.ParfumPage;
import Org.douglas.testBase.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class perfumeStep {

    WebDriver driver = BaseClass.getDriver();
    HomePage hp;
    ParfumPage pp;


    @Given("User navigates to the application")
    public void user_navigates_to_the_application() {

        LoggerHelper.getLogger(perfumeStep.class).info("Goto my account-->Click on Login.. ");
        hp = new HomePage(BaseClass.getDriver());
        pp = new ParfumPage(BaseClass.getDriver());
        hp.acceptCookies();


    }


    @When("User click on {string} tab")
    public void user_click_on_tab(String tabName) {
        LoggerHelper.getLogger(perfumeStep.class).info("Entering email and password.. ");

        hp.clickHomePageTab(tabName);

    }

    @Then("Verify user on the parfum page")
    public void verifyUserOnParfumPage() {

        String title = hp.getUrl();
        System.out.println("URL is :" + title);
        Assert.assertEquals("https://www.douglas.de/de/c/parfum/01", title);

    }

    @When("I select the {string} dropdown")
    public void iSelectTheDropdown(String dropdownOption) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        pp.selectParfumPageDropdown(dropdownOption);  // Call method to select dropdown
    }


    @Then("I select the {string} filter option from the dropdown")
    public void iSelectTheFilterOption(String filterOption) {
        pp.selectDropdownOption(filterOption);  // Call method to select a specific filter option
    }

    @Then("Verify the {string} filter is applied")
    public void verifyTheFilterIsApplied(String filterText) {
        pp.getTheFilterTextAndVerify(filterText);  // Verify the filter is applied on the page
        pp.verifyTheFilterTagAcrossPages(filterText);  // Verify if the filter is applied on all pages
    }
}
