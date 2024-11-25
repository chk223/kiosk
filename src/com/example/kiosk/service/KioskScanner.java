package com.example.kiosk.service;

import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.Grade;
import com.example.kiosk.domain.MenuItem;

import java.util.List;
import java.util.Map;

/**
 * 출력을 담당하는 서비스
 */
public interface KioskScanner {

    String selectMenu() throws Exception;
    int selectMenuItem(String menuName) throws Exception;
    void displayOrderList(List<CartItem> cartItems, double totalPrice);
    MenuItem addItemToCart(String menuName, int selectOption) throws Exception;
    int processOrder() throws Exception;
    Grade getDiscountInfo(Map<Grade, Double> discountAmount, String discountMark, double price) throws Exception;
}
