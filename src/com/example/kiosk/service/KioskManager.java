package com.example.kiosk.service;

/**
 * 각 서비스 로직을 전부 가져와서 제공하는 통합 서비스
 */
public interface KioskManager {
    /**
     * 초기 값 설정. 미리 상품을 등록해 둠
     */
    void init();

    int input() throws Exception;

    public String printMenuItems(int index) throws Exception;
    /**
     * 메뉴 출력
     */
    void printMenu();

    /**
     */
    public void process(String name, int number) throws Exception;

    /**
     * 내용 출력 후 마지막 동작 (뒤로가기, 종료) 메시지 출력
     * @param action 뒤로가기 or 종료 등
     */
    void printLastSentence(String action);
}
