import java.util.Scanner;

public class Main {

    abstract class Transport {
        private String brand;
        private String model;
        private double distanceTraveled;
        private double fuelUsed;

        public Transport(String brand, String model, double distanceTraveled, double fuelUsed) {
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

    class Car extends Transport {
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

    class Bike extends Transport {
        public Bike(String brand, String model, double distanceTraveled) {
            super(brand, model, distanceTraveled, distanceTraveled / 100);
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

    class Truck extends Transport {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transport[] vehicles = new Transport[3];
        Main mainClass = new Main();

        try {
            // Car
            System.out.println("Enter details for a Car:");
            System.out.print("Brand: ");
            String carBrand = scanner.nextLine();
            System.out.print("Model: ");
            String carModel = scanner.nextLine();
            System.out.print("Distance Traveled (km): ");
            double carDistance = Double.parseDouble(scanner.nextLine());
            System.out.print("Fuel Used (liters): ");
            double carFuel = Double.parseDouble(scanner.nextLine());
            vehicles[0] = mainClass.new Car(carBrand, carModel, carDistance, carFuel);

            // Bike 
            System.out.println("\nEnter details for a Bike:");
            System.out.print("Brand: ");
            String bikeBrand = scanner.nextLine();
            System.out.print("Model: ");
            String bikeModel = scanner.nextLine();
            System.out.print("Distance Traveled (km): ");
            double bikeDistance = Double.parseDouble(scanner.nextLine());
            vehicles[1] = mainClass.new Bike(bikeBrand, bikeModel, bikeDistance);

            // Truck 
            System.out.println("\nEnter details for a Truck:");
            System.out.print("Brand: ");
            String truckBrand = scanner.nextLine();
            System.out.print("Model: ");
            String truckModel = scanner.nextLine();
            System.out.print("Distance Traveled (km): ");
            double truckDistance = Double.parseDouble(scanner.nextLine());
            System.out.print("Fuel Used (liters): ");
            double truckFuel = Double.parseDouble(scanner.nextLine());
            vehicles[2] = mainClass.new Truck(truckBrand, truckModel, truckDistance, truckFuel);

            //Vehicle summary
            System.out.println("\n--- Vehicle Summary ---");
            for (Transport vehicle : vehicles) {
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
