package com.techelevator;

public class Candy extends Product {

    public Candy(String name, double price) {
        super(name, price);
    }

    @Override
    public void displayMessage() {
        System.out.println("Munch Munch, Yum!");

    }

    @Override
    public String getItemType() {
        return "Candy";
    }
}
