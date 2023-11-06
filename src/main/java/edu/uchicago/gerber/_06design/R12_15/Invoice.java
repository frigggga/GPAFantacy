package edu.uchicago.gerber._06design.R12_15;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Customer customer;
    private List<Product> products;
    private double totalAmountDue;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalAmountDue = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        return;
    }

    public double getTotalAmountDue() {
        return 0.0;
    }


    // Method to generate invoice details
    public String generateInvoiceDetails() {
        return " ";
    }

}

