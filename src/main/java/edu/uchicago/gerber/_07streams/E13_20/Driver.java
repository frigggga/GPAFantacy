package edu.uchicago.gerber._07streams.E13_20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the digit: ");
        int price = scan.nextInt();
        findCombinations(price, new ArrayList<>(), 1);
    }
    public static void findCombinations(int price, List<Integer> combination, int startBill) {
        if (price == 0) {
            System.out.println(combination);
            return;
        }

        int[] billTypes = new int[]{100, 20, 5, 1};

        for (int billType : billTypes) {
            if (billType >= startBill && price >= billType) {
                combination.add(billType);
                findCombinations(price - billType, combination, billType);
                combination.remove(combination.size() - 1); // backtrack
            }
        }
    }

}
