package com.techelevator;

public class Gum extends Product {


    public Gum(String name, double price) {
        super(name, price);
    }

    @Override
    public void displayMessage() {
        System.out.println("Chew Chew, Yum!");
    }

    @Override
    public String getItemType() {
        return "Gum";
    }
}
