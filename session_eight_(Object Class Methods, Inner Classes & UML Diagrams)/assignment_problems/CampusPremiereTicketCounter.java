
import java.util.*;

public class CampusPremiereTicketCounter {

    abstract static class Seat {
        private final String id;

        Seat(String id) {
            this.id = id;
        }

        String getId() { return id; }
        abstract double getPrice();
    }

    static class RegularSeat extends Seat {
        RegularSeat(String id) { super(id); }
        double getPrice() { return 150; }
    }

    static class PremiumSeat extends Seat {
        PremiumSeat(String id) { super(id); }
        double getPrice() { return 250; }
    }

    static class ReclinerSeat extends Seat {
        ReclinerSeat(String id) { super(id); }
        double getPrice() { return 400; }
    }

    static class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Show {
        private final String name;
        private boolean started = false;
        private final Map<String, Seat> bookedSeats = new HashMap<>();

        Show(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        boolean isStarted() {
            return started;
        }

        void startShow() {
            started = true;
        }

        boolean areAvailable(List<Seat> seats) {
            Set<String> requested = new HashSet<>();

            for (Seat seat : seats) {
                if (seat == null ||
                    bookedSeats.containsKey(seat.getId()) ||
                    !requested.add(seat.getId())) {
                    return false;
                }
            }
            return true;
        }

        void reserve(List<Seat> seats) {
            for (Seat seat : seats) {
                bookedSeats.put(seat.getId(), seat);
            }
        }

        void release(List<Seat> seats) {
            for (Seat seat : seats) {
                bookedSeats.remove(seat.getId());
            }
        }

        boolean isBooked(String seatId) {
            return bookedSeats.containsKey(seatId);
        }
    }

    static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private final double total;
        private boolean cancelled = false;

        Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
            total = seats.stream().mapToDouble(Seat::getPrice).sum();
        }

        void cancel() {
            if (cancelled) {
                System.out.println("Booking already cancelled.");
                return;
            }

            if (show.isStarted()) {
                System.out.println("Cannot cancel: the show has started.");
                return;
            }

            show.release(seats);
            cancelled = true;

            System.out.println(customer.name + "'s booking cancelled.");
            System.out.println("Seats " + seatList() + " released.");
        }

        String seatList() {
            List<String> ids = new ArrayList<>();
            for (Seat seat : seats) {
                ids.add(seat.getId());
            }
            return String.join(", ", ids);
        }

        void confirm() {
            System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer.name, seatList(), total);
        }
    }

    static Booking book(Customer customer, Show show, Seat... seats) {
        List<Seat> requested = Arrays.asList(seats);

        if (requested.isEmpty() || requested.size() > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        for (Seat seat : requested) {
            if (seat == null) {
                System.out.println("Invalid seat.");
                return null;
            }

            if (show.isBooked(seat.getId())) {
                System.out.println("Seat " + seat.getId() +
                    " is already booked for this show.");
                return null;
            }
        }

        if (!show.areAvailable(requested)) {
            System.out.println("Invalid or duplicate seat selection.");
            return null;
        }

        if (show.isStarted()) {
            System.out.println("Cannot book: the show has started.");
            return null;
        }

        show.reserve(requested);
        Booking booking = new Booking(customer, show, requested);
        booking.confirm();
        return booking;
    }

    public static void main(String[] args) {
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Booking ashaBooking = book(asha, show,
            new RegularSeat("A1"),
            new RegularSeat("A2"),
            new PremiumSeat("F5"));

        book(ravi, show, new RegularSeat("A2"));
        book(ravi, show, new ReclinerSeat("R1"));

        if (ashaBooking != null) {
            ashaBooking.cancel();
        }

        book(neha, show, new RegularSeat("A2"));
    }
}
