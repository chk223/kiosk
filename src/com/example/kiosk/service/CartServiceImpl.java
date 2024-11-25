package com.example.kiosk.service;

import com.example.kiosk.service.discountService.DiscountService;
import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.CartRepository;

import java.util.List;

public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addItemToCart(MenuItem menuItem) {
        CartItem cartItem = new CartItem(menuItem.getItemName(), menuItem.getPrice(), menuItem.getDescription());
        cartRepository.add(cartItem);
    }

    @Override
    public void subItemToCart(MenuItem menuItem) throws Exception {
        cartRepository.remove(menuItem.getItemName());
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartRepository.getCartItems();
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0;
        List<CartItem> cartItems = cartRepository.getCartItems();
        for(CartItem cartItem : cartItems) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public void clearCart() {
        cartRepository.removeAll();
    }
}
