class Employee {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class CompanyStaticTest {
    public static void main(String[] args) {
        new Employee("Aarav", 45000);
        new Employee("Sneha", 55000);
        new Employee("Vikram", 60000);

        Employee.printCompanyInfo();
    }
}