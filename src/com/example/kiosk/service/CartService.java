package com.example.kiosk.service;

import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.Grade;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface CartService {
    void addItemToCart(MenuItem menuItem);
    void subItemToCart(MenuItem menuItem) throws Exception;
    List<CartItem> getCartItems();
    double getTotalPrice();
    void clearCart();
}
