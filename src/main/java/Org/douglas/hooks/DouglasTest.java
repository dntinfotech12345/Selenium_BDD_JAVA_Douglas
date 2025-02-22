package Org.douglas.hooks;

import Org.douglas.testBase.BaseClass;
import Org.douglas.util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DouglasTest {

    WebDriver driver;
    Properties properties;


    @Before
    public void setup() {
        try {
            driver = BaseClass.initializeBrowser();
            properties = ConfigReader.getProperties();
            driver.manage().window().maximize();
            driver.get(properties.getProperty("webURL"));
        } catch (Exception e) {
            System.err.println("Error during setup: " + e.getMessage());
            throw new RuntimeException("Error during WebDriver setup", e);
        }
    }


    private void addScreenshot(Scenario scenario) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        } catch (Exception e) {
            System.err.println("Error capturing screenshot for scenario: " + scenario.getName());
            e.printStackTrace();
        }
    }


    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                addScreenshot(scenario);
            }
        } catch (Exception e) {
            System.err.println("Error during screenshot capture for scenario: " + scenario.getName());
            e.printStackTrace();
        } finally {

            if (driver != null) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    System.err.println("Error during WebDriver quit: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}