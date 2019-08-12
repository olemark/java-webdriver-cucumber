package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class ConverterStepdefs {
    @When("^I click on \"([^\"]*)\"$")
    public void iClickOn(String tab) throws Throwable {
        By xpathLocator = By.xpath(String.format("//*[contains(@href,'%s')]", tab));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
        WebElement converter = getDriver().findElement(xpathLocator);
        converter.click();
    }

    @And("^I set \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iSetTo(String tabFrom, String tabTo) throws Throwable {
        WebElement selectTemperature1 = getDriver().findElement(By.xpath("//select[@id='calFrom']"));
        Select selectFrom = new Select(selectTemperature1);
        selectFrom.selectByVisibleText(tabFrom);

        WebElement selectTemperature2 = getDriver().findElement(By.xpath("//select[@id='calTo']"));
        Select selectTo = new Select(selectTemperature2);
        selectTo.selectByVisibleText(tabTo);

    }

    @Then("^I enter into From field \"([^\"]*)\" and verify \"([^\"]*)\" result$")
    public void iEnterIntoFromFieldAndVerifyResult(String from, String to) throws Throwable {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(from);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='toVal']")));
        String value = getDriver().findElement(By.xpath("//input[@name='toVal']")).getAttribute("value");
        assertThat(value).contains(to);


    }


}
