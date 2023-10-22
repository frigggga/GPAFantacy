package edu.uchicago.gerber._04interfaces.E9_11;

public class Driver {
    public static void main(String[] args) {
        Person person1 = new Person("Alice", 1985);
        Student student1 = new Student("Bob", 2001, "Computer Science");
        Instructor instructor1 = new Instructor("Prof. Charlie", 1960, 60000);

        Person person2 = new Person("Allison", 1999);
        Student student2 = new Student("Tim", 2002, "Data Science");
        Instructor instructor2 = new Instructor("Prof. Smith", 1970, 50000);

        Person person3 = new Person("Rose", 1989);
        Student student3 = new Student("Rock", 2000, "Statistics");
        Instructor instructor3 = new Instructor("Prof. Simpson", 1950, 99999);

        System.out.println(person1);
        System.out.println(student1);
        System.out.println(instructor1);

        System.out.println(person2);
        System.out.println(student2);
        System.out.println(instructor2);

        System.out.println(person3);
        System.out.println(student3);
        System.out.println(instructor3);
    }
}
