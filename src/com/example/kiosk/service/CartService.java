package com.example.kiosk.service;

import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.Grade;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface CartService {
    void processOrder(int input);
    void addItemToCart(int menuIndex, int menuItemIndex);
    void removeItemToCart(MenuItem menuItem) throws Exception;
    Boolean isCartsEmpty();
}
