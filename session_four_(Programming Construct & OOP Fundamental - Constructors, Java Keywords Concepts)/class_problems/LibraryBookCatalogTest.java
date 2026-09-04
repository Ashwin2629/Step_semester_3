class LibraryBook {
    String title;
    String isbn;
    boolean catalogued;

    // Two-argument constructor
    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = (isbn == null || isbn.trim().isEmpty()) ? "PENDING" : isbn;
        this.catalogued = true;
    }

    // Chained single-argument constructor
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void printDetails() {
        System.out.println(title + " | " + isbn + " | Catalogued: " + catalogued);
    }
}

public class LibraryBookCatalogTest {
    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        LibraryBook[] batch = new LibraryBook[titles.length];

        for (int i = 0; i < titles.length; i++) {
            if (isbns[i] == null || isbns[i].trim().isEmpty()) {
                batch[i] = new LibraryBook(titles[i]);
            } else {
                batch[i] = new LibraryBook(titles[i], isbns[i]);
            }
            batch[i].printDetails();
        }
    }
}