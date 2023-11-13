package edu.uchicago.gerber._07streams.E19_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = new ArrayList<>();

        System.out.println("Enter words (type 'stop' to finish):");

        while (true) {
            String input = scanner.nextLine();
            if ("stop".equals(input)) {
                break;
            }
            words.add(input);
        }
        Stream<String> wordStream = words.stream();
        toString(wordStream);

    }
    public static void toString(Stream<String> stream){
        stream
            .filter(str -> str.length() > 1)
            .map(str -> str.charAt(0) + "..." + str.charAt(str.length() - 1))
            .forEach(System.out::println);
    }
}
