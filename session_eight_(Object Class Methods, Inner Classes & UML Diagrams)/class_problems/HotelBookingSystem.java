import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(long days) {
        return days * 2000;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(long days) {
        return days * 3500;
    }
}

class Suite extends Room {
    public Suite(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(long days) {
        return days * 5000;
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

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean cancelled = false;

    public Reservation(Customer customer,
                        Room room,
                        LocalDate startDate,
                        LocalDate endDate,
                        LocalDate cancellationDeadline) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return start.isBefore(endDate) && end.isAfter(startDate);
    }

    public void cancel(LocalDate today) {
        if (today.isBefore(cancellationDeadline)) {
            cancelled = true;
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation deadline has passed.");
        }
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getPrice() {
        long days = java.time.temporal.ChronoUnit.DAYS.between(
                startDate, endDate);

        return room.calculatePrice(days);
    }
}

public class HotelBookingSystem {

    static List<Reservation> reservations = new ArrayList<>();

    static boolean isAvailable(Room room,
                               LocalDate start,
                               LocalDate end) {

        for (Reservation reservation : reservations) {
            if (!reservation.isCancelled()
                    && reservation.getRoom() == room
                    && reservation.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Room room101 = new StandardRoom("101");
        Room room201 = new DeluxeRoom("201");

        Customer customerA = new Customer("Customer A");
        Customer customerC = new Customer("Customer C");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);

        if (isAvailable(room101, jan1, jan5)) {
            System.out.println("Standard Room 101 is available.");

            Reservation reservation =
                    new Reservation(
                            customerA,
                            room101,
                            jan1,
                            jan5,
                            LocalDate.of(2025, 12, 30));

            reservations.add(reservation);

            System.out.println("Reservation confirmed for Customer A.");
            System.out.println("Price: $" + reservation.getPrice());

            LocalDate jan3 = LocalDate.of(2026, 1, 3);
            LocalDate jan7 = LocalDate.of(2026, 1, 7);

            if (!isAvailable(room101, jan3, jan7)) {
                System.out.println(
                        "Standard Room 101 is not available from Jan 3 to Jan 7.");
            }

            reservation.cancel(LocalDate.of(2025, 12, 25));
        }

        LocalDate feb10 = LocalDate.of(2026, 2, 10);
        LocalDate feb12 = LocalDate.of(2026, 2, 12);

        if (isAvailable(room201, feb10, feb12)) {
            Reservation reservation2 =
                    new Reservation(
                            customerC,
                            room201,
                            feb10,
                            feb12,
                            LocalDate.of(2026, 2, 5));

            reservations.add(reservation2);

            System.out.println(
                    "Reservation confirmed for Customer C, Deluxe Room 201.");
            System.out.println("Price: $" + reservation2.getPrice());
        }
    }
}