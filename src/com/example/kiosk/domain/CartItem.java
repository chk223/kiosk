package com.example.kiosk.domain;

import com.example.kiosk.Util.Format;

public class CartItem {
    MenuItem menuItem;
    private int number;
    private int quantity;

    public CartItem(MenuItem menuItem, int number) {
        this.menuItem = menuItem;
        this.number = number;
        this.quantity = 1;
    }

    public int getNumber() {
        return number;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getName() {
        return menuItem.getItemName();
    }

    public double getPrice() {
        return menuItem.getPrice();
    }

    public String getDescription() {
        return menuItem.getDescription();
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

    public void displayCartItem() {
        Format.displayCartItem(number,menuItem.getItemName(),menuItem.getPrice(),menuItem.getDescription(),quantity);
    }
}
