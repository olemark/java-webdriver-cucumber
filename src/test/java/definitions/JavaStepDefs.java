package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.util.ArrayList;
import java.util.HashMap;

public class JavaStepDefs {
    @Given("^I write first java step$")
    public void iWriteFirstJavaStep() throws PendingException {
        System.out.println("Hello World!");

        String firstName = "Oleg";
        String lastName = "Markov";
        String favoriteColor = "blue";

        System.out.println(firstName + " " + lastName + ". Favorite color: " + favoriteColor);  // Concatenation of strings


    }

    @And("^I print \"([^\"]*)\" in console$")
    public void iPrintInConsole(String text) throws Throwable {
        System.out.println();  //create empty line to start next string from new line
        System.out.println(text);

    }


    @And("^I do actions with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iDoActionsWithAnd(String arg0, String arg1) throws Throwable {
        System.out.println();
        System.out.println();
        System.out.println(arg0);
        System.out.println(arg1);
        System.out.println();

        System.out.println("Uppercase: " + arg0.toUpperCase());
        System.out.println("Uppercase: " + arg1.toUpperCase());
        System.out.println();
        System.out.println("Length of arg0: " + arg0.length());
        System.out.println("Length of arg1: " + arg1.length());
        System.out.println();
        System.out.println("First way: arg0 is equal arg1? " + arg0.equals(arg1)); //String comparison
        System.out.println("Another way: arg0 is equal arg1? " + (arg0 == arg1)); //String comparison (another way)
        System.out.println();
        System.out.println("arg0 is equal arg1 ignoring case arg1? " + arg0.equalsIgnoreCase(arg1));
        System.out.println();
        System.out.println("arg0 contains arg1? " + arg0.toLowerCase().contains(arg1.toLowerCase()));  // if first variable contains second
        System.out.println("arg1 contains arg0? " + arg1.toLowerCase().contains(arg0.toLowerCase()));  // if first variable contains second



    }

    @And("^I compare \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iCompareAnd(String var1, String var2) throws Throwable {

        System.out.println();
        System.out.println();
        if (var1.equals(var2)) {
            System.out.println("Strings are equal!");
        }
        else {
            System.out.println("Strings are not equal!");
        }
    }

    @And("^I print url for \"([^\"]*)\" page$")
    public void iPrintUrlForPage(String site) throws Throwable {
        System.out.println();
        System.out.println();
        if (site.equals("google")) {
            System.out.println(site + " https://www.google.com");
        }
        else if (site.equals("sample")){
            System.out.println(site + " https://skryabin.com/webdriver/html/sample.html");
        }
        else {
            System.out.println(site + " is not supported webpage");
        }
    }






    @Given("^I work with arrays$")
    public void iWorkWithArrays() {

        String[] groceryList = {"milk", "cottage cheese", "oranges"};

        System.out.println(groceryList[0]);
        System.out.println(groceryList[1]);
        System.out.println(groceryList[2]);

        System.out.println();

        for (String item : groceryList){
            System.out.println(item);
        }

        ArrayList<String> anotherGroceryList = new ArrayList();
        anotherGroceryList.add("apple");
        anotherGroceryList.add("ice-cream");
        anotherGroceryList.add("rice");

        System.out.println();



        for (String item : anotherGroceryList){
            System.out.println(item);
        }

        System.out.println();
        System.out.println(anotherGroceryList.get(0));
        System.out.println(anotherGroceryList.get(1));
        System.out.println(anotherGroceryList.get(2));


    }

    @Given("^I work with maps$")
    public void iWorkWithMaps() {
        HashMap<String, String> states = new HashMap<>();
        states.put("CA", "California");
        states.put("DE", "Delaware");
        System.out.println(states.get("DE"));

        HashMap<String,String> admin = new HashMap<>();
        admin.put("username", "Markov");
        admin.put("password", "12345");
        admin.put("address", "123 Main St");
        admin.put("city", "San Ramon");
        admin.put("originCity", "San Ramon");
        admin.put("email", "oleg@markov.com");

        HashMap<String,String> user = new HashMap<>();
        user.put("username", "doe");
        user.put("password", "password12345");
        user.put("address", "123 Main St");
        user.put("city", "Los Altos");
        user.put("originCity", "Los Altos");
        user.put("email", "john@doe.com");

        System.out.println(admin.get("username"));
        System.out.println(admin.get("password"));
        System.out.println(user.get("username"));
        System.out.println(user.get("password"));




    }
}
