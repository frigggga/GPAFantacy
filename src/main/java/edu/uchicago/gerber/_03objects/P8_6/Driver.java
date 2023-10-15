package edu.uchicago.gerber._03objects.P8_6;

import java.text.DecimalFormat;

public class Driver {
    static DecimalFormat df = new DecimalFormat("####0.00");
    public static void main(String[] args) {
        Car c = new Car(25.0);

        c.addGas(10.0);
        System.out.println("Initial gas level: " + df.format(c.getGasLevel()));

        c.drive(100.0);

        c.drive(300.0);
        c.addGas(5.0);
        c.drive(150.0);

        System.out.println("Final gas level: " + df.format(c.getGasLevel()));
    }
}