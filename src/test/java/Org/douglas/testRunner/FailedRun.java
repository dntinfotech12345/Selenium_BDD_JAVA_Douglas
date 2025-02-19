package Org.douglas.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = {"@target/rerun.txt"},
        glue = {"Org.douglas.stepDefinitions", "Org.douglas.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true

)
public class FailedRun extends AbstractTestNGCucumberTests {

}


