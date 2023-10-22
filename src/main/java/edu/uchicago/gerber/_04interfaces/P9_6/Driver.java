package edu.uchicago.gerber._04interfaces.P9_6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        List<Appointment> appointments = new ArrayList<>();
        Appointment a1 = new OneTime("Dentist", 2023, 10, 20);
        Appointment a2 = new Daily("Weight Lifting", 2023, 10, 22);
        Appointment a3 = new Monthly("Pay rent", 2023, 10, 1);
        appointments.add(a1);
        appointments.add(a2);
        appointments.add(a3);
        System.out.println(a1 + "\n" + a2 + "\n" + a3);

        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter valid 3 integers, each representing year, month, and date, eg 2023, 10, 20");
            int year = scan.nextInt();
            int month = scan.nextInt();
            int day = scan.nextInt();
            System.out.println("------Printing results-------");
            for (Appointment appointment : appointments) {
                if (appointment.occursOn(year, month, day)) {
                    System.out.println(appointment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}
