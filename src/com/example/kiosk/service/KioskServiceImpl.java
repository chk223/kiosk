package com.example.kiosk.service;

import com.example.kiosk.domain.Food;
import com.example.kiosk.domain.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class KioskServiceImpl implements KioskService{
    private final InitService initService;
    private final PrintService printService;
    private final FindItemService findItemService;
    private final InputService inputService;

    public KioskServiceImpl(InitService initService, PrintService printService, FindItemService findItemService, InputService inputService) {
        this.initService = initService;
        this.printService = printService;
        this.findItemService = findItemService;
        this.inputService = inputService;
    }

    @Override
    public void init() {
        initService.init();
    }

    @Override
    public int input() {
        return inputService.input();
    }

    @Override
    public void printMenu() {
        printService.printMenu();
    }

    @Override
    public void printMenuItems(Food foodKind) {
        try{
            printService.printMenuItems(foodKind);
            printService.lastSentence("뒤로가기");
            System.out.print("출력 된 번호를 선택하세요: ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean process(int number, Food foodKind) throws Exception {
        try {
            if(number == 0) return true;
            MenuItem item;
            switch (foodKind) {
                case BURGER -> item = findItemService.findBurger(number);
                case DRINK -> item = findItemService.findDrink(number);
                case DESSERT -> item = findItemService.findDessert(number);
                default -> throw new Exception("잘못된 음식 종류입니다.");
            }
            if(item == null) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                return false;
            }
            else{
                printService.printSelectedMenuItem(item);
                return true;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void printLastSentence(String action) {
        printService.lastSentence(action);
    }
}
