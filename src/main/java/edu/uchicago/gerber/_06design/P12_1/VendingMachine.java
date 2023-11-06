package edu.uchicago.gerber._06design.P12_1;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private final Map<String, Product> products;
    private int insertedCoins;

    public VendingMachine() {
        this.products = new HashMap<>();
        this.insertedCoins = 0;
    }

    public void addProduct(String name, double price, int quantity) {
        products.put(name, new Product(name, price, quantity));
    }

    public void insertCoins(int amount) {
        this.insertedCoins += amount;
    }

    public String selectProduct(String name) {
        Product product = products.get(name);
        if (product != null && product.getQuantity() > 0) {
            if (insertedCoins >= product.getPrice()) {
                insertedCoins -= product.getPrice();
                product.setQuantity(product.getQuantity() - 1);
                return "Product dispensed: " + name + "\n";
            } else {
                int r = endTransaction();
                return "Insufficient money. An amount of " + r + "coins are returned\n";
            }
        } else {
            return "Product sold out or not found.\n";
        }
    }

    public int endTransaction() {
        int change = insertedCoins;
        insertedCoins = 0;
        return change; // All inserted coins are returned
    }

    public void restockProduct(String name, int quantity) {
        Product product = products.get(name);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
        }
    }


    public String displayProducts() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available products:\n");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            sb.append(entry.getKey())
                    .append(" - Price: ")
                    .append(entry.getValue().getPrice())
                    .append(" dollars, Quantity: ")
                    .append(entry.getValue().getQuantity())
                    .append("\n");
        }
        return sb.toString();
    }

}
