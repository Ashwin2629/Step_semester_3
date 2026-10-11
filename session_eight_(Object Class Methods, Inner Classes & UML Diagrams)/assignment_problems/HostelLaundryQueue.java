
public class HostelLaundryQueue {

    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() { return 30; }
        public double getCharge() { return 20; }
        public String getName() { return "Quick"; }

        @Override
        public String toString() {
            return getName() + " wash";
        }
    }

    static class NormalWash implements WashType {
        public int getDuration() { return 45; }
        public double getCharge() { return 30; }
        public String getName() { return "Normal"; }

        @Override
        public String toString() {
            return getName() + " wash";
        }
    }

    static class HeavyWash implements WashType {
        public int getDuration() { return 60; }
        public double getCharge() { return 45; }
        public String getName() { return "Heavy"; }

        @Override
        public String toString() {
            return getName() + " wash";
        }
    }

    static class Student {
        private final String name;

        Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student student = (Student) obj;
            return name.equals(student.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    static class WashCycle {
        private final Student student;
        private final WashingMachine machine;
        private final WashType washType;

        WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public Student getStudent() {
            return student;
        }

        public WashingMachine getMachine() {
            return machine;
        }

        public WashType getWashType() {
            return washType;
        }

        @Override
        public String toString() {
            return student + " -> " + machine + " : " + washType;
        }
    }

    static class WashingMachine {
        private final String id;
        private WashCycle currentCycle;
        private final java.util.ArrayDeque<WashCycle> waitingQueue = new java.util.ArrayDeque<>();

        WashingMachine(String id) {
            this.id = id;
        }

        boolean isFree() {
            return currentCycle == null;
        }

        void startWash(Student student, WashType type) {
            if (isFree()) {
                currentCycle = new WashCycle(student, this, type);
                System.out.printf(
                    "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                    type.getName(), id, student.getName(),
                    type.getDuration(), type.getCharge()
                );
                return;
            }

            waitingQueue.offer(new WashCycle(student, this, type));
            System.out.printf(
                "%s has been added to the queue for %s. %s is currently busy.%n",
                student.getName(), id, id
            );
        }

        void completeWash() {
            if (isFree()) {
                if (!waitingQueue.isEmpty()) {
                    currentCycle = waitingQueue.poll();
                    System.out.printf(
                        "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                        currentCycle.getWashType().getName(), id,
                        currentCycle.getStudent().getName(),
                        currentCycle.getWashType().getDuration(),
                        currentCycle.getWashType().getCharge()
                    );
                    return;
                }
                System.out.println("Machine " + id + " is already free.");
                return;
            }

            System.out.println(id + " cycle completed for " + currentCycle.getStudent().getName() + ".");
            currentCycle = null;
            System.out.println(id + " is now free.");

            if (!waitingQueue.isEmpty()) {
                currentCycle = waitingQueue.poll();
                System.out.printf(
                    "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                    currentCycle.getWashType().getName(), id,
                    currentCycle.getStudent().getName(),
                    currentCycle.getWashType().getDuration(),
                    currentCycle.getWashType().getCharge()
                );
            }
        }

        public java.util.Queue<WashCycle> getWaitingQueue() {
            return java.util.Collections.unmodifiableCollection(waitingQueue) instanceof java.util.Queue
                ? (java.util.Queue<WashCycle>) java.util.Collections.unmodifiableCollection(waitingQueue)
                : waitingQueue;
        }

        @Override
        public String toString() {
            return id;
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student adil = new Student("Adil");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();
        m1.startWash(neha, new NormalWash());
        m2.startWash(adil, new QuickWash());
        m1.completeWash();
        m2.completeWash();
    }
}
