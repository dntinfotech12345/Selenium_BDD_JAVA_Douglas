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


public class Hooks {

    WebDriver driver;
    Properties properties;

    @Before
    public void setup() throws Exception {
        driver = BaseClass.initializeBrowser();
        properties = ConfigReader.getProperties();
        driver.manage().window().maximize();
        driver.get(properties.getProperty("webURL"));
    }


    public void addScreenshot(Scenario scenario) {


        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        addScreenshot(scenario);
        driver.close();
    }

}
