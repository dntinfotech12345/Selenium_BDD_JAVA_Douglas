package Org.douglas.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/Features/parfumPage.feature"},
        glue = {"Org.douglas.stepDefinitions", "Org.douglas.hooks"},
		plugin = {

				    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				     "rerun:target/rerun.txt",

		         },
        dryRun = false,
        monochrome = true,
        publish = true
)
public class TestRunner {

}


