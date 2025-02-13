package Org.douglas.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/Features/perfumPage.feature"},

        glue = {"Org.douglas.stepDefinitions", "Org.douglas.hooks"},
					plugin= {

								"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
								"rerun:target/rerun.txt",

							},

        dryRun = false,    // checks mapping between scenario steps and step definition methods
        monochrome = true,    // to avoid junk characters in output
        publish = true   // to publish report in cucumber server
)
public class TestRunner {

}


