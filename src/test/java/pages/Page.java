package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class Page {

    private String url;

    public Page() {
        PageFactory.initElements(getDriver(), this);  //special constructor of the parent, needs for initialisation
    }

    public void setUrl(String url) {
        this.url = url;   //this url needs url - setter for url
    }


    public String getUrl() {
        return url;
    }

    public void open() {
        getDriver().get(url);
    }  // how to open public


    public static void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }


    public void click(WebElement element) {  // create generic method "click" with JS click - applying method "click" for any element
        waitForClickable(element);
        try {
            element.click();   // try to normal click
        } catch (WebDriverException e) {
            clickWithJS(element); // if dosn't work try with JS click
        }

    }


    public void waitForVisible(WebElement element) {  // create generic method "waitForVisible" to wait of visibility of element
        getWait().until(ExpectedConditions.visibilityOf(element));
    }


    public void sendKeys(WebElement element, String value) {   // create generic method "sendKeys" with wait of visibility of element
        waitForVisible(element);  // using here generic method waitForVisible created above
        element.sendKeys(value);
    }


    public void waitForClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

}
