package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.MenuItemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintServiceImpl implements PrintService{
    private final MenuItemRepository menuItemRepository;

    public PrintServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }
    @Override
    public void printSelectedMenuItem(MenuItem menuItem) {
        System.out.println("선택한 메뉴: "+ menuItem.getItemName()+" | W "+menuItem.getPrice()+" | "+menuItem.getDescription());
        System.out.println();
    }

    @Override
    public void printMenu() {
        String[] mainMenu = {"Burgers", "Drinks", "Desserts"};
        System.out.println("[ MAIN MENU ]");
        AtomicInteger index = new AtomicInteger(1);
        Arrays.stream(mainMenu).forEach(menu ->{
            System.out.println(index.getAndIncrement()+" "+menu);
        });
    }

    @Override
    public void printBurgers(Scanner scanner) {
        System.out.println("[ BURGERS MENU ]");
        List<MenuItem> items = menuItemRepository.getBurger();
        AtomicInteger index = new AtomicInteger(1);
        items.forEach(item -> {
            String blank = blank_format(item.getItemName());
            System.out.println(index.getAndIncrement()+". "+item.getItemName()+blank+"|  W "+item.getPrice()+" |  "+ item.getDescription());
        });
    }
    @Override
    public void printDrinks(Scanner scanner) {
        System.out.println("[ DRINKS MENU ]");
        List<MenuItem> items = menuItemRepository.getDrink();
        AtomicInteger index = new AtomicInteger(1);
        items.forEach(item -> {
            String blank = blank_format(item.getItemName());
            System.out.println(index.getAndIncrement()+". "+item.getItemName()+blank+"|  W "+item.getPrice()+" |  "+ item.getDescription());
        });
    }
    @Override
    public void printDesserts(Scanner scanner) {
        System.out.println("[ DESSERTS MENU ]");
        List<MenuItem> items = menuItemRepository.getDessert();
        AtomicInteger index = new AtomicInteger(1);
        items.forEach(item -> {
            String blank = blank_format(item.getItemName());
            System.out.println(index.getAndIncrement()+". "+item.getItemName()+blank+"|  W "+item.getPrice()+" |  "+ item.getDescription());
        });
    }

    /**
     * 상품과 가격의 경계선을 일정하게 그어 주기 위한 공백설정
     * @param name 상품 이름
     * @return 공백 문자열
     */
    public String blank_format(String name) {
        int lengthOfName = 20 - name.length();
        return " ".repeat(Math.max(0, lengthOfName));
    }

    /**
     * 입력 전 상황에 따른 문구 출력(종료 또는 뒤로가기)
     * @param action 상황
     */
    @Override
    public void lastSentence(String action) {
        System.out.println("0.  "+ action);
    }
}
