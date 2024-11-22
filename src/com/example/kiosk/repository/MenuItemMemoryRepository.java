package com.example.kiosk.repository;

import com.example.kiosk.domain.Food;
import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemMemoryRepository implements MenuItemRepository {

    private static final Menu menuItemStorage = new Menu();

    @Override
    public void save(MenuItem menuItem, Food foodKind) {
        switch (foodKind) {
            case BURGER -> menuItemStorage.addBurger(menuItem);
            case DRINK -> menuItemStorage.addDrink(menuItem);
            case DESSERT -> menuItemStorage.addDessert(menuItem);
        }
    }

    @Override
    public List<MenuItem> getBurger() {
        return menuItemStorage.getBurger();
    }

    @Override
    public List<MenuItem> getDrink() {
        return menuItemStorage.getDrink();
    }

    @Override
    public List<MenuItem> getDessert() {
        return menuItemStorage.getDessert();
    }
}
