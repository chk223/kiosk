package com.example.kiosk.repository;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface MenuRepository {
    Menu findMenu(String name);
    void removeMenu(String name);
    List<Menu> getAllMenus();
    List<MenuItem> findMenuItems(String name);
    String getMenuNameByIndex(int index);
    MenuItem findMenuItemByIndex(String name, int index);
    void saveItem(Menu menu, MenuItem menuItem);
    void removeItem(Menu menu, String itemName);
    MenuItem getSpecificMenuItem(int menuIndex, int menuItemIndex);
    int countMenus();
    int countMenuItems(int menuIndex);
}
