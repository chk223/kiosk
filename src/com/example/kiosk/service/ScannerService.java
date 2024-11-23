package com.example.kiosk.service;

/**
 * 출력을 담당하는 서비스
 */
public interface ScannerService {
    /**
     * 선택된 메뉴 정보 출력
     */
    public void printSelectedMenuItem(String name, int index) throws Exception;

    /**
     * 메뉴 출력
     */
    public void printMenu();

    /**
     * 음식 종류에 따라 모든 메뉴 출력
     */
    public void printMenuItems(String name) throws Exception;
    /**
     * 내용이 출력된 뒤 action에 따라 마지막 문장 출력
     * ex) 0. action
     * @param action 뒤로가기,종료 등
     */
    void lastSentence(String action);
    int input() throws Exception;
}
