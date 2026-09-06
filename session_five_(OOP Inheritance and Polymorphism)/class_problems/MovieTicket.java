public class MovieTicket {

    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId,
                       double ticketPrice, String movieTitle) {

        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public static void main(String[] args) {

        MovieTicket ticket = new MovieTicket(
            "A1",
            "Screen1",
            250.0,
            "Avatar"
        );

        System.out.println(ticket.seatNumber);
        System.out.println(ticket.screenId);
        System.out.println(ticket.ticketPrice);
        System.out.println(ticket.movieTitle);
    }
}