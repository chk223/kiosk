package com.example.kiosk.repository;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MenuItemMemoryRepository implements MenuItemRepository {

    private static final Map<String, Menu> menuItemStorage = new ConcurrentHashMap<>();
    private static int storageNumber = 1;
    @Override
    public void addMenu(String name) {
        menuItemStorage.put(name, new Menu(name,storageNumber++));
    }
    @Override
    public void removeMenu(String name) {
        menuItemStorage.remove(name);
    }
    @Override
    public Menu findMenu(String name) {
        return menuItemStorage.get(name);
    }
    @Override
    public List<MenuItem> findMenuItems(String name) {
        return menuItemStorage.get(name).getMenuItems();
    }

    @Override
    public MenuItem findMenuItemByIndex(String name, int index) {
        return menuItemStorage.get(name).getMenuItemByIndex(index);
    }

    @Override
    public String getMenuNameByIndex(int index) {
        for (Menu menu : menuItemStorage.values()) {
            if(menu.getNumber() == index) {
                return menu.getName();
            }
        }
        return "";
    }

    @Override
    public List<Menu> getAllMenus() {
        return new ArrayList<>(menuItemStorage.values());
    }

    @Override
    public void saveItem(MenuItem menuItem, String name) {//??
        Menu menu = menuItemStorage.containsKey(name)? menuItemStorage.get(name):new Menu(name, storageNumber++);
        menu.addMenuItem(menuItem);
        menuItemStorage.put(name, menu);
    }
    @Override
    public void removeItem(MenuItem menuItem, String name) {
        Menu menu = menuItemStorage.getOrDefault(name,new Menu(name,storageNumber++));
        menu.removeMenuItem(menuItem);
    }
}
