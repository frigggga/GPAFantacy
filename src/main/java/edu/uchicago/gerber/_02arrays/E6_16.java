package edu.uchicago.gerber._02arrays;

import java.util.Arrays;
import java.util.Scanner;

public class E6_16 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int maxValue = 0;
        System.out.println("Enter a sequence of positive integers, separate each value by space: ");
        int[] data = Arrays.stream(scan.nextLine()
                        .trim()
                        .split(" "))
                .filter(x -> !x.equals(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        for(int i = 0; i < data.length; i++){
            if(data[i] > maxValue){
                maxValue = data[i];
            }
        }

        for(int i = 0; i < data.length; i++){
            data[i] = data[i] * 20 / maxValue; //weighted value
        }

        System.out.println("Bar Chart: ");
        for (int r = 20; r >= 1; r--) {
            for(int c = 0; c < data.length; c++){
                if(data[c] - r >= 0){
                    System.out.print('*' + " ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        scan.close();
    }

}
