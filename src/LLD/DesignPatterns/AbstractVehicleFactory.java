package LLD.DesignPatterns;

// Abstract Factory Interface for Vehicle
interface VehicleFactory {
    Vehicle createVehicle();
    Engine createEngine();
}

// Concrete Factory for Petrol Vehicle
class PetrolVehicleFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Bike(); // Return a bike
    }

    public Engine createEngine() {
        return new PetrolEngine(); // Return Petrol Engine
    }
}

// Concrete Factory for Diesel Vehicle
class DieselVehicleFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Truck(); // Return a truck
    }

    public Engine createEngine() {
        return new DieselEngine(); // Return Diesel Engine
    }
}

// Abstract Product Interface for Vehicles
interface Vehicle {
    void drive();
}

// Abstract Product Interface for Engine
interface Engine {
    void start();
}

// Concrete Product for Bike
class Bike implements Vehicle {
    public void drive() {
        System.out.println("Driving a Bike.");
    }
}

// Concrete Product for Truck
class Truck implements Vehicle {
    public void drive() {
        System.out.println("Driving a truck.");
    }
}

// Concrete Product for Petrol Engine
class PetrolEngine implements Engine {
    public void start() {
        System.out.println("Starting petrol engine.");
    }
}

// Concrete Product for Diesel Engine
class DieselEngine implements Engine {
    public void start() {
        System.out.println("Starting diesel engine.");
    }
}

// Client Code
public class AbstractVehicleFactory {
    public static void main(String[] args) {
        // Creating a Petrol Vehicle
        VehicleFactory petrolVehicleFactory = new PetrolVehicleFactory();
        Vehicle petrolVehicle = petrolVehicleFactory.createVehicle();
        Engine petrolEngine = petrolVehicleFactory.createEngine();

        petrolEngine.start();
        petrolVehicle.drive();

        // Creating a Diesel Vehicle
        VehicleFactory dieselVehicleFactory = new DieselVehicleFactory();
        Vehicle dieselVehicle = dieselVehicleFactory.createVehicle();
        Engine dieselEngine = dieselVehicleFactory.createEngine();

        dieselEngine.start();
        dieselVehicle.drive();
    }
}

