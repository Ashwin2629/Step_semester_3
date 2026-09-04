import java.util.Scanner;

public class PhoneMasker {

    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append(phone.substring(6));
        sb.insert(6, "-");

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 10-digit phone number: ");
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(maskPhoneNumber(input));
        }
        scanner.close();
    }
}