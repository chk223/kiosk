package com.example.kiosk;

import com.example.kiosk.service.discountService.DiscountService;
import com.example.kiosk.service.discountService.PercentageDiscountService;
import com.example.kiosk.repository.CartMemoryRepository;
import com.example.kiosk.repository.CartRepository;
import com.example.kiosk.repository.MenuMemoryRepository;
import com.example.kiosk.repository.MenuRepository;
import com.example.kiosk.service.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        //객체 생성
        MenuRepository menuRepository = new MenuMemoryRepository();
        CartRepository cartRepository = new CartMemoryRepository();
        DiscountService discountService = new PercentageDiscountService();
        CartService cartService = new CartServiceImpl(cartRepository,menuRepository,discountService);
        KioskInit kioskInit = new KioskInit(menuRepository);
        MenuManager menuManager = new MenuManager(menuRepository,cartRepository);
        Kiosk kiosk = new Kiosk(cartService,kioskInit,menuManager);
        kiosk.start();
    }
}