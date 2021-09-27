package com.techelevator;

public class Beverages extends Item {
    public Beverages(String name, double price) {
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
