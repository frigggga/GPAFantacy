package edu.uchicago.gerber._06design.R12_13;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        // Pre-stock the vending machine
        machine.addProduct("Coke", 2.5, 10);
        machine.addProduct("Pepsi", 3.5, 5);
        machine.addProduct("Water", 1.5, 20);

        String input;
        Product temp;
        do {
            System.out.println("Welcome to the Vending Machine!");
            System.out.println(machine.displayProducts());
            System.out.println("[1] Select Product");
            System.out.println("[2] Restock Products");
            System.out.println("[3] Remove Money");
            System.out.println("[4] Add Product");
            System.out.println("[0] Exit");
            System.out.print("Please select an option: ");
            input = scanner.nextLine();

            switch (input) {
                case "1": // Select Product
                    System.out.print(machine.displayProducts());
                    System.out.print("Please type the product name you want.");
                    String name = scanner.nextLine();
                    System.out.print("Insert coins (in cents): ");
                    int coins = scanner.nextInt();
                    machine.insertCoins(coins);
                    System.out.println(machine.selectProduct(name));
                    scanner.nextLine(); // Consume newline left-over
                    break;
                case "2": // End Transaction
                    int change = machine.endTransaction();
                    System.out.println("Transaction ended. Returned " + change + " cents.");
                    break;
                case "3": // Restock Products
                    System.out.print("Enter product name to restock: ");
                    name = scanner.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int quantity = scanner.nextInt();
                    machine.restockProduct(name, quantity);
                    System.out.println("Product restocked.");
                    scanner.nextLine(); // Consume newline left-over
                    break;
                case "4": // Remove Money
                    int removedMoney = machine.endTransaction();
                    System.out.println(removedMoney + " cents removed from the machine.");
                    break;
                case "0": // Exit
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while (!input.equals("0"));

        scanner.close();
    }
}


