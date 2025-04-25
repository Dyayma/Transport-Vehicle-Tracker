import java.util.Scanner;

abstract class Vehicle {
    private String brand;
    private String model;
    private double distanceTraveled;
    private double fuelUsed;

    public Vehicle(String brand, String model, double distanceTraveled, double fuelUsed) {
        this.brand = brand;
        this.model = model;
        this.distanceTraveled = distanceTraveled;
        if (fuelUsed <= 0) {
            throw new IllegalArgumentException("Fuel used must be greater than zero.");
        }
        this.fuelUsed = fuelUsed;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public double getFuelUsed() {
        return fuelUsed;
    }

    public abstract double calculateFuelEfficiency();
    public abstract String getVehicleType();

    public String getSummary() {
        return String.format(
            "Brand: %s\n" +
            "Model: %s\n" +
            "Distance: %.2f km\n" +
            "Fuel: %.2f L\n" +
            "Efficiency: %.2f km/L\n" +
            "Type: %s",
            brand,
            model,
            distanceTraveled,
            fuelUsed,
            calculateFuelEfficiency(),
            getVehicleType()
        );
    }
}

class Car extends Vehicle {
    public Car(String brand, String model, double distanceTraveled, double fuelUsed) {
        super(brand, model, distanceTraveled, fuelUsed);
    }

    @Override
    public double calculateFuelEfficiency() {
        return getDistanceTraveled() / getFuelUsed();
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    public Bike(String brand, String model, double distanceTraveled) {
        super(brand, model, distanceTraveled, distanceTraveled / 100); // 100 km/L efficiency
    }

    @Override
    public double calculateFuelEfficiency() {
        return 100.0;
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}

class Truck extends Vehicle {
    public Truck(String brand, String model, double distanceTraveled, double fuelUsed) {
        super(brand, model, distanceTraveled, fuelUsed);
    }

    @Override
    public double calculateFuelEfficiency() {
        return getDistanceTraveled() / getFuelUsed();
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }
}

public class TransportTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehicle[] vehicles = new Vehicle[3];

        try {
            System.out.println("Enter details for a Car:");
            System.out.print("Brand: ");
            String carBrand = scanner.nextLine();
            System.out.print("Model: ");
            String carModel = scanner.nextLine();
            System.out.print("Distance Traveled (km): ");
            double carDistance = Double.parseDouble(scanner.nextLine());
            System.out.print("Fuel Used (liters): ");
            double carFuel = Double.parseDouble(scanner.nextLine());
            vehicles[0] = new Car(carBrand, carModel, carDistance, carFuel);

            System.out.println("\nEnter details for a Bike:");
            System.out.print("Brand: ");
            String bikeBrand = scanner.nextLine();
            System.out.print("Model: ");
            String bikeModel = scanner.nextLine();
            System.out.print("Distance Traveled (km): ");
            double bikeDistance = Double.parseDouble(scanner.nextLine());
            vehicles[1] = new Bike(bikeBrand, bikeModel, bikeDistance);

            System.out.println("\nEnter details for a Truck:");
            System.out.print("Brand: ");
            String truckBrand = scanner.nextLine();
            System.out.print("Model: ");
            String truckModel = scanner.nextLine();
            System.out.print("Distance Traveled (km): ");
            double truckDistance = Double.parseDouble(scanner.nextLine());
            System.out.print("Fuel Used (liters): ");
            double truckFuel = Double.parseDouble(scanner.nextLine());
            vehicles[2] = new Truck(truckBrand, truckModel, truckDistance, truckFuel);

            System.out.println("\n--- Vehicle Summary ---");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.getSummary());
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
