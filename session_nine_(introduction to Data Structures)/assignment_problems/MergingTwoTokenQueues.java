
import java.util.ArrayList;

public class MergingTwoTokenQueues {
    public static ArrayList<Integer> mergeTokens(
            int[] counterA, int[] counterB) {

        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                result.add(counterA[i++]);
            } else {
                result.add(counterB[j++]);
            }
        }

        while (i < counterA.length) {
            result.add(counterA[i++]);
        }

        while (j < counterB.length) {
            result.add(counterB[j++]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};

        System.out.println(mergeTokens(counterA, counterB));
    }
}

