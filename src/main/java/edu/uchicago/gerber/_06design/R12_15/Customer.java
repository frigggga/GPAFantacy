package edu.uchicago.gerber._06design.R12_15;

public class Customer {
    private String name;
    private Address billingAddress;
    private Address shippingAddress;

    public Customer(String name, Address billingAddress, Address shippingAddress) {
        this.name = name;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }


}