package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Product {
    private String name;
    //String slot;
    private int quantity;
    private double price;
    private String itemType;

    public Product(String name, double price, int startStock) {
        this.name = name;
        this.price = price;
        this.quantity = startStock;
    }



    public int getQuantity() {
        return quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public String getName(){

        return name;
    }

    public double getPrice(){

        return  price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return this.quantity >= 1 ? true : false;
    }
    public abstract void displayMessage();
    public void purchaseItem() {
        this.quantity--;
    }
}
