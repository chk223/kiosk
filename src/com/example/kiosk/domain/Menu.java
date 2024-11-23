package com.example.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private final int number;
    private List<MenuItem> menuItems;

    public Menu(String name,int number) {
        this.name = name;
        this.menuItems = new ArrayList<>();
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }
    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }
    public MenuItem getMenuItemByIndex(int index) {
        return menuItems.get(index-1);
    }
}
