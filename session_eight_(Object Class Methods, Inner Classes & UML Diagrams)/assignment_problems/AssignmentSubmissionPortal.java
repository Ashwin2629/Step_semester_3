
import java.time.LocalDate;

public class AssignmentSubmissionPortal {

    interface Assignment {
        String getTitle();
        int getMaxMarks();
        LocalDate getDueDate();
        double getPenaltyPerDay();

        default double applyPenalty(double marks, long lateDays) {
            double penalty = lateDays * getPenaltyPerDay();
            return Math.max(0, marks * (1 - penalty));
        }
    }

    static class CodingAssignment implements Assignment {
        private final String title;
        private final int maxMarks;
        private final LocalDate dueDate;

        CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        public String getTitle() { return title; }
        public int getMaxMarks() { return maxMarks; }
        public LocalDate getDueDate() { return dueDate; }
        public double getPenaltyPerDay() { return 0.10; }
    }

    static class WrittenAssignment implements Assignment {
        private final String title;
        private final int maxMarks;
        private final LocalDate dueDate;

        WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        public String getTitle() { return title; }
        public int getMaxMarks() { return maxMarks; }
        public LocalDate getDueDate() { return dueDate; }
        public double getPenaltyPerDay() { return 0.20; }
    }

    static class Student {
        String name;

        Student(String name) {
            this.name = name;
        }
    }

    enum Status {
        SUBMITTED, GRADED
    }

    static class Submission {
        private final Student student;
        private final Assignment assignment;
        private LocalDate submissionDate;
        private Status status = Status.SUBMITTED;
        private double finalMarks;

        Submission(Student student, Assignment assignment,
                   LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
        }

        void resubmit(LocalDate newDate) {
            if (status == Status.GRADED) {
                System.out.println("Cannot resubmit: '" +
                    assignment.getTitle() + "' has already been graded.");
                return;
            }

            submissionDate = newDate;
            System.out.println(student.name + " resubmitted '" +
                assignment.getTitle() + "'.");
        }

        void grade(double awardedMarks) {
            if (status == Status.GRADED) {
                System.out.println("Submission has already been graded.");
                return;
            }

            if (awardedMarks < 0 ||
                awardedMarks > assignment.getMaxMarks()) {
                System.out.println("Invalid marks.");
                return;
            }

            long lateDays = Math.max(0,
                java.time.temporal.ChronoUnit.DAYS.between(
                    assignment.getDueDate(), submissionDate));

            finalMarks = assignment.applyPenalty(awardedMarks, lateDays);
            status = Status.GRADED;

            if (lateDays == 0) {
                System.out.printf("%s graded: %.0f/%d.%n",
                    student.name, finalMarks, assignment.getMaxMarks());
            } else {
                System.out.printf(
                    "%s graded: %.0f/%d after %.0f%% late penalty.%n",
                    student.name, finalMarks, assignment.getMaxMarks(),
                    lateDays * assignment.getPenaltyPerDay() * 100);
            }

            System.out.println("Status: " + status);
        }

        void printSubmission() {
            long lateDays = Math.max(0,
                java.time.temporal.ChronoUnit.DAYS.between(
                    assignment.getDueDate(), submissionDate));

            if (lateDays == 0) {
                System.out.println(student.name + "'s submission for '" +
                    assignment.getTitle() + "' received (on time).");
            } else {
                System.out.println(student.name + "'s submission for '" +
                    assignment.getTitle() + "' received (" +
                    lateDays + " days late).");
            }

            System.out.println("Status: " + status);
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
            "Linked List Lab", 50, LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
            "Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(
            asha, coding, LocalDate.of(2026, 3, 10));

        Submission s2 = new Submission(
            ravi, written, LocalDate.of(2026, 3, 14));

        s1.printSubmission();
        s2.printSubmission();

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(LocalDate.of(2026, 3, 11));
    }
}
