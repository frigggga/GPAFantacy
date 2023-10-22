package edu.uchicago.gerber._04interfaces.E9_11;

public class Person {
    private String name;
    private int year;

    public Person(String name, int birthYear) {
        this.name = name;
        this.year = birthYear;
    }

    public String getName() {
        return this.name;
    }

    public int getBirthYear() {
        return this.year;
    }

    @Override
    public String toString() {
        return "Person Name: " + this.name + ", Birth Year: " + this.year;
    }
}

