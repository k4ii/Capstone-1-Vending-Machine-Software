package com.techelevator;

public class Chips extends Product {

    public Chips(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public void displayMessage() {
        System.out.println("Crunch Crunch, Yum!");
    }

    @Override
    public String getItemType() {
        return "Chips";
    }
}
