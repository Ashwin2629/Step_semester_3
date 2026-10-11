enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public void approve() {
        if (status == LeaveStatus.PENDING) {
            status = LeaveStatus.APPROVED;
            System.out.println("Leave request for " + employee.getName()
                    + " from " + startDate + " to " + endDate + " approved.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Cannot approve. Current status: " + status);
        }
    }

    public void reject() {
        if (status == LeaveStatus.PENDING) {
            status = LeaveStatus.REJECTED;
            System.out.println("Leave request for " + employee.getName()
                    + " from " + startDate + " to " + endDate + " rejected.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Cannot reject. Current status: " + status);
        }
    }

    public void changeToPending() {
        System.out.println("Cannot change leave request status from "
                + status + " to Pending for " + employee.getName()
                + " from " + startDate + " to " + endDate + ".");
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest request1 =
                new LeaveRequest(john, "Jan 1", "Jan 5");

        System.out.println("Leave request submitted for John from "
                + request1.getStartDate() + " to " + request1.getEndDate() + ".");
        System.out.println("Status: " + request1.getStatus());

        request1.approve();

        LeaveRequest request2 =
                new LeaveRequest(jane, "Feb 10", "Feb 11");

        System.out.println("\nLeave request submitted for Jane from "
                + request2.getStartDate() + " to " + request2.getEndDate() + ".");
        System.out.println("Status: " + request2.getStatus());

        request2.reject();

        System.out.println();
        request1.changeToPending();
    }
}