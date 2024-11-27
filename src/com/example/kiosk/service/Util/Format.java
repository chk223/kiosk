package com.example.kiosk.service.Util;

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
    public static void displayOrderMenu(int size) {
        System.out.println(size+1+". Orders");
        System.out.println(size+2+". Cancel");
    }

    public static void displayMenuItem(String name, double price, String description) {
        System.out.println(name+" | W "+price+" | "+description);
    }

    public static void displaySelectOneOfTheTwoOptions(String firstOption, String secondOption){
        System.out.println("1. "+firstOption+ blankFormat("")+"2. "+secondOption);
    }

    public static void displaySelectOneOfTheThreeOptions(String firstOption, String secondOption, String thirdOption){
        System.out.println("1. "+firstOption+ blankFormat("")+"2. "+secondOption+ blankFormat("")+"3. "+thirdOption);
    }

    public static double changeRoundDoubleFormat(double doubleFormat) {
        return (double) Math.round(doubleFormat * 100) / 100;
    }
}
