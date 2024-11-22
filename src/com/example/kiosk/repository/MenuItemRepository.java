package com.example.kiosk.repository;

import com.example.kiosk.domain.Food;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    void save(MenuItem menuItem, Food foodKind);
    List<MenuItem> getBurger();
    List<MenuItem> getDrink();
    List<MenuItem> getDessert();
}
