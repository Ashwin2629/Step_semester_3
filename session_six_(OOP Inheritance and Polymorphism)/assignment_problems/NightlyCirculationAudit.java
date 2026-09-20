// File: NightlyCirculationAudit.java

class LibraryMember {
    private static int counter = 100;
    private static int membersEnrolled = 0;

    public final String memberNumber;
    protected int borrowLimit;
    private int booksBorrowed = 0;

    public LibraryMember(int borrowLimit) {
        counter++;
        membersEnrolled++;
        this.memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    public void borrowBook(String genre) {
        // Records the genre if needed, then reuses the base borrowBook()
        borrowBook();
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public static boolean isValidRenewalCode(String code) {
        // Format: "R" + 2 digits + 1 uppercase letter (exact length = 4)
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class NightlyCirculationAudit {

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof FacultyMember) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + facultyCount + " faculty | " + regularCount + " regular";
    }
}