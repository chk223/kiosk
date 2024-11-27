package com.example.kiosk;

import com.example.kiosk.service.cartService.CartService;
import com.example.kiosk.service.cartService.CartServiceImpl;
import com.example.kiosk.service.discountService.DiscountService;
import com.example.kiosk.service.discountService.PercentageDiscountService;
import com.example.kiosk.repository.cartRepository.CartMemoryRepository;
import com.example.kiosk.repository.cartRepository.CartRepository;
import com.example.kiosk.repository.menuRepository.MenuMemoryRepository;
import com.example.kiosk.repository.menuRepository.MenuRepository;
import com.example.kiosk.service.*;

public class Main {
    public static void main(String[] args) {
        //객체 생성 및 의존성 주입
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