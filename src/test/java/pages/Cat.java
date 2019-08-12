package pages;

public class Cat extends Animal {

    public Cat() {              //nomame property for homeless cats

    }

    public Cat(String name) {     // property for cats with names
        setName(name);

    }

    public void meow() {
        System.out.println(getClass() + " " + getName() + " says: Meow!");
    }
}
