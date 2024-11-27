package com.example.kiosk.domain;

import com.example.kiosk.Util.Format;
import com.example.kiosk.exception.RepositoryException;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 메뉴의 순서 번호 반환
     * @return 순서 번호
     */
    public int getNumber() {
        return number;
    }

    /**
     * 메뉴 이름 반환
     * @return 메뉴 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 메뉴에 있는 MenuItem 리스트를 반환
     * @return MenuItem 리스트
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * 메뉴에 상품 추가
     * @param item 상품
     */
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    /**
     * 상품 이름으로 메뉴에서 상품 삭제
     * @param itemName 상품 이름
     */
    public void removeMenuItem(String itemName) {
        menuItems.removeIf(menuItem -> menuItem.getItemName().equals(itemName));
    }

    /**
     * 주어진 인덱스 번호에 해당하는 MenuItem 반환
     * @param index 주어진 인덱스 번호(사용자가 선택한)
     * @return 인덱스를 통해 찾은 MenuItem
     */
    public MenuItem getMenuItemByIndex(int index) {
        return IntStream.range(0,menuItems.size()).filter(idx -> idx== index-1)
                .mapToObj(menuItems::get).findFirst()
                .orElseThrow(() -> new RepositoryException(index));
    }

    /**
     * 해당 메뉴의 MenuItem 을 모두 출력
     */
    public void displayMenuItems() {
        System.out.println("[ "+name.toUpperCase()+" MENU ]");
        AtomicInteger index = new AtomicInteger(1);
        menuItems.forEach(item -> {
            String blank = Format.blankFormat(item.getItemName());
            System.out.println(index.getAndIncrement()+". "+item.getItemName()+blank+"|  W "+item.getPrice()+" |  "+ item.getDescription());
        });
        Format.lastSentence("뒤로가기");
    }

    /**
     * MenuItem 의 총 개수 반환
     * @return MenuItem 의 총 개수
     */
    public int getCountMenuItems() {
        return menuItems.size();
    }

    /**
     * 인덱스에 해당하는 MenuItem 출력
     * @param menuItemIndex MenuItem 의 인덱스(사용자가 선택한)
     */
    public void displaySpecificMenuItem(int menuItemIndex) {
        MenuItem menuItem = getMenuItemByIndex(menuItemIndex);
        Format.displayMenuItem(menuItem.getItemName(),menuItem.getPrice(),menuItem.getDescription());
    }
}
