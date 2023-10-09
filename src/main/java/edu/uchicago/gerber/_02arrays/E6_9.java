package edu.uchicago.gerber._02arrays;

import java.util.Arrays;
import java.util.Scanner;

// Source: https://stackoverflow.com/questions/2795350/how-to-put-a-scanner-input-into-an-array-for-example-a-couple-of-numbers
// I learnt how to put a scanner input directly into an array, utilizing Arrays.stream()

public class E6_9 {
    public static boolean equals(int[] a, int[] b){
        if (a == null && b == null) {
            return true;
        }

        if ((a == null && b != null) || (a != null && b == null)) {
            return false;
        }

        if(a.length != b.length){
            return false;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first array, separate each value by space: ");
        int[] a = Arrays.stream(scan.nextLine()
                        .trim()
                        .split(" "))
                .filter(x -> !x.equals(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println("Enter the second array, separate each value by space: ");
        int[] b = Arrays.stream(scan.nextLine()
                        .trim()
                        .split(" "))
                .filter(x -> !x.equals(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        boolean result = equals(a, b);
        if(result){
            System.out.println("The two arrays are equal. ");
        }else{
            System.out.println("The two arrays are not equal. ");
        }
    }
}
