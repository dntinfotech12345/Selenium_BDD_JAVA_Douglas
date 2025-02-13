package Org.douglas.hooks;

import Org.douglas.util.ConfigReader;
import Org.douglas.testBase.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class Hooks {

    WebDriver driver;
    Properties p;

    @Before
    public void setup() throws IOException, InterruptedException {
        driver = BaseClass.initilizeBrowser();

        p = ConfigReader.getProperties();
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL"));

        Thread.sleep(4000);//check the alternative for this
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }


    @After
    public void tearDown() {

        driver.close();

    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {

        // this is for cucumber junit report
        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

        }

    }

}
