package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.*;
import static support.TestContext.getWait;

public class ConverterPage extends Page {

    public ConverterPage() {
        setUrl("https://www.unitconverters.net/");
    }


    //create annotations
    @FindBy(xpath = "//input[@name='toVal']")
    private WebElement toValue;


    private WebElement getTabName(String value) {
        return getDriver().findElement(By.xpath(String.format("//*[contains(@href,'%s')]", value)));
    }


    public void clickTab(String value) {
        getTabName(value).click();
    }


    private WebElement tabFromName(String value) {
        return getDriver().findElement(By.xpath("//select[@id='calFrom']"));
    }

    public void selectFrom(String value) {
        new Select(tabFromName(value)).selectByVisibleText(value);
    }


    private WebElement tabToName(String value) {
        return getDriver().findElement(By.xpath("//select[@id='calTo']"));
    }

    public void selectTo(String value) {
        new Select(tabToName(value)).selectByVisibleText(value);
    }


    private WebElement valueFromField(String value) {
        return getDriver().findElement(By.xpath("//input[@name='fromVal']"));
    }


    public void enterFromValue(String value) {
        valueFromField(value).sendKeys(value);

    }


    public void getWaitForValue(WebElement value) {

        getWait().until(ExpectedConditions.visibilityOf(value));

    }

    public void getWaitForToValue() {
        getWaitForValue(toValue);

    }

    public String toValueResult() {
        return toValue.getAttribute("value");

    }


}

