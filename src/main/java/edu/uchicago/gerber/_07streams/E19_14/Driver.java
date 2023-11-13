package edu.uchicago.gerber._07streams.E19_14;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("I have already created one input file called 'E19_14.txt' inside the project root directory, feel free to use it");
        System.out.print("Enter the input file name: ");
        String fileName = scan.nextLine();

        try {
            List<String> words = Files.lines(Paths.get(fileName))
                    .flatMap(line -> List.of(line.split("\\s+")).stream())
                    .collect(Collectors.toList());

            Optional<String> palindrome = words.parallelStream()
                    .filter(word -> word.length() >= 5 && isPalindrome(word))
                    .findAny();

            palindrome.ifPresent(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPalindrome(String word) {
        String lowerCaseWord = word.toLowerCase();
        int len = lowerCaseWord.length();
        for (int i = 0; i < len / 2; i++) {
            if (lowerCaseWord.charAt(i) != lowerCaseWord.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}

