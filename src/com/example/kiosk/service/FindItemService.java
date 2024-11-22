package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;

public interface FindItemService {
    MenuItem findBurger(int index);
    MenuItem findDrink(int index);
    MenuItem findDessert(int index);
}
