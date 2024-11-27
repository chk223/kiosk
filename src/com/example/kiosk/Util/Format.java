package com.example.kiosk.Util;

public class Format {

    public static int confirm = 1;
    public static int cancel = 2;
    public static int delete = 3;

    /**
     * 상품과 가격의 경계선을 일정하게 그어 주기 위한 공백설정
     * @param name 상품 이름
     * @return 공백 문자열
     */
    public static String blankFormat(String name) {
        int lengthOfName = 20 - name.length();
        return " ".repeat(Math.max(0, lengthOfName));
    }

    /**
     * 입력 전 상황에 따른 문구 출력(종료 또는 뒤로가기)
     * @param action 상황
     */
    public static void lastSentence(String action) {
        System.out.println("0.  "+ action);
    }

    /**
     * 장바구니에 상품이 있을 경우 출력하는 주문하기/취소 버튼
     * @param size 메뉴의 총 개수
     */
    public static void displayOrderMenu(int size) {
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(size+1+". Orders");
        System.out.println(size+2+". Cancel");
    }

    /**
     * 메뉴 아이템의 요소를 받아 형식에 맞게 출력
     * @param name 메뉴 아이템 이름
     * @param price 메뉴 아이템 가격
     * @param description 메뉴 아이템 설명
     */
    public static void displayMenuItem(String name, double price, String description) {
        System.out.println(name+" | W "+price+" | "+description);
    }

    public static void displayCartItem(int number, String name, double price, String description, int quantity) {
        System.out.println(number+". "+name+" | W "+price+" | "+description+" | 수량: "+quantity+"개 | 합계: "+price*quantity+" W");
    }

    /**
     * 2 개중 한개를 선택하는 옵션 선택 포맷
     * @param firstOption 첫 번째 옵션 이름
     * @param secondOption 두 번째 옵션 이름
     */
    public static void displaySelectOneOfTheTwoOptions(String firstOption, String secondOption){
        System.out.println("1. "+firstOption+ blankFormat("")+"2. "+secondOption);
    }

    /**
     * 3 개중 한개를 선택하는 옵션 선택 포맷
     * @param firstOption 첫 번째 옵션 이름
     * @param secondOption 두 번째 옵션 이름
     * @param thirdOption 세 번째 옵션 이름
     */
    public static void displaySelectOneOfTheThreeOptions(String firstOption, String secondOption, String thirdOption){
        System.out.println("1. "+firstOption+ blankFormat("")+"2. "+secondOption+ blankFormat("")+"3. "+thirdOption);
    }

    /**
     * 소수점 두 번째 자리까지 반올림
     * @param doubleFormat 반올림 할 수
     * @return 소수점 두 번째 자리까지 반올림 된 수
     */
    public static double changeRoundDoubleFormat(double doubleFormat) {
        return (double) Math.round(doubleFormat * 100) / 100;
    }
}
