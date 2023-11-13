package edu.uchicago.gerber._07streams.E19_5;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {
    public static <T> String toString(Stream<T> stream, int n){
        return stream
                .limit(n)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String intResult = toString(intStream, 6);
        System.out.println("First 5 elements of the int stream: " + intResult);

        // Example with a stream of strings
        Stream<String> stringStream = Stream.of("Apple", "Banana", "Canada", "Dallas", "Elephant", "Fiona", "Grape");
        String stringResult = toString(stringStream, 3);
        System.out.println("First 3 elements of the string stream: " + stringResult);
    }
}
