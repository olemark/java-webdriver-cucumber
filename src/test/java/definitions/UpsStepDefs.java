package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;


import java.text.NumberFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;


public class UpsStepDefs {
    @And("^I select \"([^\"]*)\" and \"([^\"]*)\" on global page$")
    public void iSelectAndOnGlobalPage(String region, String country) throws Throwable {
        WebElement topHeader = getDriver().findElement(By.xpath("//div[@class='page-title_cell']"));
        new Actions(getDriver()).moveToElement(topHeader).perform();


        // Check that region with contry expanded or collapsed
        By countrySelector = By.xpath(String.format("//a[contains(text(),'%s')]", country));
        if (getDriver().findElements(countrySelector).size() == 0) {
            getDriver().findElement(By.xpath("//a[text()='" + region + "']")).click();
        }
        // Another option to check
//        if(!getDriver().findElement(countrySelector).isDisplayed()) {
//            getDriver().findElement(By.xpath("//a[text()='" + region + "']")).click();
//        }
        getDriver().findElement(countrySelector).click();

    }


    @And("^I open Shipping menu$")
    public void iOpenShippingMenu() {
        WebElement ship = getDriver().findElement(By.xpath("//*[@id='ups-menuLinks1']")); // get the element
        ship.click(); // click the element

    }


    @And("^I go to Create a Shipment$")
    public void iGoToCreateAShipment() {
        getDriver().findElement(By.xpath("//*[@id='ups-navItems']//a[contains(@href,'ship?loc=en_US')]")).click();
    }


