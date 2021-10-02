package com.techelevator;

public class Chip extends Product {

    public Chip(String name, double price, int quantity) {
        super(name, price, quantity);
    }



    @Override
    public void displayMessage() {
        System.out.println("Crunch Crunch, Yum!");
    }


}
