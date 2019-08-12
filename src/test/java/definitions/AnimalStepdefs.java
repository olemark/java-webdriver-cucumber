package definitions;

import cucumber.api.java.en.Given;
import pages.Cat;
import pages.Dog;

public class AnimalStepdefs {
    @Given("^I work with classes$")
    public void iWorkWithClasses() {

       Cat myCat = new Cat();
       Dog myDog = new Dog("Fluffy");   //need to give name because there is no dog without name in Dog class

       myCat.meow();
       System.out.println(myCat.getName());

       myCat.setName("Dusty");
       myCat.sleep();
       myCat.walk();
       myCat.eat("cat's food");

       myDog.walk();
       myDog.bark();
    }
}
