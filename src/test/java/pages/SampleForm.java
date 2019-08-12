package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getDriver;


public class SampleForm extends Page {

    public SampleForm() {
        setUrl("https://skryabin.com/webdriver/html/sample.html");  //we only put new url if we navigate to it to entry the App  (so we set url here)
    }

    //create annotations
    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='middleName']")
    private WebElement middleName;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//div[@aria-describedby='nameDialog']//span[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@name='agreedToPrivacyPolicy']")
    private WebElement agreedToPrivacyPolicy;


    @FindBy(xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name='contactPersonName']")
    private WebElement contactPersonName;

    @FindBy(xpath = "//input[@name='contactPersonPhone']")
    private WebElement contactPersonPhone;



// working with iframe
    public void switchToFrameByName(String name) {
        getDriver().switchTo().frame(name);
    }

    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }
    public void fillContactNameAndPhone(String name, String phone) {
        switchToFrameByName("additionalInfo");  //switch to iframe with name additionalInfo
        sendKeys(contactPersonName, name);
        sendKeys(contactPersonPhone, phone);
        switchToDefaultContent();
    }





    public void fillUsername(String value) { //  using created generic method "sendKeys" in "Page"
        sendKeys(username, value);
    }


    public void fillPassword(String value) {  //  using created method "sendKeys" in "Page" for sendKeys to "password"
        sendKeys(password, value);
    }
//    public void fillPassword(String value) {
//        password.sendKeys(value);
//    }

    public void fillConfirmPassword(String value) {  //  using created generic method "sendKeys" in "Page"
        sendKeys(confirmPassword, value);

    }

    public void fillEmail(String value) {
        sendKeys(email, value);
    }

    public void fillName(String firstName, String lastName) {  //create method for filling name
        click(name);  //  using created generic method "click" in "Page" for clicking "name"
        sendKeys(this.firstName, firstName); //using generic method sendKeys, using variable "this" because of variables "firstName" and "lastName" duplication in this method
        sendKeys(this.lastName, lastName);
        click(saveButton);
    }


    public void fillName(String firstName, String middleName, String lastName) {  //create method for filling name with middleName (may be the same name)
        click(name);  //  using created generic method "click" in "Page" for clicking "name"
        sendKeys(this.firstName, firstName); //using generic method sendKeys, using variable "this" because of variables "firstName" and "lastName" duplication in this method
        sendKeys(this.middleName, middleName); //using generic method sendKeys, using variable "this" because of variables "firstName" and "lastName" duplication in this method
        sendKeys(this.lastName, lastName);
        click(saveButton);
    }

    public void checkAgreedToPrivacyPolicy() {    // use created method "click" in "Page" for "agreedToPrivacyPolicy" button
        click(agreedToPrivacyPolicy);


    }

    public void clickSubmit() {
        click(submitButton); //  using created generic method "click" in "Page" for clicking "submitButton"
    }



    public boolean isConfirmPasswordDisabled() {  //  create method that returns true or false for "password"
        return !confirmPassword.isEnabled();
    }





}
