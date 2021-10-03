package com.techelevator;

public abstract class Product {
    public int items_quantity;
    private String name ;
    private double price;

    public Product(String name, double price, int items_quantity) {
        this.name = name;
        this.price = price;
        this.items_quantity = items_quantity;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void  displayMessage();

    public void purchase(){
        items_quantity-=1;

    }
    public void setItems_quantity(){
        this.items_quantity = items_quantity;
    }

    public int getItems_quantity() {
        return items_quantity;
    }
}
