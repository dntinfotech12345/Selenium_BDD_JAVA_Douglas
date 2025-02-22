package Org.douglas.testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = {"src/test/resources/features/parfumPage.feature"},
        glue = {"Org.douglas.stepDefinitions", "Org.douglas.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false, monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests {

}


