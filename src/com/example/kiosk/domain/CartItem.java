package com.example.kiosk.domain;

import com.example.kiosk.service.Util.Format;

public class CartItem {
    MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.quantity = 1;
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
        Format.displayMenuItem(menuItem.getItemName(),menuItem.getPrice(),menuItem.getDescription());
    }
}
