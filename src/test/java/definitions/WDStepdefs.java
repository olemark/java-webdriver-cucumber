package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class WDStepdefs {
    @When("^I select any position$")
    public void iSelectAnyPosition() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='wd-FacetedSearchResultList-PaginationText-facetSearchResultList.newFacetSearch.Report_Entry']")));

        //randomly selection of element
        List<WebElement> jobs = getDriver().findElements(By.xpath("//div[contains(@id,'promptOption-gwt-uid-')]"));
        int i = new Random().nextInt(jobs.size()); //generate random index of array's element
        jobs.get(i).click();

    }

    @And("^I go to apply with LinkedIn$")
    public void iGoToApplyWithLinkedIn() throws Exception {
        getDriver().findElement(By.xpath("(//div[contains(@id,\"Import_from_LinkedIn']\")]/button)[1]")).click();

    }

    @Then("^I verify LinkedIn login page opens$")
    public void iVerifyLinkedInLoginPageOpens() {

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header__logo']//*[@preserveAspectRatio='xMinYMin meet']")));
        assertThat(getDriver().getTitle()).containsIgnoringCase("Sign In to LinkedIn");


    }
}
