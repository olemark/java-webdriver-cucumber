package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class CareersHeader extends Page {



    @FindBy(xpath = "//span[contains(@class,'position-center')]")
    private WebElement headerTitle;


    @FindBy(xpath = "//a[@href='/login']")
    private WebElement login;

    @FindBy(xpath = "//span[@class='logout-box']/a")
    private WebElement name;

    @FindBy(xpath = "//a[@href='/recruit']")
    private WebElement recruit;




    private String headerText;  //text we have to verify

    public void setHeader(String value) {   //setter for header text
        headerText = value;
    }

    public void assertHeader() {   //assertion for header text
        assertThat(headerTitle.getText().equals(headerText)).isTrue();
    }




    public void clickLogin() {
        click(login);
    }

    public void clickRecruit() {
        click(recruit);
    }


    public String getNameOfLoggedUser(){
        return name.getText();
    }
}
