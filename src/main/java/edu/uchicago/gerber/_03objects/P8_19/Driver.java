package edu.uchicago.gerber._03objects.P8_19;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new java.util.Scanner(System.in);

        System.out.print("Enter the starting angle (degrees): ");
        double angle = scan.nextDouble();

        System.out.print("Enter the initial velocity (m/s): ");
        double velocity = scan.nextDouble();

        Cannonball c = new Cannonball(0.0);
        System.out.print("The initial position of x is: 0.0. ");
        c.shoot(angle, velocity);

        scan.close();
    }
}
