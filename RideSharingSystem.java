import java.util.Scanner;

// Custom Exception for invalid ride type
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract class Ride
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance; // accessible to subclasses

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    // Encapsulation (getters)
    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method
    public abstract double calculateFare();
}

// BikeRide subclass
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10;
    }
}

// CarRide subclass
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20;
    }
}

// Main class
public class RideSharingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = sc.nextDouble();

            if (distance <= 0) {
                System.out.println("Distance must be greater than 0");
                return;
            }

            Ride ride;
            // Hardcoded driver/vehicle details for simplicity
            if (rideType.equals("bike")) {
                ride = new BikeRide("Ravi Kumar", "TS09AB1234", distance);
            } else if (rideType.equals("car")) {
                ride = new CarRide("Suresh Reddy", "TS08CD5678", distance);
            } else {
                throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            // Output
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input.");
        } finally {
            sc.close();
        }
    }
}
