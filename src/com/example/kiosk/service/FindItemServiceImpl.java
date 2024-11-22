package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.MenuItemRepository;

import java.util.List;

public class FindItemServiceImpl implements FindItemService{
    private final MenuItemRepository itemRepository;

    public FindItemServiceImpl(MenuItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public MenuItem findBurger(int index) {
        List<MenuItem> burger = itemRepository.getBurger();
        return burger.get(index - 1);
    }

    @Override
    public MenuItem findDrink(int index) {
        List<MenuItem> drink = itemRepository.getDrink();
        return drink.get(index - 1);
    }

    @Override
    public MenuItem findDessert(int index) {
        List<MenuItem> dessert = itemRepository.getDessert();
        return dessert.get(index - 1);
    }
}
