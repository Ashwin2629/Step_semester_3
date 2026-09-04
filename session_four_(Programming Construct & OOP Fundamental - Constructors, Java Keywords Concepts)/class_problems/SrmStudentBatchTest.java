class SrmStudent {
    String name;
    static String collegeName;
    static String academicYear;

    // Static initialization block runs exactly once when class loads
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }
}

public class SrmStudentBatchTest {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        SrmStudent[] batch = new SrmStudent[names.length];
        for (int i = 0; i < names.length; i++) {
            batch[i] = new SrmStudent(names[i]);
        }
    }
}