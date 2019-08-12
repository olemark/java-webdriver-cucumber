package support;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


// whenever we need use something for REST API we create methods in RestWrapper Class
public class RestWrapper {

    private final String baseUrl = "https://skryabin.com/recruit/api/v1/"; // define base URL (final means constant - will not change)
//    private final String baseUrl = "https://localhost:8090/recruit/api/v1/"; // using this with Charles Proxy
    private static String loginToken;  // static means it's share across any instance           // define Token (do not know the value)
    private final String CONTENT_TYPE = "Content-Type";                    // define "Content-Type" as a String for future using
    private final String JSON = "application/json";                        // define "application/json" as a String for future using
    private final String TOKEN = "x-access-token";
    public static final String POSITIONS = "positions";    // create a constant variable
    public static final String POSITION = "position";  // create a constant variable

    //    public void login(HashMap<String, String> credentials) throws UnirestException {
    public RestWrapper login(HashMap<String, String> credentials) throws UnirestException {  // 1of3. we can also use this ("public RestWrapper" instead of "public void") to use it simple in Hooks file
        JSONObject payload = new JSONObject(credentials);
        RequestBodyEntity request = Unirest.post(baseUrl + "login")         // assign your preparation for request to variable "request"
                .header(CONTENT_TYPE, JSON)                                 // providing Header JSON format (by default)
                .body(payload);                                             // preparation for the request. JSONObject() is class that works with JSON format (body expects JSON object)

        HttpResponse<JsonNode> response = request.asJson();                 // making request using ".asJson()" and saving result in variable "response"

        assertThat(response.getStatus()).isEqualTo(200);                    // get status from "response" variable and assert it with int 200

        JSONObject body = response.getBody().getObject();                   // get objects from Body and assign it to JSONObject variable "body"

        loginToken = body.getString("token");                               // get "token" value from body and assign it to empty String "loginToken"

        System.out.println("Login successful! Token: " + loginToken);
        return this;  // 2of3. we can also use this (return RestWrapper itself) to use it simple in Hooks (add this string) (see Hooks file for 3of3)
    }

    public JSONObject createPosition(HashMap<String, String> position) throws UnirestException {

        //need to convert dateOpen from position.yml to different format to use this to for future assertion(see Verifications steps). Need to convert it from 01/25/2019(in UI format from yml file) to 2019-01-25 (ISO format saving in database or in REST API)
        String dateOpen = position.get("dateOpen");
        String dateOpenISO = new SimpleDateFormat("yyy-MM-dd").format(new Date(dateOpen)); //converting using SimpleDateFormat
        dateOpenISO = dateOpenISO + "T05:00:00.000Z";  //add time to format (because it expects also the time) - see verification step. It's bug in the application. We can file it.
        position.put("dateOpen", dateOpenISO); //put new date format after converting


        //preparation for request
        JSONObject positionJson = new JSONObject(position);
        RequestBodyEntity request = Unirest.post(baseUrl + POSITIONS)
                .header(CONTENT_TYPE, JSON)
                .header(TOKEN, loginToken)
                .body(positionJson);

        //executing request, getting response
        HttpResponse<JsonNode> response = request.asJson();


        //parse the response, verify response
        assertThat(response.getStatus()).isEqualTo(201);
        JSONObject responsePositionJson = response.getBody().getObject();
        System.out.println("\n\nPosition created: " + responsePositionJson); // "\n\n" means make from new line, like add empty line: System.out.println()


        assertObjectsEqual(responsePositionJson, positionJson); //assert expected and actual result from generic method assertObjectsEqual
        TestContext.setTestData(POSITION, responsePositionJson); //need for save data in POSITION variable (created before)

        return responsePositionJson;

    }

    public void deletePosition(int positionId) throws Exception {
        HttpRequestWithBody request = Unirest.delete(baseUrl + POSITIONS + "/" + positionId)
                .header(TOKEN, loginToken);

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(204);
        System.out.println("\n\nSuccessfully deleted position id: " + positionId);

    }


    public JSONArray getPositions() throws Exception {    // getting created position from list of positions for assertions
        GetRequest request = Unirest.get(baseUrl + POSITIONS);
        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isBetween(200, 204); // assert that response might be between 200 and 204
        JSONArray positionJson = response.getBody().getArray();

        return positionJson;

    }


    public JSONObject updatePosition(HashMap<String, String> fields, int positionId) throws Exception {
        JSONObject fieldJson = new JSONObject(fields);
        RequestBodyEntity request = Unirest.put(baseUrl + POSITIONS + "/" + positionId)
                .header(CONTENT_TYPE, JSON)
                .header(TOKEN, loginToken)
                .body(fieldJson);

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject responseFieldsJson = response.getBody().getObject();

        assertObjectsEqual(responseFieldsJson, fieldJson);  //assert expected and actual result from generic method assertObjectsEqual
        System.out.println("\n\nPosition " + positionId + " is updated: " + responseFieldsJson);

        return responseFieldsJson;

    }

    public JSONObject getPositionById(int positionId) throws Exception {
        GetRequest request = Unirest.get(baseUrl + POSITIONS + "/" + positionId);


        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject positionJson = response.getBody().getObject();

        System.out.println("\n\nReturned position " + positionJson);

        return positionJson;

    }


    public static void assertObjectsEqual(JSONObject actualObject, JSONObject expectedObject) {

        Set<String> keys = expectedObject.keySet();  //make an array for each key in Hashmap (getting the kye only from expectedPosition(POST), because in actualPosition (when we do GET) may be more keys)
        for (String key : keys) {   //for each key from keys array
            System.out.println();
            System.out.println("Actual: " + actualObject.get(key));
            System.out.println("Expected: " + expectedObject.get(key));
            assertThat(actualObject.get(key).equals(expectedObject.get(key))).isTrue()  // assert each actual key with expected key and check that it it true
                    .overridingErrorMessage("Actual: " + actualObject.get(key) + " not equal " + expectedObject.get(key)); // do it if it is FALSE
        }



    }




}
