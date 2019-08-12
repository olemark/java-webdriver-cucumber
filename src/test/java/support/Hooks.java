package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static support.TestContext.getPosition;
import static support.TestContext.getRecruiter;

public class Hooks {
//
//    @Before(value = "@create_position", order = 1)  //this can be executed before scenario with tag @create_position
//    public void createPosition() throws Exception{
//        new RestWrapper().login(getRecruiter());
//        new RestWrapper().createPosition(getPosition());
//    }

    @Before(value = "@create_position", order = 1)  // 3of3 We can use this instead @before above (see WrestWrapper for 1of3 and 2of3). This also can be executed before scenario with tag @create_position
    public void createPosition() throws Exception{
        new RestWrapper().login(getRecruiter()).createPosition(getPosition());
    }

    @Before(order = 0)
    public void scenarioStart() {   //created for web driver
        TestContext.initialize();
        TestContext.getDriver().manage().deleteAllCookies();
        TestContext.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //This is an implicit wait - wait in general
    }

    @After(order = 0)
    public void scenarioEnd(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) TestContext.getDriver();
            byte[] screenshot = screenshotTaker.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        TestContext.close();
    }
}
