package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class CareersLandingPage extends CareersHeader{



    public CareersLandingPage() {
        setUrl("https://skryabin-careers.herokuapp.com/");
        setHeader("Careers");  //set the header name for Landing Page
    }




}
