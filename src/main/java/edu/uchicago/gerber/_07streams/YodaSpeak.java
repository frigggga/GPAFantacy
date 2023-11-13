package edu.uchicago.gerber._07streams;

import java.util.Scanner;

public class YodaSpeak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        String[] words = sentence.split("\\s+");

        StringBuilder reversedSentence = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]);
            if (i > 0) {
                reversedSentence.append(" ");
            }
        }
        System.out.println("Reversed sentence: " + reversedSentence);
    }
}
