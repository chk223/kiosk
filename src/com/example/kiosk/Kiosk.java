package com.example.kiosk;
import com.example.kiosk.service.*;
import com.example.kiosk.service.Util.Format;
import com.example.kiosk.service.Util.VerifyInput;

/**
 * 프로그램 순서 및 흐름 제어 담당
 */
public class Kiosk {
    private final CartService cartService;
    private final KioskInit kioskInit;
    private final MenuManager menuManager;
    public Kiosk(CartService cartService, KioskInit kioskInit, MenuManager menuManager) {
        this.cartService = cartService;
        this.kioskInit = kioskInit;
        this.menuManager = menuManager;
    }
    private static final int MAX_ITERATIONS = 100000;
    public void start() {
        int remainIteration = MAX_ITERATIONS;
        kioskInit.init();
        while(remainIteration>0) {
            displayMenus();
            int extendedMenuRange = countTotalMenu();
            int input = VerifyInput.validateAndReturnInput(0, extendedMenuRange);
            if(input == 0) break;
            processMenuService(input, extendedMenuRange);
            remainIteration--;
        }
    }

    private void processMenuService(int menuNumber, int extendedMenuRange) {
        int originalMenuRange = menuManager.getMenuCount();
        if (menuNumber <= originalMenuRange) { //메뉴 선택
            menuManager.displayMenuItem(menuNumber);
            //메뉴 서비스 로직
        }
        else if(menuNumber <= extendedMenuRange) {
            cartService.processOrder(menuNumber);
        }
    }

    private void displayMenus() {
        menuManager.displayMenus();
        if(!cartService.isCartsEmpty()) {// 장바구니에 있으면.
            Format.displayOrderMenu(menuManager.getMenuCount());
        }
    }

    private int countTotalMenu() {
        int totalMenuCount =  menuManager.getMenuCount();
        if(!cartService.isCartsEmpty()) {// 장바구니에 있으면.
            totalMenuCount +=2;//장바구니 있으면 메뉴 개수 증가
        }
        return totalMenuCount;
    }

}
