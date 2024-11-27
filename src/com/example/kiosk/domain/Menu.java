package com.example.kiosk.domain;

import com.example.kiosk.service.Util.Format;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Menu {
    private final String name;
    private final int number;
    private List<MenuItem> menuItems;

    public Menu(String name,int number) {
        this.name = name;
        this.menuItems = new ArrayList<>();
        this.number = number;
    }

    public void addMenuItem(String name, double price, String description) {
        MenuItem menuItem = new MenuItem(name,price,description);
        menuItems.add(menuItem);
    }

    public void removeMenuItem(int index) {
        MenuItem menuItem = getMenuItemByIndex(index);
        menuItems.remove(menuItem);
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }
    public void removeMenuItem(String itemName) {
        menuItems.removeIf(menuItem -> menuItem.getItemName().equals(itemName));
    }
    public MenuItem getMenuItemByIndex(int index) {
        return IntStream.range(0,menuItems.size()).filter(idx -> idx== index-1)
                .mapToObj(menuItems::get).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력된 인덱스에 해당하는 상품이 없음"));
    }

    public void displayMenuItems() {
        System.out.println("[ "+name.toUpperCase()+" MENU ]");
        AtomicInteger index = new AtomicInteger(1);
        menuItems.forEach(item -> {
            String blank = Format.blankFormat(item.getItemName());
            System.out.println(index.getAndIncrement()+". "+item.getItemName()+blank+"|  W "+item.getPrice()+" |  "+ item.getDescription());
        });
        Format.lastSentence("뒤로가기");
    }
    public int getCountMenuItems() {
        return menuItems.size();
    }
    public void displaySpecificMenuItem(int menuItemIndex) {
        MenuItem menuItem = getMenuItemByIndex(menuItemIndex);
        Format.displayMenuItem(menuItem.getItemName(),menuItem.getPrice(),menuItem.getDescription());
    }
}
