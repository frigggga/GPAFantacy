package edu.uchicago.gerber._03objects.P8_5;

public class Driver {

    public static void main(String[] args) {
        System.out.println("Create a soda can with radius 3.0, height 8.0 and calculate its surface area and volume. ");
        SodaCan s = new SodaCan(3.0, 8.0);
        double surfaceArea = s.getSurfaceArea();
        double volume = s.getVolume();
        System.out.println("Surface area is " + surfaceArea + " and volume is " + volume);

        System.out.println("\nCreate a soda can with radius 2.0, height 10.0 and calculate its surface area and volume. ");
        SodaCan s2 = new SodaCan(2.0, 10.0);
        surfaceArea = s2.getSurfaceArea();
        volume = s2.getVolume();
        System.out.println("Surface area is " + surfaceArea + " and volume is " + volume);

        System.out.println("\nCreate a soda can with radius 4.0, height 12.0 and calculate its surface area and volume. ");
        SodaCan s3 = new SodaCan(4.0, 12.0);
        surfaceArea = s3.getSurfaceArea();
        volume = s3.getVolume();
        System.out.println("Surface area is " + surfaceArea + " and volume is " + volume);

    }
}
