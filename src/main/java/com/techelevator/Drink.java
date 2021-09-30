package com.techelevator;

public class Drink extends Product {
    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public void displayMessage() {
        System.out.println("Glug Glug, Yum!");
    }

    @Override
    public String getItemType() {
        return "Beverages";
    }
}
