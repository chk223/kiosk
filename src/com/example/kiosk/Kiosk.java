package com.example.kiosk;
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
        kioskService.init();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            kioskService.printMenu();
            kioskService.printLastSentence("종료");
            try{
                System.out.print("출력 된 번호를 선택하세요: ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        kioskService.BurgerProcess(scanner);
                        break;
                    case 2:
                        kioskService.DrinksProcess(scanner);
                        break;
                    case 3:
                        kioskService.DessertProcess(scanner);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("입력이 잘못되었습니다.(1~3의 숫자를 입력해주세요.)");
                }
            } catch (InputMismatchException e) {
                System.out.println("입력이 잘못되었습니다. 1,2,3,0의 정수를 입력하세요.");
                scanner.nextLine();//입력 버퍼 비우기
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
