package com.techelevator;

public class Chips extends Item{

    public Chips(String name, double price) {
        super(name, price);
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
