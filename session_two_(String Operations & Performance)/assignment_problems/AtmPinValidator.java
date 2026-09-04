import java.util.Scanner;

public class AtmPinValidator {

    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ATM PIN: ");
        if (scanner.hasNextLine()) {
            String pin = scanner.nextLine();
            checkPinLength(pin);
        }
        scanner.close();
    }
}