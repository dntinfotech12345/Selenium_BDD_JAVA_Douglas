package Org.douglas.stepDefinitions;

import Org.douglas.helper.LoggerHelper;
import Org.douglas.pageObjects.HomePage;
import Org.douglas.pageObjects.ParfumPage;
import Org.douglas.testBase.BaseClass;
import Org.douglas.util.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

public class ParfumStep {

    private ParfumPage parfumPage;
    private HomePage homePage;
    private Properties properties;

    @Given("I navigate to the website")
    public void launchWebURL() throws IOException {
        homePage = new HomePage(BaseClass.driver);
        parfumPage = new ParfumPage(BaseClass.driver);
        properties = ConfigReader.getProperties();
        LoggerHelper.getLogger(ParfumStep.class).info("Website URL: " + properties.getProperty("webURL"));
    }

    @Then("I accept the cookie consent")
    public void acceptCookieConsent() {
        LoggerHelper.getLogger(ParfumStep.class).info("Accepting cookie consent.");
        homePage.acceptCookies();
    }

    @When("I click on {string} tab")
    public void clickOnTab(String tabName) {
        LoggerHelper.getLogger(ParfumStep.class).info("Clicking on tab: " + tabName);
        homePage.clickHomePageTab(tabName);
    }

    @Then("I verify that I was landed on the parfum page")
    public void verifyUserOnParfumPage() {
        LoggerHelper.getLogger(ParfumStep.class).info("Verifying that user is on the Parfum page.");
        String URL = parfumPage.getParfumPageUrl();
        String expectedURL = properties.getProperty("parfumPageURL");
        Assert.assertEquals(URL, expectedURL);
    }

    @When("I select the {string} dropdown")
    public void iSelectTheDropdown(String dropdownOption) {
        LoggerHelper.getLogger(ParfumStep.class).info("Selecting a dropdown filter option: {}", dropdownOption);
        parfumPage.scrollToFacetTitle();
        parfumPage.selectParfumPageDropdown(dropdownOption);
    }

    @Then("I select the {string} filter option from the dropdown")
    public void iSelectTheFilterOption(String filterOption) {
        LoggerHelper.getLogger(ParfumStep.class).info("Selecting filter option: {}", filterOption);
        parfumPage.scrollToFacetTitle();
        parfumPage.selectDropdownOption(filterOption);
    }

    @Then("I verify that I was landed on parfum page")
    public void verifyTitle() {
        LoggerHelper.getLogger(ParfumStep.class).info("Verifying the title of the Parfum page.");
        String title = parfumPage.getTitle();
        String expectedTitle = properties.getProperty("parfumPageTitle");
        Assert.assertEquals(title, expectedTitle);
    }

    @Then("I verify the filter option from dropdown")
    public void verifyDropDownFilter() {
        LoggerHelper.getLogger(ParfumStep.class).info("Verifying selected filter option from the dropdown.");
        parfumPage.getTheFilterTextAndVerify(parfumPage.getFilteredValue());
    }
}