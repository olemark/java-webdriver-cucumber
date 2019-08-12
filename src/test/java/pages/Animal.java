package pages;

public abstract class Animal {

    private String name;

    public Animal() {                       //empty constructor
        name = "Nameless one";              //initialize name in constructor, assign the name
    }

    public String getName() {               //create getter - how do you read the name
        return name;
    }

    public void setName(String name) {        //create setter - how do you update the name
        this.name = name;
    }

    public void walk() {
        System.out.println(getClass() + " " + getName() + " is walking...");
    }

    public void sleep() {
        System.out.println(getClass() + " " + getName() + " is sleeping...");
    }

    public void eat(String what) {
        System.out.println(getClass() + " " + getName() + " is eating " + what);
    }

}
