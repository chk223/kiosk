package com.example.kiosk;
import com.example.kiosk.service.KioskManager;

/**
 * 프로그램 순서 및 흐름 제어 담당
 */
public class Kiosk {
    private final KioskManager kioskManager;
    public Kiosk(KioskManager kioskManager) {
        this.kioskManager = kioskManager;
    }
    public void start() {
        int maximum_count = 1000000;
        kioskManager.init();
        while(maximum_count>0) {
            kioskManager.printMenu();
            kioskManager.printLastSentence("종료");
            int number = 0;
            String name = "";
            try {
                System.out.println("첫 번째 입력 받을 차례");
                number = kioskManager.input();
                if(number == 0) break;
                name = kioskManager.printMenuItems(number);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(!name.isEmpty())
            {
                while(maximum_count>0) {
                    try{
                        System.out.println("두 번째 입력 받을 차례");
                        number = kioskManager.input();
                        if(number == 0) break;
                        kioskManager.process(name,number);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    maximum_count --;
                }
            }
            maximum_count -= 1;
        }
    }

}
