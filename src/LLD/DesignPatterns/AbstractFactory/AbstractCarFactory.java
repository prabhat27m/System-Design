package LLD.DesignPatterns.AbstractFactory;


interface CarFactory {
    Car createCar();
    CarSpecification createSpecification();
}

// Concrete Factory for North America Cars
class TataCarFactory implements CarFactory {
    public Car createCar() {
        return new TataCar();
    }

    public CarSpecification createSpecification() {
        return new TataCarSpecification();
    }
}

// Concrete Factory for Europe Cars
class ToyotaCarFactory implements CarFactory {
    public Car createCar() {
        return new ToyotaCar();
    }

    public CarSpecification createSpecification() {
        return new ToyotaCarSpecification();
    }
}

// Abstract Product Interface for Cars
interface Car {
    void assemble();
}

// Abstract Product Interface for Car Specifications
interface CarSpecification {
    void display();
}

// Concrete Product for Sedan Car
class TataCar implements Car {
    public void assemble() {
        System.out.println("Assembling Tata car.");
    }
}

// Concrete Product for Hatchback Car
class ToyotaCar implements Car {
    public void assemble() {
        System.out.println("Assembling Toyota car.");
    }
}

// Concrete Product for North America Car Specification
class TataCarSpecification implements CarSpecification {
    public void display() {
        System.out.println("Tata Car Specification: Safety features compliant with local regulations.");
    }
}

// Concrete Product for Europe Car Specification
class ToyotaCarSpecification implements CarSpecification {
    public void display() {
        System.out.println("Toyota Car Specification: Fuel efficiency and emissions compliant with great standards.");
    }
}


// Client Code
public class AbstractCarFactory {
    public static void main(String[] args) {
        // Creating cars for North America
        CarFactory tataCarFactory = new TataCarFactory();
        Car tataCarFactoryCar = tataCarFactory.createCar();
        CarSpecification tataCarFactorySpecification = tataCarFactory.createSpecification();

        tataCarFactoryCar.assemble();
        tataCarFactorySpecification.display();

        // Creating cars for Europe
        CarFactory toyotaCarFactory = new ToyotaCarFactory();
        Car toyotaCarFactoryCar = toyotaCarFactory.createCar();
        CarSpecification toyotaCarFactorySpecification = toyotaCarFactory.createSpecification();

        toyotaCarFactoryCar.assemble();
        toyotaCarFactorySpecification.display();
    }
}
