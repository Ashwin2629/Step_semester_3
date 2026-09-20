public class MonthlyAttendanceAnnouncer {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void displayInfo() {
            System.out.print(
                    "Standard | Sessions: " + sessionsAttended
            );
        }
    }

    static class PremiumMember extends GymMember {

        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee,
                             String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        public void displayInfo() {
            System.out.print(
                    "Premium | Trainer: " + trainerName
                    + " | Sessions: " + sessionsAttended
            );
        }

        public String getTrainerName() {
            return trainerName;
        }
    }

    public static String batchPrint(GymMember[] members) {

        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo();

            StringBuilder current = new StringBuilder();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                current.append(" [Trainer via downcast: ")
                       .append(premium.getTrainerName())
                       .append("]");
            }

            StringBuilder temp = new StringBuilder();

            // Build the output without repeated String concatenation.
            if (member instanceof PremiumMember) {
                PremiumMember premium =
                        (PremiumMember) member;

                temp.append("Premium | Trainer: ")
                    .append(premium.getTrainerName())
                    .append(" | Sessions: ")
                    .append(premium.sessionsAttended)
                    .append(" [Trainer via downcast: ")
                    .append(premium.getTrainerName())
                    .append("]");
            } else {
                temp.append("Standard | Sessions: ")
                    .append(member.sessionsAttended);
            }

            result.append(temp).append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember[] members = {
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}