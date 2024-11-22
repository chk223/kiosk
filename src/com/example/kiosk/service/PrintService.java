package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;

import java.util.Scanner;

/**
 * 출력을 담당하는 서비스
 */
public interface PrintService {
    /**
     * 선택된 메뉴 정보 출력
     * @param menuItem 선택된 음식(치즈버거, 아메리카노 등..)
     */
    public void printSelectedMenuItem(MenuItem menuItem);

    /**
     * 메뉴 출력
     */
    public void printMenu();

    /**
     * 모든 버거 출력
     */
    public void printBurgers();
    /**
     * 모든 음료 출력
     */
    public void printDrinks();

    /**
     * 모든 디저트 출력
     */
    public void printDesserts();

    /**
     * 내용이 출력된 뒤 action에 따라 마지막 문장 출력
     * ex) 0. action
     * @param action 뒤로가기,종료 등
     */
    void lastSentence(String action);
}
