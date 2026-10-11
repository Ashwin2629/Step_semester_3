
public class FitZoneMembershipDesk {

    interface MembershipPlan {
        int getMonths();
        double getDiscount();
        String getName();

        default double calculateFee() {
            return 1000 * getMonths() * (1 - getDiscount());
        }
    }

    static class MonthlyPlan implements MembershipPlan {
        public int getMonths() { return 1; }
        public double getDiscount() { return 0; }
        public String getName() { return "Monthly"; }
    }

    static class QuarterlyPlan implements MembershipPlan {
        public int getMonths() { return 3; }
        public double getDiscount() { return 0.10; }
        public String getName() { return "Quarterly"; }
    }

    static class AnnualPlan implements MembershipPlan {
        public int getMonths() { return 12; }
        public double getDiscount() { return 0.25; }
        public String getName() { return "Annual"; }
    }

    static class Member {
        private final String name;
        private Membership membership;

        Member(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        void buyMembership(MembershipPlan plan) {
            membership = new Membership(this, plan);

            System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getName(), name, plan.calculateFee(),
                membership.getStatus()
            );
        }

        void checkIn() {
            if (membership == null) {
                System.out.println("Check-in denied: no membership.");
                return;
            }

            if (membership.getStatus() == Status.ACTIVE) {
                System.out.println(name + " checked in successfully.");
            } else {
                System.out.println("Check-in denied: " + name +
                    "'s membership is " + membership.getStatus() + ".");
            }
        }

        void freeze() {
            if (membership != null) {
                membership.freeze();
            }
        }

        void unfreeze() {
            if (membership != null) {
                membership.unfreeze();
            }
        }

        void expire() {
            if (membership != null) {
                membership.expire();
            }
        }
    }

    enum Status {
        ACTIVE, FROZEN, EXPIRED
    }

    static class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private Status status = Status.ACTIVE;

        Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
        }

        MembershipPlan getPlan() {
            return plan;
        }

        Status getStatus() {
            return status;
        }

        void freeze() {
            if (status == Status.EXPIRED) {
                System.out.println(
                    "Cannot freeze an Expired membership.");
                return;
            }

            if (status != Status.ACTIVE) {
                System.out.println("Membership is already Frozen.");
                return;
            }

            status = Status.FROZEN;
            System.out.println(member.getName() +
                "'s " + plan.getName() + " membership frozen.");
            System.out.println("Status: " + status);
        }

        void unfreeze() {
            if (status == Status.EXPIRED) {
                System.out.println(
                    "Cannot unfreeze an Expired membership.");
                return;
            }

            if (status != Status.FROZEN) {
                System.out.println("Membership is not Frozen.");
                return;
            }

            status = Status.ACTIVE;
            System.out.println(member.getName() +
                "'s " + plan.getName() + " membership unfrozen.");
            System.out.println("Status: " + status);
        }

        void expire() {
            status = Status.EXPIRED;
            System.out.println(member.getName() +
                "'s " + plan.getName() + " membership expired.");
            System.out.println("Status: " + status);
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        asha.buyMembership(new QuarterlyPlan());
        ravi.buyMembership(new MonthlyPlan());

        asha.checkIn();
        asha.freeze();
        asha.checkIn();

        ravi.expire();
        ravi.freeze();
    }
}
