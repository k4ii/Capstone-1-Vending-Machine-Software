package com.techelevator;

public class Gum extends Product {


    public Gum(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public void displayMessage() {
        System.out.println("Chew Chew, Yum!");
    }


}
