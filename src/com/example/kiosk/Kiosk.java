package com.example.kiosk;
import com.example.kiosk.domain.Food;
import com.example.kiosk.service.KioskService;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 프로그램 순서 및 흐름 제어 담당
 */
public class Kiosk {
    private final KioskService kioskService;

    public Kiosk(KioskService kioskService) {
        this.kioskService = kioskService;
    }

    public void start() {
        System.out.println(Food.findFoodByIndex(1));
        kioskService.init();
        while(true) {
            kioskService.printMenu();
            kioskService.printLastSentence("종료");
            int number = 0;
            try {
                number = kioskService.input();
            } catch (Exception e) {
                System.out.printf(e.getMessage());
            }
            if(number==0) return;
            Food foodKind = Food.findFoodByIndex(number);
            while(true) {
                try {
                    kioskService.printMenuItems(foodKind);
                    if(kioskService.process(kioskService.input(),foodKind)) break;
                } catch (Exception e) {
                    System.out.println("입력이 잘못되었습니다. 1,2,3,0의 정수를 입력하세요.");
                }
            }
        }
    }

    /**
     * 입력
     */

}
