// File: WeeklyCheckInSettlement.java

class GymMember {
    private static int counter = 2000;
    private static int membersEnrolled = 0;

    public final String membershipNumber;
    private int feesPaid = 0;

    public GymMember(int monthlyFee) {
        counter++;
        membersEnrolled++;
        this.membershipNumber = "GYM-" + counter;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        // Mode can be logged/recorded here, then delegates to 1-argument overload
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static boolean isValidReferralCode(String code) {
        // Format: "G" + two digits + one uppercase letter (length: 4)
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}

public class WeeklyCheckInSettlement {

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof GroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + groupCount + " group | " + individualCount + " individual";
    }
}