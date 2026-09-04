import java.util.Scanner;

public class StudentRecordParser {

    public static void parseStudentRecord(String csvLine) {
        String[] parts = csvLine.split(",");

        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = parts[0].trim();
        String rollNo = parts[1].trim();
        String dept = parts[2].trim();

        System.out.println("Name: " + name + " | Roll No: " + rollNo + " | Dept: " + dept);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CSV record (Name, RollNumber, Department): ");
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            parseStudentRecord(input);
        }
        scanner.close();
    }
}