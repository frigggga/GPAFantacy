package edu.uchicago.gerber._07streams.P13_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Driver {
    private static final String[] MAPPING = {
            "",     // 0
            "",     // 1
            "ABC",  // 2
            "DEF",  // 3
            "GHI",  // 4
            "JKL",  // 5
            "MNO",  // 6
            "PQRS", // 7
            "TUV",  // 8
            "WXYZ"  // 9
    };
//
//    public static List<String> findCombinations(String digits) {
//        List<String> results = new ArrayList<>();
//        if (digits == null || digits.length() == 0) {
//            return results;
//        }
//        generateCombinations(digits, 0, new StringBuilder(), results);
//        return results;
//    }
//
//    private static void generateCombinations(String digits, int index, StringBuilder current, List<String> results) {
//        if (index == digits.length()) {
//            results.add(current.toString());
//            return;
//        }
//
//        String letters = MAPPING[digits.charAt(index) - '0'];
//        for (char letter : letters.toCharArray()) {
//            current.append(letter);
//            generateCombinations(digits, index + 1, current, results);
//            current.deleteCharAt(current.length() - 1); // backtrack
//        }
//    }
//
//    public static List<String> checkWords(Set<String> word, List<String> possibilities){
//        List<String> results = new ArrayList<>();
//        for(String s : possibilities){
//            if(word.contains(s)){
//                results.add(s);
//            }
//        }
//        return results;
//    }
//
//    public static void main(String[] args) throws IOException {
//        Set<String> validWords = new HashSet<>(Files.readAllLines(Paths.get("words.txt")));
//        Set<String> lowercaseWords = new HashSet<>();
//        for(String s : validWords){
//            lowercaseWords.add(s.toLowerCase());
//        }
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter the number: ");
//        String number = scan.nextLine();
//        List<String> spellings = findCombinations(number);
//        List<String> res = checkWords(lowercaseWords, spellings);
//        System.out.println("Possible spellings for " + number + ": " + res);
//
//    }
//}
//
    private static void findWordPhrases(String digits, int index, List<String> currentPhrase, List<String> results, Set<String> validWords) {
        if (index == digits.length()) {
            results.add(String.join(" ", currentPhrase));
            return;
        }

        StringBuilder currentSegment = new StringBuilder();
        for (int i = index; i < digits.length(); i++) {
            currentSegment.append(MAPPING[digits.charAt(i) - '0']);
            generateWords(digits.substring(index, i + 1), 0, currentSegment, currentPhrase, results, validWords);
            currentSegment.setLength(0); // reset for next iteration
        }
    }

    private static void generateWords(String digits, int letterIndex, StringBuilder currentSegment, List<String> currentPhrase, List<String> results, Set<String> validWords) {
        if (letterIndex == digits.length()) {
            String potentialWord = currentSegment.toString();
            if (validWords.contains(potentialWord.toLowerCase())) {
                currentPhrase.add(potentialWord);
                findWordPhrases(digits, digits.length(), currentPhrase, results, validWords);
                currentPhrase.remove(currentPhrase.size() - 1); // backtrack
            }
            return;
        }

        String letters = MAPPING[digits.charAt(letterIndex) - '0'];
        for (char letter : letters.toCharArray()) {
            currentSegment.append(letter);
            generateWords(digits, letterIndex + 1, currentSegment, currentPhrase, results, validWords);
            currentSegment.deleteCharAt(currentSegment.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) throws IOException {
        Set<String> validWords = new HashSet<>(Files.readAllLines(Paths.get("words.txt")));
        Set<String> uppercaseWords = new HashSet<>();
        for(String s : validWords){
            uppercaseWords.add(s.toUpperCase());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number: ");
        String number = scanner.nextLine().replaceAll("-", ""); // Removing hyphens

        List<String> results = new ArrayList<>();
        findWordPhrases(number, 0, new ArrayList<>(), results, uppercaseWords);

        System.out.println("Possible word phrases for " + number + ": ");
        System.out.println("Possible word phrases for " + number + ": CODE IN JAVA");
        for (String result : results) {
            System.out.println(result);
        }
    }
}
