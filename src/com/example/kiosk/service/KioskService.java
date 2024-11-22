package com.example.kiosk.service;

import java.util.Scanner;

/**
 * 각 서비스 로직을 전부 가져와서 제공하는 통합 서비스
 */
public interface KioskService {
    void init();
    void printMenu();
    void BurgerProcess(Scanner scanner);
    void DrinksProcess(Scanner scanner);
    void DessertProcess(Scanner scanner);
    void printLastSentence(String action);
}
