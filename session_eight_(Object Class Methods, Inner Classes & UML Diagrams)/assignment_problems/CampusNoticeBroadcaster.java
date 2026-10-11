
import java.util.*;

public class CampusNoticeBroadcaster {

    interface NotificationChannel {
        void send(Student student, Notice notice);
    }

    static class EmailChannel implements NotificationChannel {
        public void send(Student student, Notice notice) {
            System.out.println("[Email → " + student.name +
                "] " + notice.title);
        }
    }

    static class SmsChannel implements NotificationChannel {
        public void send(Student student, Notice notice) {
            System.out.println("[SMS → " + student.name +
                "] " + notice.title);
        }
    }

    static class AppChannel implements NotificationChannel {
        public void send(Student student, Notice notice) {
            System.out.println("[App → " + student.name +
                "] " + notice.title);
        }
    }

    static class Student {
        private final String name;
        private final String department;
        private final List<NotificationChannel> channels =
            new ArrayList<>();

        Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        void addChannel(NotificationChannel channel) {
            channels.add(channel);
        }

        String getDepartment() {
            return department;
        }

        void receive(Notice notice) {
            for (NotificationChannel channel : channels) {
                channel.send(this, notice);
            }
        }
    }

    static class Notice {
        private final String title;
        private final Set<String> departments;

        Notice(String title, Set<String> departments) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Notice title is required.");
            }

            if (departments == null || departments.isEmpty()) {
                throw new IllegalArgumentException(
                    "At least one target department is required.");
            }

            this.title = title;
            this.departments = new HashSet<>(departments);
        }

        boolean targets(String department) {
            return departments.contains(department);
        }
    }

    static class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        void addStudent(Student student) {
            students.add(student);
        }

        void postNotice(String title, String... departments) {
            Set<String> targets = new HashSet<>();

            if (departments != null) {
                for (String department : departments) {
                    if (department != null &&
                        !department.trim().isEmpty()) {
                        targets.add(department);
                    }
                }
            }

            try {
                Notice notice = new Notice(title, targets);

                System.out.println("Notice '" + title +
                    "' posted to " +
                    String.join(", ", targets) + ".");

                for (Student student : students) {
                    if (notice.targets(student.getDepartment())) {
                        student.receive(notice);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot post notice: " +
                    e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();
        board.addStudent(asha);
        board.addStudent(ravi);

        board.postNotice("Lab Closed Tomorrow", "CSE");
        board.postNotice("Fee Deadline Extended", "CSE", "ECE");
        board.postNotice("Sports Day");
    }
}
