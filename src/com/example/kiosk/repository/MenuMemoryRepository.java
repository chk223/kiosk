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
        if (menuStorage.containsKey(name)) return menuStorage.get(name);
        else {
            Menu menu = new Menu(name, storageNumber++);
            menuStorage.put(menu.getName(),menu);
            return menu;
        }
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

    @Override
    public void removeItem(Menu menu, MenuItem menuItem) {
        menu.removeMenuItem(menuItem);
        menuStorage.put(menu.getName(), menu);
    }
    @Override
    public int countMenus() {
        return menuStorage.values().size();
    }
    @Override
    public int countMenuItems(int menuIndex) {
        Menu menu = getMenuByIndex(menuIndex);
        return menu.getCountMenuItems();
    }

    private Menu getMenuByIndex(int menuIndex) {
        String name="";
        for (Menu menu : menuStorage.values()) {
            if(menu.getNumber() == menuIndex) {
                name = menu.getName();
            }
        }
        return menuStorage.get(name);
    }
    public void displaySpecificMenuItem(int menuIndex, int menuItemIndex) {
        Menu menu = getMenuByIndex(menuIndex);
        menu.displaySpecificMenuItem(menuItemIndex);
    }

    @Override
    public MenuItem getSpecificMenuItem(int menuIndex, int menuItemIndex) {
        Menu menu = getMenuByIndex(menuIndex);
        return menu.getMenuItemByIndex(menuItemIndex);
    }
}
