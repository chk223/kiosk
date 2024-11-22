package com.example.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> burger;
    private final List<MenuItem> drink;
    private final List<MenuItem> dessert;

    public Menu() {
        burger = new ArrayList<>();
        drink = new ArrayList<>();
        dessert = new ArrayList<>();
    }

    public List<MenuItem> getDrink() {
        return drink;
    }

    public void addDrink(MenuItem menuItem) {
        drink.add(menuItem);
    }

    public void deleteDrink(MenuItem menuItem) {
        drink.remove(menuItem);
    }


    public List<MenuItem> getDessert() {
        return dessert;
    }

    public void addDessert(MenuItem menuItem) {
        dessert.add(menuItem);
    }

    public void deleteDessert(MenuItem menuItem) {
        dessert.remove(menuItem);
    }


    public List<MenuItem> getBurger() {
        return burger;
    }
    public void addBurger(MenuItem menuItem) {
        burger.add(menuItem);
    }

    public void deleteBurger(MenuItem menuItem) {
        burger.remove(menuItem);
    }

}
