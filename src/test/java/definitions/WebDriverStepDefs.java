package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class WebDriverStepDefs {
    @Given("^I go to \"([^\"]*)\" page$")
    public void iGoToPage(String page) throws Throwable {
        if (page.equalsIgnoreCase("sample")) {
            getDriver().get("https://skryabin.com/webdriver/html/sample.html");
        } else if (page.equalsIgnoreCase("google")) {
            getDriver().get("https://www.google.com/");
        } else if (page.equalsIgnoreCase("engprod")) {
            getDriver().get("https://www.engprod-charter.net");
        } else if (page.equalsIgnoreCase("usps")) {
            getDriver().get("https://usps.com");
        } else if (page.equalsIgnoreCase("ups_global")) {
            getDriver().get("https://www.ups.com");
        } else if (page.equalsIgnoreCase("ups")) {
            getDriver().get("https://www.ups.com/us/en/Home.page");
        }
        else if (page.equalsIgnoreCase("converter")) {
            getDriver().get("https://www.unitconverters.net/");
        }
        else if (page.equalsIgnoreCase("workday")) {
            getDriver().get("https://workday.wd5.myworkdayjobs.com/Workday");
        }
        else {
            throw new Exception("Page not recognized: " + page);
        }
    }

    @And("^I print page details$")
    public void iPrintPageDetailes() {
        System.out.println(getDriver().getCurrentUrl());  //get and print current URL
        System.out.println(getDriver().getTitle());       //get and print Title
        System.out.println(getDriver().getWindowHandle()); //get and print Window Handles
        System.out.println(getDriver().getPageSource());   //get and print Page Source

    }

    @And("^I login in alert pop-up window$")
    public void iLoginInAlertPoppupWindow() throws Exception {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        Alert alert = getDriver().switchTo().alert();
        alert.dismiss();


////Selenium-WebDriver Java Code for entering Username & Password as below:
//        getDriver().findElement(By.id("userID")).sendKeys("userName");
//        getDriver().findElement(By.id("password")).sendKeys("myPassword");

//        getDriver().switchTo().defaultContent();


//        baseurl = ”http://” + "hello" + “:” + "world" + “@” + url;
//
//        getDriver().get(baseurl + “/”);
//        getDriver().findElement(By.xpath(".//a[@href=contains(text(),'yearly-calendar.xls')]")).click();


//        Robot robot = new Robot();
//        Thread.sleep(2000);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        Thread.sleep(2000);
//        robot.keyPress(KeyEvent.VK_TAB);
//        Thread.sleep(2000);
//        robot.keyPress(KeyEvent.VK_TAB);
//        Thread.sleep(2000);
//        robot.keyPress(KeyEvent.VK_TAB);
//        Thread.sleep(2000);
//        robot.keyPress(KeyEvent.VK_ENTER);


    }

    @And("^I go back and forward and refresh$")
    public void iGoBackAndForwardAndRefresh() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();

    }

    @And("^I change resolution to \"([^\"]*)\"$")
    public void iChangeResolutionTo(String layout) throws Throwable {
        if (layout.equalsIgnoreCase("phone")) {
            Dimension phone = new Dimension(400, 800);
            getDriver().manage().window().setSize(phone);
        } else if (layout.equalsIgnoreCase("desktop")) {
            Dimension desktop = new Dimension(1600, 768);
            getDriver().manage().window().setSize(desktop);
        } else {
            throw new Exception("Unknown layout: " + layout);
        }


    }

    @And("^I fill out all required fields$")
    public void iFillOutAllRequiredFields() throws Exception {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("omarkov");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("test@gmail.com");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("password1");
        getDriver().findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("password1");
        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();  //example

        getDriver().findElement(By.xpath("//input[@name='name']")).click();  //example
        getDriver().findElement(By.xpath("//input[@id='firstName']")).sendKeys("Oleg");
        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Markov");
        getDriver().findElement(By.xpath("//div[@aria-describedby='nameDialog']//span[contains(text(),'Save')]")).click();

    }

    @And("^I submit the page$")
    public void iSubmitThePage() throws Exception {

        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();

        Thread.sleep(3000);
    }

    @Then("^I verify required fields$")
    public void iVerifyRequiredFields() {


//        Assertions if element is presented
        WebElement resultLegend = getDriver().findElement(By.xpath("//legend[@class='applicationResult']"));
        assertThat(resultLegend.isDisplayed()).isTrue();


    }

    @Then("^I verify next required fields$")
    public void iVerifyNextRequiredFields() {
        //        Assertions that element equal some value Ignoring Case Sensitivity
        String actualPrivacyPolicy = getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText();
        assertThat(actualPrivacyPolicy).isEqualToIgnoringCase("true");


//        Assertions that element contains some value Ignoring Case Sensitivity
        String resultText = getDriver().findElement(By.xpath("//div[@id='samplePageResult']//section")).getText(); //get text from container div
        assertThat(resultText).containsIgnoringCase("Oleg Markov");
        assertThat(resultText).containsIgnoringCase("test@gmail.com");
        assertThat(resultText).containsIgnoringCase("omarkov");
        assertThat(resultText).containsIgnoringCase("[entered]");// check that Password has been entered
        assertThat(resultText).doesNotContain("password1"); // check that div doesn't contain entered Password

    }

    @And("^I print browser logs$")
    public void iPrintBrowserLogs() {
        LogEntries logs = getDriver().manage().logs().get("browser");

        for (LogEntry log : logs) {
            System.out.println(log);

            if (log.getLevel().toString().equals("SEVERE")) {
                System.out.println("!!!SEVERE!!!");
            }

        }


    }

    @And("^I \"([^\"]*)\" third party agreement$")
    public void iThirdPartyAgreement(String action) throws Throwable {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();

        if (action.equalsIgnoreCase("accept")) {
            getDriver().switchTo().alert().accept();
        } else if (action.equalsIgnoreCase("dismiss")) {
            getDriver().switchTo().alert().dismiss();
        } else {
            throw new Exception("Unknown action: " + action);
        }
    }

    @And("^I add contact \"([^\"]*)\" with phone \"([^\"]*)\"$")
    public void iAddContactWithPhone(String name, String phone) throws Throwable {

        getDriver().switchTo().frame("additionalInfo");
        getDriver().findElement(By.xpath("//input[@id='contactPersonName']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys(phone);
        getDriver().switchTo().defaultContent();
    }

    @And("^I verify \"([^\"]*)\" in related documents$")
    public void iVerifyInRelatedDocuments(String documentName) throws Throwable {

        String curerntWindow = getDriver().getWindowHandle();

        getDriver().findElement(By.xpath("//button[contains(@onclick,'window')]")).click();

        Set<String> windows = getDriver().getWindowHandles();
        for (String window : windows) {
            getDriver().switchTo().window(window);
        }


        String documentPageText = getDriver().findElement(By.xpath("//body")).getText();
        assertThat(documentPageText).containsIgnoringCase(documentName);

        getDriver().switchTo().window(curerntWindow);

    }
}
