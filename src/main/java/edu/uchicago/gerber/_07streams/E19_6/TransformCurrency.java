package edu.uchicago.gerber._07streams.E19_6;

import java.util.Currency;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransformCurrency {
    public static void main(String[] args) {
        Stream<Currency> currencyStream = Currency.getAvailableCurrencies().stream();
        currencyStream
                .map(Currency::getDisplayName)
                .sorted()
                .forEach(System.out::println);
    }
}
