package pages;

public class Dog extends Animal{

    public Dog(String name) {            // property for dogs with names (no homeless dogs without names)
        setName(name);
    }

    public void bark() {
        System.out.println(getClass() + " " + getName() + " says: Bark!");
    }
}
