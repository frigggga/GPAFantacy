package edu.uchicago.gerber._04interfaces.P9_1;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Please make sure you are using a 24-hour time window\n ");

        System.out.println("Created three clocks, the first one is a local clock and the later two are world clocks with an offset of 3 and -3.\n ");

        Clock c1 = new Clock();
        System.out.println(c1.getTime());

        Clock c2 = new WorldClock(3);
        System.out.println(c2.getTime());

        Clock c3 = new WorldClock(-3);
        System.out.println(c3.getTime());
    }
}
