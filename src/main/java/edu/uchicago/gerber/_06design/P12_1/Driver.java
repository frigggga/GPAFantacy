package edu.uchicago.gerber._06design.P12_1;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        // Pre-stock the vending machine
        machine.addProduct("Coke", 4, 10);
        machine.addProduct("Pepsi", 5, 5);
        machine.addProduct("Water", 2, 20);

        String input;
        Product temp;
        do {
            System.out.println("Welcome to the Vending Machine!");
            System.out.println(machine.displayProducts());
            System.out.println("[1] Select Product");
            System.out.println("[2] End Transaction");
            System.out.println("[3] Product Restock");
            System.out.println("[4] Remove Money");
            System.out.println("[0] Exit");
            System.out.print("Please select an option: ");
            input = scanner.nextLine();

            switch (input) {
                case "1": // Select Product
                    System.out.print(machine.displayProducts());
                    System.out.print("Please type the product name you want.");
                    String name = scanner.nextLine();
                    System.out.print("Insert coins (in dollars): ");
                    int coins = scanner.nextInt();
                    machine.insertCoins(coins);
                    System.out.println(machine.selectProduct(name));
                    scanner.nextLine(); // Consume newline left-over
                    break;
                case "2": // End Transaction
                    int change = machine.endTransaction();
                    System.out.println("Transaction ended. Returned " + change + " dollars.\n");
                    break;
                case "3": // Restock Products
                    System.out.print("Enter product name to restock: ");
                    name = scanner.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int quantity = scanner.nextInt();
                    machine.restockProduct(name, quantity);
                    System.out.println("Product restocked\n");
                    scanner.nextLine(); // Consume newline left-over
                    break;
                case "4": // Remove Money
                    int removedMoney = machine.endTransaction();
                    System.out.println(removedMoney + " dollars removed from the machine.\n");
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


