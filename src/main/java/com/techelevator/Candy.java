package com.techelevator;

public class Candy extends Product {

    public Candy(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public void displayMessage() {
        System.out.println("Munch Munch, Yum!");

    }


}
