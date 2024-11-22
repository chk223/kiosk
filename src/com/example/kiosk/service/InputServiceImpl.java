package com.example.kiosk.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputServiceImpl implements InputService{
    @Override
    public int input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("출력 된 번호를 선택하세요: ");
        return scanner.nextInt();
    }
}
