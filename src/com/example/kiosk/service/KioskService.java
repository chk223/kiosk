package com.example.kiosk.service;

import com.example.kiosk.domain.Food;

import java.util.Scanner;

/**
 * 각 서비스 로직을 전부 가져와서 제공하는 통합 서비스
 */
public interface KioskService {
    /**
     * 초기 값 설정. 미리 상품을 등록해 둠
     */
    void init();

    public void printMenuItems(Food foodKind) throws Exception;
    /**
     * 메뉴 출력
     */
    void printMenu();

    /**
     * 해당 음식 종류의 인덱스를 통해 음식 출력
     * @param number 인덱스
     * @param foodKind 음식 종류
     * @return True/false: 제대로 된 입력을 받을 때까지 반복여부
     * @throws Exception
     */
    Boolean process(int number, Food foodKind) throws Exception;

    /**
     * 내용 출력 후 마지막 동작 (뒤로가기, 종료) 메시지 출력
     * @param action 뒤로가기 or 종료 등
     */
    void printLastSentence(String action);
}
