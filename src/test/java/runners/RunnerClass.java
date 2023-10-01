package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "src/test/resources/features/",
        //glue keyword we use to provide the path of the package where all the step def is available
        glue = "steps",
        //dryRyn=true it stops actual execution, it quickly scans all the steps and will provide the missing
        //step definition
        dryRun = false,
        tags="@TESTTEST",
        //monochrome tag means sometimes the console output for cucumber test is having some
        //irrelevant information, when you set it to true, it removes all that irrelevant information
        //from the console and will give you a simple output
        monochrome = true,
        //it used to print all the steps in the console
        //hml plugin is generating the report
        //all types of reports will be found under target folder
        plugin={"pretty","html:target/cucumber.html","json:target/cucumber.json",
        "rerun:target/failed.txt"}
)
public class RunnerClass {

}
