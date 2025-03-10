package LLD.DesignPatterns.Strategy;

interface DriveStrategy {
    void drive(Vehicle vehicle);
}

// Vehicle is now a concrete class, but with abstract methods for vehicle-specific details
abstract class Vehicle {
    private final DriveStrategy driveStrategy;

    // Constructor now takes the DriveStrategy as a parameter
    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    // Abstract methods that must be implemented by subclasses
    public abstract String getVehicleType();
    public abstract String getVehicleModelNumber();

    // Perform driving using the assigned strategy
    public void performDrive() {
        if (driveStrategy != null) {
            driveStrategy.drive(this);
        } else {
            System.out.println("No driving strategy set for " + getVehicleType());
        }
    }
}

// Concrete Vehicle classes
class PassengerVehicle extends Vehicle {
    public PassengerVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);  // Pass the strategy to the parent class
    }

    @Override
    public String getVehicleType() {
        return "Passenger Vehicle";
    }

    @Override
    public String getVehicleModelNumber() {
        return "PASS234";
    }
}

class SportsVehicle extends Vehicle {
    public SportsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);  // Pass the strategy to the parent class
    }

    @Override
    public String getVehicleType() {
        return "Sports Vehicle";
    }

    @Override
    public String getVehicleModelNumber() {
        return "SPRT234";
    }
}

class OffroadingVehicle extends Vehicle {
    public OffroadingVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);  // Pass the strategy to the parent class
    }

    @Override
    public String getVehicleType() {
        return "Offroading Vehicle";
    }

    @Override
    public String getVehicleModelNumber() {
        return "OFFR234";
    }
}

// Concrete DriveStrategy implementations
class PassengerVehicleDriveStrategy implements DriveStrategy {
    @Override
    public void drive(Vehicle vehicle) {
        System.out.println("Driving a passenger vehicle with comfort and efficiency.");
    }
}

class SportsVehicleDriveStrategy implements DriveStrategy {
    @Override
    public void drive(Vehicle vehicle) {
        System.out.println("Driving a sports vehicle with speed and performance.");
    }
}

class OffroadingVehicleDriveStrategy implements DriveStrategy {
    @Override
    public void drive(Vehicle vehicle) {
        System.out.println("Driving an off-roading vehicle with rugged terrain handling.");
    }
}

public class VehicleDriveStrategy {
    public static void main(String[] args) {
        // Pass the specific DriveStrategy when creating each Vehicle instance
        Vehicle passengerVehicle = new PassengerVehicle(new PassengerVehicleDriveStrategy());
        Vehicle sportsVehicle = new SportsVehicle(new SportsVehicleDriveStrategy());
        Vehicle offroadingVehicle = new OffroadingVehicle(new OffroadingVehicleDriveStrategy());

        // Performing drives
        passengerVehicle.performDrive();
        sportsVehicle.performDrive();
        offroadingVehicle.performDrive();
    }
}
