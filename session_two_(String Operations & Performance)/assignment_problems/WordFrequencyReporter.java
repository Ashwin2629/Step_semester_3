import java.util.*;

public class WordFrequencyReporter {

    public static void printFilteredWordFrequency(String feedback) {
        // Define stop words set for fast lookup
        Set<String> stopWords = new HashSet<>(Arrays.asList(
            "the", "was", "and", "a", "is", "of", "in"
        ));

        // Normalize text: lowercase and strip punctuation (periods, commas)
        String cleaned = feedback.toLowerCase()
                                 .replace(".", "")
                                 .replace(",", "");

        // Split by whitespace
        String[] words = cleaned.trim().split("\\s+");

        // Count frequencies of words not in the stop words list
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Convert map entries to list and sort descending by count
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Display results
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter feedback paragraph: ");
        if (scanner.hasNextLine()) {
            String feedback = scanner.nextLine();
            printFilteredWordFrequency(feedback);
        }
        scanner.close();
    }
}