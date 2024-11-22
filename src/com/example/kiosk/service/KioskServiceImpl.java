package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;

import java.util.Scanner;

public class KioskServiceImpl implements KioskService{
    private final InitService initService;
    private final PrintService printService;
    private final FindItemService findItemService;

    public KioskServiceImpl(InitService initService, PrintService printService, FindItemService findItemService) {
        this.initService = initService;
        this.printService = printService;
        this.findItemService = findItemService;
    }

    @Override
    public void init() {
        initService.init();
    }

    @Override
    public void printMenu() {
        printService.printMenu();
    }

    @Override
    public void BurgerProcess(Scanner scanner) {
        printService.printBurgers(scanner);
        printService.lastSentence("뒤로가기");
        try{
            int number = scanner.nextInt();
            if(number == 0) return;
            MenuItem burger = findItemService.findBurger(number);
            if(burger == null) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
            }
            else {
                printService.printSelectedMenuItem(burger);
            }
        } catch (Exception e){
            System.out.println("번호가 잘못되었습니다. 출력된 번호 중 선택해주세요."+e.getMessage());
        }
    }

    @Override
    public void DrinksProcess(Scanner scanner) {
        printService.printDrinks(scanner);
        printService.lastSentence("뒤로가기");
        try{
            int number = scanner.nextInt();
            if(number == 0) return;
            MenuItem drink = findItemService.findDrink(number);
            if(drink == null) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
            }
            else {
                printService.printSelectedMenuItem(drink);
            }
        } catch (Exception e){
            System.out.println("번호가 잘못되었습니다. 출력된 번호 중 선택해주세요."+e.getMessage());
        }
    }

    @Override
    public void DessertProcess(Scanner scanner) {
        printService.printDesserts(scanner);
        printService.lastSentence("뒤로가기");
        try{
            int number = scanner.nextInt();
            if(number == 0) return;
            MenuItem dessert = findItemService.findDessert(number);
            if(dessert == null) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
            }
            else {
                printService.printSelectedMenuItem(dessert);
            }
        } catch (Exception e){
            System.out.println("번호가 잘못되었습니다. 출력된 번호 중 선택해주세요."+e.getMessage());
        }
    }

    @Override
    public void printLastSentence(String action) {
        printService.lastSentence(action);
    }
}
