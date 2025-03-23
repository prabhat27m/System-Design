package LLD.DesignPatterns.Prototype;

/**
 * Simple Prototype Design Pattern Example
 */

// Prototype interface
interface Cloneable {
    Object clone();
}

// Concrete prototype class
class Vehicle implements Cloneable {
    private String brand;
    private String model;
    private String color;
    private int year;

    public Vehicle() {
        // Default constructor
    }

    // Copy constructor used for cloning
    public Vehicle(Vehicle source) {
        this.brand = source.brand;
        this.model = source.model;
        this.color = source.color;
        this.year = source.year;
    }

    @Override
    public Vehicle clone() {
        return new Vehicle(this);
    }

    // Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Vehicle [brand=" + brand + ", model=" + model +
                ", color=" + color + ", year=" + year + "]";
    }
}

// Main class to demonstrate the Prototype pattern
public class CarPrototype {
    public static void main(String[] args) {
        // Create and set up the original prototype
        Vehicle carPrototype = new Vehicle();
        carPrototype.setBrand("Toyota");
        carPrototype.setModel("Camry");
        carPrototype.setColor("White");
        carPrototype.setYear(2023);

        System.out.println("Original prototype: " + carPrototype);

        // Clone the prototype to create a new car
        Vehicle car1 = carPrototype.clone();
        // Customize the clone
        car1.setColor("Red");

        // Clone again to create another car
        Vehicle car2 = carPrototype.clone();
        // Customize the second clone
        car2.setColor("Blue");
        car2.setYear(2024);

        // Print the results
        System.out.println("\nCar 1 (cloned and modified): " + car1);
        System.out.println("Car 2 (cloned and modified): " + car2);
        System.out.println("Original prototype unchanged: " + carPrototype);
    }
}