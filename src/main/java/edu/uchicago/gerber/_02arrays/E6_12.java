package edu.uchicago.gerber._02arrays;
import java.util.*;


public class E6_12 {
    public static void main(String[] args){
        int[] randomInt = new int[20];
        System.out.println("Initial integers: ");
        for(int i = 0; i < 20; i++){
            int n = (int)(Math.random()*100);
            randomInt[i] = n;
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.println("Sorted Array: ");
        Arrays.sort(randomInt);
        for(int i = 0; i < 20; i ++){
            System.out.print(randomInt[i] + " ");
        }

    }
}
