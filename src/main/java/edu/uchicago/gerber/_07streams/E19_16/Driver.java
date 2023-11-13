package edu.uchicago.gerber._07streams.E19_16;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("I have already created one input file called 'E19_16.txt' inside the project root directory, feel free to use it");
        System.out.print("Enter the input file name: ");
        String fileName = scan.nextLine();

        try {
            Map<Character, Double> averageWordLengths = Files.lines(Paths.get(fileName))
                    .flatMap(line -> Pattern.compile("\\s+").splitAsStream(line)) // Split line into words
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(
                            word -> Character.toLowerCase(word.charAt(0)),
                            Collectors.averagingInt(String::length)
                    ));

            averageWordLengths.forEach((letter, avgLength) ->
                    System.out.println(letter + ": " + avgLength));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
