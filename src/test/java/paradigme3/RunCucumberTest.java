package paradigme3;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/paradigme3/age-requirement.feature"},
        plugin = {  "pretty",
                    "json:target/sysReport/json/report.json"},
                    tags = {"@ListOfString"})
public class RunCucumberTest {
}
