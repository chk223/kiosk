package com.example.kiosk.repository;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MenuMemoryRepository implements MenuRepository {

    private static final Map<String, Menu> menuStorage = new ConcurrentHashMap<>();
    private static int storageNumber = 1;
    @Override
    public Menu findMenu(String name) {
        return menuStorage.containsKey(name) ? menuStorage.get(name) : new Menu(name, storageNumber++);
    }
    @Override
    public void removeMenu(String name) {
        menuStorage.remove(name);
    }

    @Override
    public List<Menu> getAllMenus() {
        return new ArrayList<>(menuStorage.values());
    }

    @Override
    public List<MenuItem> findMenuItems(String name) {
        return menuStorage.get(name).getMenuItems();
    }

    @Override
    public String getMenuNameByIndex(int index) {
        for (Menu menu : menuStorage.values()) {
            if(menu.getNumber() == index) {
                return menu.getName();
            }
        }
        return "";
    }

    @Override
    public MenuItem findMenuItemByIndex(String name, int index) {
        return menuStorage.get(name).getMenuItemByIndex(index);
    }

    @Override
    public void saveItem(Menu menu, MenuItem menuItem) {
        menu.addMenuItem(menuItem);
        menuStorage.put(menu.getName(), menu);
    }

    public void removeItem(Menu menu, MenuItem menuItem) {
        menu.removeMenuItem(menuItem);
        menuStorage.put(menu.getName(), menu);
    }
}
