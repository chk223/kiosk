package com.example.kiosk.repository;

import com.example.kiosk.domain.CartItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartMemoryRepository implements CartRepository{

    private static final Map<String, CartItem> cartStorage = new HashMap<>();

    @Override
    public void add(CartItem cartItem) {
        if(cartStorage.containsKey(cartItem.getName())) {
            CartItem targetItem = cartStorage.get(cartItem.getName());
            targetItem.addQuantity();
            cartStorage.put(targetItem.getName(), targetItem);
        }
        else{
            cartStorage.put(cartItem.getName(), cartItem);
        }
    }

    @Override
    public void remove(String name) throws Exception {
        if(cartStorage.containsKey(name)) {
            CartItem cartItem = cartStorage.get(name);
            if(cartItem.getQuantity() > 1) {
                cartItem.removeQuantity();
                cartStorage.put(cartItem.getName(), cartItem);
            }
            else {
                cartStorage.remove(name);
            }
        }
        else {
            throw new Exception("장바구니에 해당 이름의 메뉴가 없음!");
        }
    }

    @Override
    public void removeAll() {
        for(CartItem cartItem : cartStorage.values()) {
            cartStorage.remove(cartItem.getName());
        }
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartStorage.values().stream().toList();
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for(CartItem cartItem: cartStorage.values()) {
            totalPrice += cartItem.getPrice();
        }
        return totalPrice;
    }
}
