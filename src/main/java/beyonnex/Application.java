package beyonnex;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        AnagramChecker anagramChecker = new AnagramChecker();
        Scanner scanner = new Scanner(System.in);

        boolean exitRequested = false;
        while (!exitRequested) {
            System.out.println("Choose an option:");
            System.out.println("1. Check if two texts are anagrams");
            System.out.println("2. Get all previous anagrams for a given text");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the first text:");
                    String text1 = scanner.nextLine().toLowerCase();
                    System.out.println("Enter the second text:");
                    String text2 = scanner.nextLine().toLowerCase();
                    if (anagramChecker.isAnagram(text1, text2)) {
                        System.out.println("The texts are anagrams!");
                    } else {
                        System.out.println("The texts are not anagrams.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter a text to find its anagrams:");
                    String inputString = scanner.nextLine().toLowerCase();
                    List<String> anagrams = anagramChecker.getAnagrams(inputString);
                    System.out.println("Anagrams for " + inputString + ": " + anagrams);
                }
                case 3 -> {
                    System.out.println("Exiting program. Goodbye!");
                    exitRequested = true;
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}