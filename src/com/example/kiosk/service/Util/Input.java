package com.example.kiosk.service.Util;

import java.util.Scanner;

public class Input {
    public static int input() throws Exception {
        Scanner scanner = new Scanner(System.in);
        try{
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new Exception("입력 값이 잘못됨!");
        }
    }
}
