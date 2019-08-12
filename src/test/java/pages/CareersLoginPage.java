package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersLoginPage extends CareersHeader{



    @FindBy(xpath = "//label[@for='loginUsername']/following-sibling::input")
    private WebElement username;

    @FindBy(xpath = "//label[@for='loginPassword']/following-sibling::input")
    private WebElement password;

    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement submit;

    public void login (String username, String password){
        sendKeys(this.username, username);
        sendKeys(this.password, password);
        click(submit);
    }

    public CareersLoginPage() {
        setHeader("Login");  //set the header name for Login Page
    }




}
