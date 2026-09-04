class Employee {
    String empId;
    double salary;

    // Resolving field/parameter clash using this
    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    // Resolving parameter clash using this
    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void printFinalSalary() {
        System.out.println(this.empId + " | Final Salary: Rs " + this.salary);
    }
}

public class PayrollBonusTest {
    public static void main(String[] args) {
        Employee[] team = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        double bonus = 5000;

        for (Employee emp : team) {
            emp.raiseSalary(bonus);
            emp.printFinalSalary();
        }
    }
}