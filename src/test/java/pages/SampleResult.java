package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SampleResult extends Page{



    //create annotations
    @FindBy(xpath ="//div[@id='samplePageResult']//section")
    private WebElement result;

    @FindBy(xpath ="//b[@name='password']")
    private WebElement passwordResult;

    @FindBy(xpath ="//legend[@class='applicationResult']")
    private WebElement legendResult;

    @FindBy(xpath ="//b[@name='agreedToPrivacyPolicy']")
    private WebElement agreedToPrivacyPolicyResult;



    public String getResult() {
        waitForVisible(result);  // using generic created method "waitForVisible" from Page
        return result.getText();
    }
    public String getPasswordResult() {

        waitForVisible(passwordResult);
        return passwordResult.getText();
    }

    public String getAgreedToPrivacyPolicyResult() {
        waitForVisible(agreedToPrivacyPolicyResult);
        return agreedToPrivacyPolicyResult.getText();
    }

    public Boolean displayedLegendResult() {

        return legendResult.isDisplayed();
    }

}
