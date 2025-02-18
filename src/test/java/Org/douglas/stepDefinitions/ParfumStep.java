package Org.douglas.stepDefinitions;

import Org.douglas.helper.LoggerHelper;
import Org.douglas.pageObjects.HomePage;
import Org.douglas.pageObjects.ParfumPage;
import Org.douglas.testBase.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ParfumStep {

    ParfumPage parfumPage;
    HomePage homePage;

    @Given("I navigate to the website")
    public void launchWebURL() {
        homePage = new HomePage(BaseClass.getDriver());
        parfumPage = new ParfumPage(BaseClass.getDriver());
    }

    @Then("I accept the cookie consent")
    public void acceptCookieConsent() {
        homePage.acceptCookies();
    }

    @When("I click on {string} tab")
    public void clickOnTab(String tabName) {
        LoggerHelper.getLogger(ParfumStep.class).info("Clicking on tab: " + tabName);
        homePage.clickHomePageTab(tabName);
    }

    @Then("I verify that I was landed on the parfum page")
    public void verifyUserOnParfumPage() {
        LoggerHelper.getLogger(ParfumStep.class).info("Get the title of the current page");
        String URL = parfumPage.getParfumPageUrl();
        Assert.assertEquals("https://www.douglas.de/de/c/parfum/01", URL);
    }

    @When("I select the {string} dropdown")
    public void iSelectTheDropdown(String dropdownOption) {
        LoggerHelper.getLogger(ParfumStep.class).info("Selecting a dropdown filter on the Parfum page");
        parfumPage.scrollToFacetTitle();
        parfumPage.selectParfumPageDropdown(dropdownOption);
    }

    @Then("I select the {string} filter option from the dropdown")
    public void iSelectTheFilterOption(String filterOption) throws InterruptedException {
        LoggerHelper.getLogger(ParfumStep.class).info("Selecting a filter option");
        parfumPage.scrollToFacetTitle();
        parfumPage.selectDropdownOption(filterOption);
    }

    @Then("I verify that I was landed on parfum page")
    public void verifyTitle() {
        LoggerHelper.getLogger(ParfumStep.class).info("Verify the page title");
        String title = parfumPage.getTitle();
        Assert.assertEquals("Parfüm & Düfte", title);
    }

    @Then("I verify the filter option from dropdown")
    public void verifyDropDownFilter() {
        parfumPage.getTheFilterTextAndVerify(parfumPage.getFilteredValue());
    }


}
