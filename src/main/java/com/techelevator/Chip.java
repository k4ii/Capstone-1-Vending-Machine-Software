package com.techelevator;

public class Chip extends Product {

    public Chip(String name, double price) {
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
