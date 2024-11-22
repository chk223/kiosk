package com.example.kiosk.domain;

public class MenuItem {
    private String itemName;
    private double price;
    private String description;

    public MenuItem(String itemName, double price, String description) {
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
