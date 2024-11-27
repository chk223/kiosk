package com.example.kiosk;
import com.example.kiosk.service.*;
import com.example.kiosk.Util.Format;
import com.example.kiosk.Util.VerifyInput;
import com.example.kiosk.service.cartService.CartService;

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
            int menuRange = decideMenuRange();
            int input = VerifyInput.validateRangeAndReturnInput(0, menuRange);
            if(input == 0) break;
            processMenuService(input, menuRange);
            remainIteration--;
        }
    }

    /**
     * 초기 메뉴에서 올바른 값 입력 시 장바구니에 담기 또는 결제,취소 로직 실행
     * @param menuNumber 선택 된 번호
     * @param menuRange 메뉴 범위(장바구니에 상품 존재 시 확장됨)
     */
    private void processMenuService(int menuNumber, int menuRange) {
        int originalMenuRange = menuManager.getMenuCount();
        if (menuNumber <= originalMenuRange) { //메뉴 선택
            menuManager.serviceShopping(menuNumber);
            //메뉴 서비스 로직
        }
        else if(menuNumber <= menuRange) {
            cartService.processOrder(menuNumber);
        }
    }

    /**
     * Main메뉴 출력
     */
    private void displayMenus() {
        menuManager.displayMenus();
        if(!cartService.isCartsEmpty()) {// 장바구니에 있으면.
            Format.displayOrderMenu(menuManager.getMenuCount());
        }
    }

    /**
     * 장바구니 여부에 따라 
     * 출력될 메뉴의 범위 반환
     * @return 출력될 메뉴의 범위
     */
    private int decideMenuRange() {
        int totalMenuCount =  menuManager.getMenuCount();
        if(!cartService.isCartsEmpty()) {// 장바구니에 있으면.
            totalMenuCount +=2;//장바구니 있으면 메뉴 개수 증가
        }
        return totalMenuCount;
    }

}