    @When("^I fill out origin shipment fields: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillOutOriginShipmentFields(String country, String name, String address, String zip, String email, String phone) throws Throwable {

        //Initializing sender - Hash Map was created for sender to use getSender method(to use data from sender.yml file)
        HashMap<String, String> sender = TestContext.getSender();

        //Using data from sender.yml file:
        getDriver().findElement(By.xpath(String.format("//select[@id='origincountry']//option[text()='%s']", country))).click();
        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys(sender.get("name"));
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys(sender.get("address"));
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys(sender.get("email"));
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys(sender.get("phone"));
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys(sender.get("zip"));

        //Using data from Cucumber steps:
//        getDriver().findElement(By.xpath(String.format("//select[@id='origincountry']//option[text()='%s']", country))).click();
//        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys(name);
//        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys(address);
//        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys(email);
//        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys(phone);
//        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys(zip);

        // explicit wait for element visibility

        By cityResult = By.xpath("//input[@id='origincity']");
        By stateResult = By.xpath("//select[@id='originstate']/option[text()='California']");
        WebElement citySearchResultElement = getDriver().findElement(cityResult);

        //we create getWait() object in TestContext.java and can use it in our java code instead of "new WebDriverWait(getDriver(), 5)" every time for explicit wait
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(citySearchResultElement, "value"));
        getWait().until(ExpectedConditions.elementToBeSelected(stateResult));

//        String city = "mountain view".toUpperCase();
//        getWait().until(ExpectedConditions.textToBePresentInElementValue(citySearchResultElement, city));


    }

    @And("^I submit the shipment form$")
    public void iSubmitTheShipmentForm() throws Exception {

        //JavaScript clicking
        String oldUrl = getDriver().getCurrentUrl();

        String nextButtonXpath;
        if (oldUrl.contains("payment")) {
            nextButtonXpath = "//button[@id='nbsBackForwardNavigationReviewPrimaryButton']";
        } else {
            nextButtonXpath = "//button[@id='nbsBackForwardNavigationContinueButton']";
        }
        WebElement nextButton = getDriver().findElement(By.xpath(nextButtonXpath));
        //JavaScript clicking
        TestContext.clickWithJS(nextButton);
        getWait().until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));

    }


    @Then("^I verify origin shipment fields submitted: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iVerifyOriginShipmentFieldsSubmitted(String country_index, String name, String address, String zip, String email, String phone) throws Throwable {

        // this wait checking that text is not empty, then we proceed. Using lambda function
        WebElement searchResultElement = getDriver().findElement(By.xpath("//*[contains(@class,'ups-group_condensed')]"));
        System.out.println(String.format("Search result: |%s|", searchResultElement.getText()));
        getWait().until((ExpectedCondition<Boolean>) driver -> searchResultElement.getText().length() > 0);
        System.out.println();
        System.out.println(String.format("Search result after wait: |%s|", searchResultElement.getText()));

        System.out.println();

        //Assertions all items in the block - using data from cucumber
        String textToVerify = searchResultElement.getText(); //get text from container for assertions
//        assertThat(textToVerify).containsIgnoringCase(country_index);
//        assertThat(textToVerify).containsIgnoringCase(name);
//        assertThat(textToVerify).containsIgnoringCase(address);
//        assertThat(textToVerify).containsIgnoringCase(zip);
//        assertThat(textToVerify).containsIgnoringCase(email);
//        assertThat(textToVerify).containsIgnoringCase(phone);

        //Initializing sender - Hash Map was created for sender to use getSender method(to use data from sender.yml file)
        HashMap<String, String> sender = TestContext.getSender();
        //Assertions all items in the block - using data from sender.yml file
        assertThat(textToVerify).containsIgnoringCase(country_index);
        assertThat(textToVerify).containsIgnoringCase(sender.get("name"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("address"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("zip"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("email"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("phone"));
    }

    @And("^I cancel the shipment form$")
    public void iCancelTheShipmentForm() throws Exception {

        clickWithJS(getDriver().findElement(By.xpath("//*[@id='nbsBackForwardNavigationCancelShipmentButton']")));
        getDriver().findElement(By.xpath("//*[@id='nbsCancelShipmentWarningYes']")).click();


    }


    @Then("^I verify shipment form is reset$")
    public void iVerifyShipmentFormIsReset() {
        By cancelBlock = By.xpath("//*[@class='ups-wrap']//*[contains(text(),'shipping from')]");
        String cancelResult = getDriver().findElement(cancelBlock).getText();
        assertThat(cancelResult).containsIgnoringCase("Where are you shipping from?");

        System.out.println();

        String value = getDriver().findElement(By.xpath("//input[@id='originname']")).getAttribute("value");
        assertThat(value.isEmpty()).isTrue();
        System.out.println("Shipping has been canceled!");
    }


    @When("^I fill out destination shipment fields: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillOutDestinationShipmentFields(String name, String address, String zip) throws Throwable {

        //Initializing sender - Hash Map was created for sender to use getSender method(to use data from receiver.yml file)
        HashMap<String, String> receiver = TestContext.getReceiver();
        //Using data from receiver.yml file:
        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys(receiver.get("name"));
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys(receiver.get("address"));
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys(receiver.get("zip"));


        //Using data from Cucumber steps:
//        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys(name);
//        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys(address);
//        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys(zip);

        // explicit wait for element visibility
        By cityResult = By.xpath("//input[@id='destinationcity']");
        WebElement cityDestinationElement = getDriver().findElement(cityResult);
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(cityDestinationElement, "value"));


    }


    @Then("^I verify total charges appear$")
    public static void iVerifyTotalChargesAppear() {

        By total = By.xpath("//span[@id='total-charges-spinner']");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(total));
        WebElement totalCharges = getDriver().findElement(total);
        assertThat(totalCharges.getText()).isNotEmpty();
        System.out.println("Appeared charges verified: " + totalCharges.getText());
        System.out.println();
    }


    @And("^I set packaging type$")
    public void iSetPackagingType() {
//        By packagingType = By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']/option[contains(text(),'Small')]");
//        getDriver().findElement(packagingType).click();

        //Choose packaging type using Select element - another way
        WebElement selectElement = getDriver().findElement(By.xpath("//select[@id='nbsPackagePackagingTypeDropdown0']"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("UPS Express Box - Small");


        By packageWeight = By.xpath("//input[@id='nbsPackagePackageWeightField0']");
        getDriver().findElement(packageWeight).sendKeys("1");

    }

    @Then("^I verify total charges changed$")
    public void iVerifyTotalChargesChanged() {
        By totalChargesSelector = By.xpath("//span[@id='total-charges-spinner']");
        WebElement totalChargesElement = getDriver().findElement(totalChargesSelector);
        String oldCharges = totalChargesElement.getText();

        System.out.println();
        System.out.println(String.format("Total charges before wait: %s", oldCharges));

//        //Lambda function approach to wait until text change in WebElement
//        getWait().until((ExpectedCondition<Boolean>) driver -> !totalChargesElement.getText().equals(oldCharges));


        //Another approach to wait until text change in WebElement
        getWait().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(totalChargesSelector, oldCharges)));

        //Assertions approach right away - does not make sense here
//        assertThat(totalChargesElement.getText()).doesNotContain(oldCharges);
        System.out.println();
        System.out.println(String.format("Changed charges verified after wait: %s", totalChargesElement.getText()));
        System.out.println();


    }


    @And("^I select cheapest delivery option$")
    public void iSelectCheapestDeliveryOption() throws Exception {
        System.out.println();

        List<WebElement> deliveryOptions = getDriver().findElements(By.xpath("//*[contains(@id,'nbsServiceTileTotalCharge')]"));
        ArrayList<Double> priceList = new ArrayList<>(); // declare new ArrayList with Double type

//        //Snippet how to extract currency - another approached (is commented) - Teacher version
//        Locale locale = new Locale("en", "US");
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
//        double cheapestPrice = Double.MAX_VALUE; //declare the max value



        for (WebElement price : deliveryOptions) {
            if (price.isDisplayed()) {
                Double doubleItem = Double.parseDouble(price.getText().replaceAll("[^\\d.]", "")); // convert each string to double excluding all characters and symbols

//                // logic how extract Currency from each price and find cheapest price - Teacher version
//                double currentPrice = formatter.parse(price.getText()).doubleValue();
//                if (currentPrice < cheapestPrice) {
//                    cheapestPrice = currentPrice;
//                }
//

                priceList.add(doubleItem);
            }


        }

        System.out.println();
        System.out.println("Delivery options found on the page: " + priceList);

        double cheapestOption = Collections.min(priceList);  // find minimum from Array List and assign to variable cheapestOption
        System.out.println("Min delivery option is: $" + cheapestOption);
        WebElement cheapestPriceElement = getDriver().findElement(By.xpath(String.format("//*[contains(@id,'nbsServiceTileTotalCharge')]/*[contains(text(),'%s')]/../../..", cheapestOption)));
        cheapestPriceElement.click();

//        //Teacher version of clicking on cheapest price
//        WebElement cheapestPriceElementTeacher = getDriver().findElement(By.xpath(String.format("//*[contains(@id,'nbsServiceTileTotalCharge')]/*[contains(text(),'%s')]/../../..", cheapestPrice)));
//        clickWithJS(cheapestPriceElementTeacher);

    }


    @And("^I set Saturday Delivery type$")
    public void iSetSaturdayDeliveryType() {
        WebElement saturday = getDriver().findElement(By.xpath("//label[@for='nbsSaturdayDeliveryOptionBaseOptionSwitch']"));
        saturday.click();




    }

    @And("^I select Paypal payment type$")
    public void iSelectPaypalPaymentType() {

        WebElement otherWays = getDriver().findElement(By.xpath("//label[@for='other-ways-to-pay-tile']"));
        clickWithJS(otherWays);

        By xpathLocator = By.xpath("//img[contains(@src,'paypal')]");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
        WebElement payPal = getDriver().findElement(xpathLocator);
        clickWithJS(payPal);

    }
    @Then("^I review all recorded details on the review page$")
    public void iReviewAllRecordedDetailsOnTheReviewPage() throws Exception {
        HashMap<String, String> sender = getSender();
        String origin = getDriver().findElement(By.xpath("//origin-return[@id='nbsSpaOriginReturn']")).getText();
        assertThat(origin).containsIgnoringCase(sender.get("name"));
        assertThat(origin).containsIgnoringCase(sender.get("address"));
        assertThat(origin).containsIgnoringCase(sender.get("zip"));
        assertThat(origin).containsIgnoringCase(sender.get("city"));
        assertThat(origin).containsIgnoringCase(sender.get("state"));

        String destination = getDriver().findElement(By.xpath("//destination[@id='nbsSpaDestination']")).getText();
        HashMap<String, String> receiver = getReceiver();
        assertThat(destination).containsIgnoringCase(receiver.get("name"));
        assertThat(destination).containsIgnoringCase(receiver.get("address"));
        assertThat(destination).containsIgnoringCase(receiver.get("zip"));
        assertThat(destination).containsIgnoringCase(receiver.get("city"));
        assertThat(destination).containsIgnoringCase(receiver.get("state"));
    }
}



