package edu.uchicago.gerber._02arrays;

import java.util.Scanner;

public class P5_24 {

    public static int value(char letter){
        int r = 0;
        switch(letter){
            case 'I':   r = 1;
                        break;
            case 'V':   r = 5;
                        break;
            case 'X':   r = 10;
                        break;
            case 'L':   r = 50;
                        break;
            case 'C':   r = 100;
                        break;
            case 'D':   r = 500;
                        break;
            case 'M':   r = 1000;
                        break;

        }
        return r;
    }

    public static int calculate(String str){
        int total = 0;
        while(str.length() != 0){
            if(str.length() == 1 || value(str.charAt(0)) >= value(str.charAt(1))){
                total += value(str.charAt(0));
                str = str.substring(1);
            }else{
                int diff = value(str.charAt(1)) - value(str.charAt(0));
                total += diff;
                str = str.substring(2);
            }
        }
        return total;

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a valid roman numerals (eg. MCMLXXVIII) : ");
        String roman = scan.nextLine();
        scan.close();
        System.out.println("The decimal number is:" + calculate(roman));

    }
}
