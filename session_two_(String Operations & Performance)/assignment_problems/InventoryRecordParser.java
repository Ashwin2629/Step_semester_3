import java.util.Scanner;

public class InventoryRecordParser {

    public static void parseInventoryRecord(String csvLine) {
        String[] parts = csvLine.split(",");

        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String product = parts[0].trim();
        String sku = parts[1].trim();
        String qty = parts[2].trim();

        System.out.println("Product: " + product + " | SKU: " + sku + " | Qty: " + qty);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CSV line (Product, SKU, Quantity): ");
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parseInventoryRecord(line);
        }
        scanner.close();
    }
}