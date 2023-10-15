package edu.uchicago.gerber._03objects.P8_1;

public class Driver {
    public static void main(String[] args) {
        System.out.println("create a microwave control panel and test it");
        Microwave m = new Microwave();

        System.out.println("\nTest 1: 30 seconds, power level 1");
        m.increaseTime();
        m.start();

        System.out.println("\nTest 2: 60 seconds, power level 2");
        m.increaseTime();
        m.increaseTime();
        m.switchPowerLevel();
        m.start();

        System.out.println("\nTest 3: reset power level to 1, cook for 90 seconds");
        m.reset();
        m.increaseTime();
        m.increaseTime();
        m.increaseTime();
        m.start();
    }
}
