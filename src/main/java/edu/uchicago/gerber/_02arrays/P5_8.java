package edu.uchicago.gerber._02arrays;

import java.util.Scanner;

public class P5_8 {

    public static boolean isLeapYear(int year){
        return (year % 4 == 0);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a valid year (eg. 2023) : ");
        String year = scan.nextLine();
        scan.close();
        if(isLeapYear(Integer.valueOf(year))){
            System.out.println("Year " + year + " is a leap year");
        }else{
            System.out.println("Year " + year + " is not a leap year");
        }
    }
}
