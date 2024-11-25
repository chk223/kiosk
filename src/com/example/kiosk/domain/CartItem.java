package com.example.kiosk.domain;

public class CartItem {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public CartItem(String name, double price, String description) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = 1;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity() {
        this.quantity += 1;
    }

    public void removeQuantity() {
        this.quantity -= 1;
    }
}
