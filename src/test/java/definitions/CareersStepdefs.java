package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import pages.*;
import support.RestWrapper;
import support.TestContext;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class CareersStepdefs {


    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String page) throws Throwable {
        switch (page) {

            case "careers":
                new CareersLandingPage().open();
                break;
            case "sample":
                System.out.println("Sample");
                break;
            default:
                throw new Exception("Page " + page + " not recognized!");
        }


    }

    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String role) throws Throwable {

        HashMap<String, String> user = getData(role);  //initialising data from "role".yml file (in our case file is recruiter.yml). It means here that generic "getData(role)" is equal to "getRecruiter()".
        CareersLandingPage landingPage = new CareersLandingPage();  //initialising of LandingPage
        landingPage.assertHeader();  //validation of headerTitle "Careers" on the Landing Page
        landingPage.clickLogin();

        CareersLoginPage loginPage = new CareersLoginPage(); //initialising of LoginPage
        loginPage.assertHeader();   //validation of headerTitle "Login" on the Login Page
        loginPage.login(user.get("email"), user.get("password"));  //using method login() on the Login Page


    }

    @Then("^I verify \"([^\"]*)\" login$")
    public void iVerifyLogin(String role) throws Throwable {
        HashMap<String, String> user = getData(role);
        CareersLandingPage landingPage = new CareersLandingPage();

        String actualName = landingPage.getNameOfLoggedUser();
        assertThat(actualName).isEqualTo(user.get("name")); //verifying name displayed after recruiter login on Landing Page

    }

    @When("^I create new position$")
    public void iCreateNewPosition() throws Exception {
        CareersLandingPage landingPage = new CareersLandingPage(); //initialising CareersLandingPage
        landingPage.clickRecruit();

        CareersRecruitPage recruitPage = new CareersRecruitPage();  //initialising CareersRecruitPage
        recruitPage.assertHeader(); // verifying header text on CareersRecruitPage
        recruitPage.clickNewPosition();

        CareersNewPositionPage newPositionPage = new CareersNewPositionPage();
        newPositionPage.assertHeader();

//Adding Time Stamp to title to make it unique, create (set) a new title in "testData" HashMap in TestContext and replace "title"'s value  in position.yml (for next assertion)
        HashMap<String, String> position = TestContext.getPosition(); // initialising data for "position.yml" file
        String title = position.get("title"); //getting value for "title" key from "position.yml" and assign it to String title (for our case it equals "VP, Development")
        title = TestContext.addTimeStamp(title); //using "addTimeStamp" method which adding Time Stamp to variable "title" and assign it to the new variable "title"
        TestContext.setTestData("positionTitle", title); //set a new "title" value (with data stamp) to temporary field of "testData" HashMap in TestContext
        position.put("title", title); //put(add) new  value for key "title" in "position.yml" (replace "title"'s value)

        newPositionPage.fillPosition(position);
        newPositionPage.submit();

    }

    @And("^I verify position created$")
    public void iVerifyPositionCreated() throws Exception {
        CareersRecruitPage recruitPage = new CareersRecruitPage();
        boolean isPresent = recruitPage.isPositionPresentOnPage(TestContext.getStringTestData("positionTitle"));
        assertThat(isPresent).isTrue();

    }


    @Given("^I login to REST as \"([^\"]*)\"$")
    public void iLoginToRESTAs(String role) throws Throwable {
        HashMap<String, String> user = getData(role);
        new RestWrapper().login(user);
    }

    @When("^I create via REST new position$")
    public void iCreateViaRESTNewPosition() throws Exception {
        HashMap<String, String> position = getPositionWithTimestamp();

        new RestWrapper().createPosition(position);
    }

    @Then("^I verify via REST a new position in the list$")
    public void iVerifyViaRESTANewPositionInTheList() throws Exception {
        JSONArray actualPositions = new RestWrapper().getPositions(); //getting the list of actual positions
        JSONObject expectedPosition = getJsonTestData(RestWrapper.POSITION);  //getting created position from temporary saved data and assign it to variable expectedPosition

        boolean found = false; // concept of flag (by default found = false)

        for (int i = 0; i < actualPositions.length(); i++) {  //make a loop that check actual position ID equal expectedPosition ID (only focus on ID)
            JSONObject actualPosition = actualPositions.getJSONObject(i);

            if (actualPosition.getInt("id") == expectedPosition.getInt("id")) {
                found = true;  // only if condition is true in that case found = true
                break;         // if it's found then break the loop
            }
        }
        assertThat(found).isTrue();  //than we do assertion that found is true

    }


    @And("^I update via REST new position$")
    public void iUpdateViaRESTNewPosition() throws Exception {
        HashMap<String, String> fieldsToUpdate = new HashMap<>();

        fieldsToUpdate.put("city", "Mountain View");  //update new data
        int positionId = getJsonTestData(RestWrapper.POSITION).getInt("id");

        JSONObject response = new RestWrapper().updatePosition(fieldsToUpdate, positionId);
        assertThat(response.getString("city")).isEqualTo("Mountain View");  // verification of updated data key: "city", value: "Mountain View" with data gotten from response

        JSONObject position = getJsonTestData(RestWrapper.POSITION); //getting temporary saved data for POSITION and assign it to variable position
        position.put("city", "Mountain View");
        setTestData(RestWrapper.POSITION, position);
    }


    @Then("^I verify via REST position details$")
    //How we can do it: We posted position(POST) than we read(GET) and do assertion
    public void iVerifyViaRESTPositionDetails() throws Exception {
        JSONObject expectedPosition = getJsonTestData(RestWrapper.POSITION);
        int positionId = expectedPosition.getInt("id");
        JSONObject actualPosition = new RestWrapper().getPositionById(positionId);

        RestWrapper.assertObjectsEqual(actualPosition, expectedPosition); //assert expected and actual result from generic method assertObjectsEqual


    }

    @And("^I delete via REST new position$")
    public void iDeleteViaRESTNewPosition() throws Exception {
        int positionId = getJsonTestData(RestWrapper.POSITION).getInt("id"); // get position id from created and saved before POSITION and assign it to variable (id it's the number so we use int)
        new RestWrapper().deletePosition(positionId);

    }


}
