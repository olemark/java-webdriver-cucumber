package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;


public class UspsStepDefs {
    @When("^I go to Lookup ZIP page by address$")
    public void iGoToLookupZIPPageByAddress() throws Exception {
        getDriver().findElement(By.xpath("//*[@id='mail-ship-width']")).click();

        WebElement zip = getDriver().findElement(By.xpath("//img[@id='icon-zip']")); //The same way to click element
        zip.click();

        getDriver().findElement(By.xpath("//*[@class='column-one-text']/../a")).click();

    }


    @When("^I mouse over and go to Lookup ZIP page by address$")
    public void iMouseOverAndGoToLookupZIPPageByAddress() throws Exception {
        //Mouse over element

        Actions actions = new Actions(getDriver());
        WebElement mailShip = getDriver().findElement(By.xpath("//*[@id='mail-ship-width']"));
        actions.moveToElement(mailShip).perform();

        getDriver().findElement(By.xpath("//li[@class='tool-zip']//a")).click();
        getDriver().findElement(By.xpath("//*[@class='column-one-text']/../a")).click();

    }

    @And("^I fill out \"([^\"]*)\" street, \"([^\"]*)\" city, \"([^\"]*)\" state$")
    public void iFillOutStreetCityState(String street, String city, String state) throws Throwable {
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);

        getDriver().findElement(By.xpath(String.format("//select[@id='tState']/option[@value='%s']", state))).click(); // Injection the variable in Java
//        getDriver().findElement(By.xpath("//select[@id='tState']/option[@value='" + state + "']")).click(); // The same way

        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();

    }

    @Then("^I validate \"([^\"]*)\" zip code exists in the result$")
    public void iValidateZipCodeExistsInTheResult(String zip) throws Throwable {
        // explicit wait by time
//        Thread.sleep(1000);

        // explicit wait for element visibility
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        By firstResultItem = By.xpath("//div[@id='zipByAddressDiv']//li[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstResultItem));

        // this wait checking that text is not empty, then we proceed. Using lambda function
//        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement zipSearchResultElement = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
//        System.out.println(String.format("Search result: |%s|", zipSearchResultElement.getText()));
//        wait.until((ExpectedCondition<Boolean>) driver -> zipSearchResultElement.getText().length() > 0);
//        System.out.println();
//        System.out.println(String.format("Search result after wait: |%s|", zipSearchResultElement.getText()));

        System.out.println();

//Assertions of ZIP code in the block
        String zipSearchResult = zipSearchResultElement.getText(); //get text from container
        assertThat(zipSearchResult).containsIgnoringCase(zip); // assertion  'zip' text in 'zipSearchResult' container

//Assertions of ZIP code in each item
        List<WebElement> items = getDriver().findElements(By.xpath("//div[@id='zipByAddressDiv']//li"));
        for (WebElement item : items) {
            assertThat(item.getText()).contains(zip);  // assertions 'zip' text in every single 'li' string using 'for loop'
        }


    }



}
