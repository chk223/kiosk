package com.example.kiosk.service.Util;

public class VerifyInput {
    public static Boolean verifyRange(int min, int max, int input) {
        return input >= min && input <= max;
    }
    public static int verify(int min, int max) {
        int maximumCount = 100;
        do{
            try {
                System.out.print("입력: ");
                int input = Input.input();
                Boolean verify = verifyRange(min,max, input);
                if(verify) {
                    return input;
                }
            } catch (Exception e) {
                maximumCount--;
                System.out.println(e.getMessage());
            }
        } while(maximumCount >0);
        System.out.println("입력 횟수가 초과되었습니다.");
        return 0;
    }
}
