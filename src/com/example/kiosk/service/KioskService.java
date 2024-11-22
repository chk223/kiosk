package com.example.kiosk.service;

import java.util.Scanner;

/**
 * 각 서비스 로직을 전부 가져와서 제공하는 통합 서비스
 */
public interface KioskService {
    /**
     * 초기 값 설정. 미리 상품을 등록해 둠
     */
    void init();

    /**
     * 메뉴 출력
     */
    void printMenu();

    /**
     * 번호를 입력 받아 해당하는 버거의 정보 출력
     * @param scanner 스캐너 객체를 한 개로 돌려쓰기
     */
    void BurgerProcess(Scanner scanner);

    /**
     * 번호를 입력 받아 해당하는 음료의 정보 출력
     *      * @param scanner 스캐너 객체를 한 개로 돌려쓰기
     */
    void DrinksProcess(Scanner scanner);
    /**
     * 번호를 입력 받아 해당하는 디저트의 정보 출력
     * @param scanner 스캐너 객체를 한 개로 돌려쓰기
     */
    void DessertProcess(Scanner scanner);

    /**
     * 내용 출력 후 마지막 동작 (뒤로가기, 종료) 메시지 출력
     * @param action 뒤로가기 or 종료 등
     */
    void printLastSentence(String action);
}
