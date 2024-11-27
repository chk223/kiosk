package com.example.kiosk.repository.cartRepository;

import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.exception.RepositoryException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CartMemoryRepository implements CartRepository{

    private static final Map<String, CartItem> cartStorage = new ConcurrentHashMap<>();
    private static int cartstorageNumber = 1;

    @Override
    public void add(MenuItem menuItem) {
        CartItem cartItem = cartStorage.get(menuItem.getItemName());
        if (cartItem != null) {
            cartItem.addQuantity(); // 기존 항목의 수량 증가
        } else {
            cartStorage.put(menuItem.getItemName(), new CartItem(menuItem, cartstorageNumber++)); // 새 항목 추가
        }
    }

    @Override
    public void remove(String name) throws RepositoryException {
        CartItem cartItem = cartStorage.get(name);
        if (cartItem == null) {
            throw new RepositoryException(name);
        }
        if (cartItem.getQuantity() > 1) {
            cartItem.removeQuantity(); // 수량 감소
        } else {
            cartStorage.remove(name); // 항목 제거
        }
    }

    @Override
    public void removeAll() {
        cartStorage.clear();
    }

    @Override
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartStorage.values());
    }

    @Override
    public double getTotalPrice() {
        return cartStorage.values().stream().mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity()).sum();
    }

    @Override
    public String getCartItemNameByIndex(int index) {
        CartItem item = cartStorage.values().stream().filter(cartItem -> cartItem.getNumber() == index).
                findFirst().orElseThrow(() -> new NoSuchElementException("해당 인덱스의 상품이 없음."));
        return item.getName();
    }
    @Override
    public List<Integer> getCartItemNumbers() {
        List<Integer> cartItemNumberList = new ArrayList<>();
        cartStorage.values().stream().forEach(cartItem -> {
            cartItemNumberList.add(cartItem.getNumber());
        });
        return cartItemNumberList;
    }
}
