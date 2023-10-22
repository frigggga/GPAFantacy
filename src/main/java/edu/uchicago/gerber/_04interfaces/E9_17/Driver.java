package edu.uchicago.gerber._04interfaces.E9_17;

import java.text.DecimalFormat;

public class Driver {
    static DecimalFormat df = new DecimalFormat("####0.00");

    public static void main(String[] args) {
        SodaCan[] cans = {
                new SodaCan(3.0, 8.0),
                new SodaCan(2.0, 10.0),
                new SodaCan(4.0, 12.0)
        };
        System.out.println("Create a soda can with radius 3.0, height 8.0 and calculate its surface area and volume. ");
        System.out.println("Create a soda can with radius 2.0, height 10.0 and calculate its surface area and volume. ");
        System.out.println("Create a soda can with radius 4.0, height 12.0 and calculate its surface area and volume. ");
        System.out.println(java.time.LocalTime.now());
        double totalSurfaceArea = 0;
        for (SodaCan s : cans) {
            totalSurfaceArea += s.getMeasure();
        }

        double averageSurfaceArea = totalSurfaceArea / cans.length;

        System.out.println("\nAverage Surface Area of Soda Cans: " + df.format(averageSurfaceArea));
    }

}
