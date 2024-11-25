package com.example.kiosk;

import com.example.kiosk.repository.MenuMemoryRepository;
import com.example.kiosk.repository.MenuRepository;
import com.example.kiosk.service.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        //객체 생성
        MenuRepository menuRepository = new MenuMemoryRepository();
        KioskScanner kioskScanner = new KioskScannerImpl(menuRepository);
        KioskManager kioskManager = new KioskManagerImpl(kioskScanner, menuRepository);
        Kiosk kiosk = new Kiosk(kioskManager);
        kiosk.start();
    }
}