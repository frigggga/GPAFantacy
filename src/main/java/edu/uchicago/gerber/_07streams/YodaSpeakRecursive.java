package edu.uchicago.gerber._07streams;

import java.util.Scanner;

public class YodaSpeakRecursive {
    private static String reverseWords(String sentence) {
        int spaceIndex = sentence.indexOf(" ");

        if (spaceIndex == -1) {
            return sentence;
        }

        String firstWord = sentence.substring(0, spaceIndex);
        String restOfSentence = sentence.substring(spaceIndex + 1);
        return reverseWords(restOfSentence) + " " + firstWord;
    }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a sentence:");
            String sentence = scanner.nextLine();
            String reversedSentence = reverseWords(sentence);
            System.out.println("Reversed sentence: " + reversedSentence);

    }
}
