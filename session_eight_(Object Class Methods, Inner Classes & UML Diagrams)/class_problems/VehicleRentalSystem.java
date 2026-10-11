abstract class Vehicle {
    private String vehicleId;
    private boolean available = true;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public boolean isAvailable() {
        return available;
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);

    public void rent() {
        setAvailable(false);
    }

    public void returnVehicle() {
        setAvailable(true);
    }
}

class Sedan extends Vehicle {
    public Sedan(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    public SUV(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    public Truck(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public void startRental() {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getVehicleId() + " is currently unavailable.");
            return;
        }

        vehicle.rent();

        System.out.println(vehicle.getVehicleId()
                + " rented successfully by " + customer.getName());
        System.out.println("Rental charge: $"
                + vehicle.calculateCharge(days));
    }

    public void returnRental() {
        vehicle.returnVehicle();
        System.out.println(vehicle.getVehicleId()
                + " returned by " + customer.getName());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Rental rental1 = new Rental(sedan, customer1, 3);
        rental1.startRental();

        Rental rental2 = new Rental(sedan, customer2, 2);
        rental2.startRental();

        rental1.returnRental();

        Rental rental3 = new Rental(suv, customer3, 5);
        rental3.startRental();
    }
}