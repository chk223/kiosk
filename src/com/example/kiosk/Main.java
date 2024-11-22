package com.example.kiosk;

import com.example.kiosk.repository.MenuItemMemoryRepository;
import com.example.kiosk.repository.MenuItemRepository;
import com.example.kiosk.service.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        MenuItemRepository menuItemRepository = new MenuItemMemoryRepository();
        PrintService printService = new PrintServiceImpl(menuItemRepository);
        InitService initService = new InitServiceImpl(menuItemRepository);
        FindItemService findItemService = new FindItemServiceImpl(menuItemRepository);
        KioskService kioskService = new KioskServiceImpl(initService,printService, findItemService);
        Kiosk kiosk = new Kiosk(kioskService);
        kiosk.start();
    }
}