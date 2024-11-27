package com.example.kiosk.Util;

public class VerifyInput {
    /**
     * 해당 범위에 있는지 검증
     * @param min 최소값
     * @param max 최대값
     * @param input 입력값
     * @return 참(해당 범위에 입력값이 존재) 또는 거짓(해당 범위에 입력값이 존재하지 않음)
     */
    public static Boolean verifyRange(int min, int max, int input) {
        return (input >= min && input <= max);
    }

    /**
     * 최소, 최대 값을 받아서 두 값 사이의 입력을 받고 반환함. 일정 횟수 이상 잘못 입력 시 0 반환.(이전 단계로 돌아가기 위함)
     * @param min 최소값
     * @param max 최대값
     * @return 검증이 완료 된 입력 값 반환(입력 횟수 초과 시 0 반환)
     */
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
