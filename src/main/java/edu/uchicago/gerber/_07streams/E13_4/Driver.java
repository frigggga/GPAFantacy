package edu.uchicago.gerber._07streams.E13_4;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the digit you want to convert to binary numbers: ");
        int digit = scan.nextInt();
        System.out.println(computeBinary(digit));
    }
    public static String computeBinary(int x){
        if(x == 1){
            return "1";
        }else if(x == 0){
            return "0";
        }else{
            if(x % 2 == 0){
                return computeBinary(x/2) + "0";
            }else{
                return computeBinary(x/2) + "1";
            }
        }
    }
}
