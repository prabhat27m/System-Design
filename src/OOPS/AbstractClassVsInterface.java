package OOPS;

// Interface Example
interface Animal {
    // Interface variables (constants)
    String TYPE = "Mammal"; // Implicitly public, static, and final

    // Abstract method
    void makeSound();

    // Static method (since Java 8)
    static int getDefaultLegs() {
        return 4;
    }

    // Default method (since Java 8)
    default void describe() {
        System.out.println("This is an animal of type: " + TYPE);
    }
}

// Implementing the interface
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    public void getBasicDetails() {
        System.out.println("Default number of legs: " + Animal.getDefaultLegs());
        describe(); // Calling default method from interface
    }

    @Override
    public void describe(){
        System.out.println("This is overriden !!!");
    }
}

// Abstract Class Example
abstract class Car {
    String brand;

    // Constructor
    public Car(String brand) {
        this.brand = brand;
    }

    // Concrete method
    public void start() {
        System.out.println(brand + " car is starting.");
    }

    // Abstract method
    public abstract void drive();
}

// Subclass extending the abstract class
class Sedan extends Car {
    public Sedan(String brand) {
        super(brand);
    }

    @Override
    public void drive() {
        System.out.println(brand + " car is being driven.");
    }
}

// Main Class
public class AbstractClassVsInterface {
    public static void main(String[] args) {
        // Using the interface
        Animal dog = new Dog();
        dog.makeSound(); // Output: Woof!

        // Casting to access Dog-specific method
        ((Dog) dog).getBasicDetails();
        // Output:
        // Default number of legs: 4
        // This is an animal of type: Mammal

        System.out.println(Animal.getDefaultLegs());
        // Using the abstract class
        Car myCar = new Sedan("Toyota");
        myCar.start();  // Output: Toyota car is starting.
        myCar.drive();  // Output: Toyota car is being driven.
    }
}
