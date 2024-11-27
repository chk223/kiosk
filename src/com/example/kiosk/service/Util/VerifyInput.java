package com.example.kiosk.service.Util;

public class VerifyInput {
    public static Boolean verifyRange(int min, int max, int input) {
        return (input >= min && input <= max);
    }
    public static int validateAndReturnInput(int min, int max) {
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
                System.out.println(min+"이상 "+max+" 이하의 정수를 입력해주세요.");
                e.printStackTrace();
            }
        } while(maximumCount >0);
        System.out.println("입력 횟수가 초과되었습니다.");
        return 0;
    }
}
