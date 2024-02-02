package beyonnex;

import java.util.*;

public class AnagramChecker {

    private final Set<String> inputTexts = new HashSet<>();

    boolean isAnagram(String subject, String candidate) {
        inputTexts.add(subject);
        inputTexts.add(candidate);

        char[] subjectChars = standardise(subject).toCharArray();
        char[] candidateChars = standardise(candidate).toCharArray();

        Arrays.sort(subjectChars);
        Arrays.sort(candidateChars);

        return Arrays.equals(subjectChars, candidateChars);
    }

    static String standardise(String input) {
        return input
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();
    }

    List<String> getAnagrams(String subject) {
        return inputTexts.stream()
                .filter((inputText) -> !subject.equals(inputText))
                .filter((inputText) -> isAnagram(subject, inputText))
                .toList();
    }
}