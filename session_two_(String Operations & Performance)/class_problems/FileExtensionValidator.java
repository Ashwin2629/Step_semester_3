import java.util.Scanner;

public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex != -1 && dotIndex < filename.length() - 1) {
            String ext = filename.substring(dotIndex + 1);

            if (ext.equalsIgnoreCase("pdf") || 
                ext.equalsIgnoreCase("docx") || 
                ext.equalsIgnoreCase("zip")) {
                return "Accepted";
            }
        }

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename with extension: ");
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(validateFileExtension(input));
        }
        scanner.close();
    }
}