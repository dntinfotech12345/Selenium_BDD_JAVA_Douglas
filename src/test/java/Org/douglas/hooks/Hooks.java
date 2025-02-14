package Org.douglas.hooks;

<<<<<<< HEAD
import Org.douglas.testBase.BaseClass;
import Org.douglas.util.ConfigReader;
import io.cucumber.java.After;
=======
import Org.douglas.util.ConfigReader;
import Org.douglas.testBase.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
>>>>>>> c5445263ed58008cab96613dbeac0fe03bb9d2e1
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

<<<<<<< HEAD
=======
import java.io.IOException;
import java.time.Duration;
>>>>>>> c5445263ed58008cab96613dbeac0fe03bb9d2e1
import java.util.Properties;


public class Hooks {

    WebDriver driver;
<<<<<<< HEAD
    Properties properties;

    @Before
    public void setup() throws Exception {
        driver = BaseClass.initilizeBrowser();
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
=======
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

>>>>>>> c5445263ed58008cab96613dbeac0fe03bb9d2e1
    }

}
