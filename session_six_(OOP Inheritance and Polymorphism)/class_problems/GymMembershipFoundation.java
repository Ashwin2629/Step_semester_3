public class GymMembershipFoundation {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty()
                    || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            if (monthlyFee <= 0) {
                throw new IllegalArgumentException("Monthly fee must be positive");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public void displayInfo() {
            System.out.println("Standard Member | Sessions: " + sessionsAttended);
        }

        public static String signUpBatch(String[] memberIds, int monthlyFee) {
            int signedUp = 0;
            int rejected = 0;

            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            return "Signed Up: " + signedUp + " | Rejected: " + rejected;
        }
    }

    static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        public void displayInfo() {
            System.out.println("Premium Member | Trainer: "
                    + trainerName + " | Sessions: " + sessionsAttended);
        }
    }

    public static void main(String[] args) {

        GymMember member = new GymMember("MEM1", 1000);
        member.attendSession();
        member.displayInfo();

        PremiumMember premium =
                new PremiumMember("MEM01", 2000, "Coach Riya");

        premium.attendSession();
        premium.attendSession();

        System.out.println(premium.getSessionsAttended());

        String[] ids = {"MEM1", "GM1", "MEM2", " ", "MEM3"};

        System.out.println(
                GymMember.signUpBatch(ids, 1000)
        );
    }
}
