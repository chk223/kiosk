package com.example.kiosk.Util;

import com.example.kiosk.exception.InputException;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 정수 입력을 담당
     * @return 입력 된 정수 반환
     * @throws Exception 정수가 아닌 값 입력 시 예외 던짐
     */
    public static int input() throws Exception {
        try{
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new InputException();
        }
    }
}
