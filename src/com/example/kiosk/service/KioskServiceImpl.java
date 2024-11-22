package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;

import java.util.InputMismatchException;
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
        while(true) {
            printService.printBurgers(scanner);
            printService.lastSentence("뒤로가기");
            try{
                System.out.print("출력 된 번호를 선택하세요: ");
                int number = scanner.nextInt();
                if(number == 0) return;
                MenuItem burger = findItemService.findBurger(number);
                if(burger == null) {
                    System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                }
                else {
                    printService.printSelectedMenuItem(burger);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                scanner.nextLine();
            }  catch (Exception e){
                System.out.println(e.getMessage());
            }        }

    }

    @Override
    public void DrinksProcess(Scanner scanner) {
        while(true) {
            printService.printDrinks(scanner);
            printService.lastSentence("뒤로가기");
            try{
                System.out.print("출력 된 번호를 선택하세요: ");
                int number = scanner.nextInt();
                if(number == 0) return;
                MenuItem drink = findItemService.findDrink(number);
                if(drink == null) {
                    System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                }
                else {
                    printService.printSelectedMenuItem(drink);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                scanner.nextLine();
            }  catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void DessertProcess(Scanner scanner) {
        while(true) {
            printService.printDesserts(scanner);
            printService.lastSentence("뒤로가기");
            try{
                System.out.print("출력 된 번호를 선택하세요: ");
                int number = scanner.nextInt();
                if(number == 0) return;
                MenuItem dessert = findItemService.findDessert(number);
                if(dessert == null) {
                    System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                }
                else {
                    printService.printSelectedMenuItem(dessert);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("번호가 잘못 입력되었습니다. 출력된 번호 중 하나를 입력하세요.");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void printLastSentence(String action) {
        printService.lastSentence(action);
    }
}
