package com.example.kiosk.repository;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    public void addMenu(String name);
    public void removeMenu(String name);
    public Menu findMenu(String name);
    public List<MenuItem> findMenuItems(String name);
    public MenuItem findMenuItemByIndex(String name, int index);
    public String getMenuNameByIndex(int index);
    public List<Menu> getAllMenus();
    public void saveItem(MenuItem menuItem, String name);
    public void removeItem(MenuItem menuItem, String name);
}
