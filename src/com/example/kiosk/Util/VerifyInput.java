package com.example.kiosk.Util;

import com.example.kiosk.exception.InputException;

import java.util.List;

public class VerifyInput {
    private static final String ERROR_MESSAGE = "화면에 표기 된 값을 입력해주세요.";
    private static final String EXCEED_MESSAGE = "입력 횟수가 초과되었습니다.";
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
    public static int validateRangeAndReturnInput(int min, int max) {
        int maximumCount = 100;
        do{
            try {
                System.out.print("입력: ");
                int input = Input.input();
                Boolean verify = verifyRange(min,max, input);
                if(verify) {
                    return input;
                }
                else displayErrorMessage(ERROR_MESSAGE);
            } catch (Exception e) {
                maximumCount--;
                displayErrorMessage(ERROR_MESSAGE);
            }
        } while(maximumCount >0);
        displayErrorMessage(EXCEED_MESSAGE);
        return 0;
    }

    /**
     * 입력 값이 숫자 리스트의 값 안에 있는지 검증 후 반환
     * @param numberList 숫자 리스트
     * @return 검증 된 입력 값 반환 / 0: 입력 횟수 초과 시 반환(이전 단계 돌아가기)
     */
    public static int validateNumberInList(List<Integer> numberList) {
        int maximumCount = 100;
        do{
            try {
                System.out.print("입력: ");
                int input = Input.input();
                Boolean verify = verifyContain(input,  numberList);
                if(verify) {
                    return input;
                }
                else displayErrorMessage(ERROR_MESSAGE);
            } catch (Exception e) {
                maximumCount--;
                displayErrorMessage(ERROR_MESSAGE);
            }
        } while(maximumCount >0);
        displayErrorMessage(EXCEED_MESSAGE);
        return 0;
    }

    /**
     * 입력 값이 숫자 리스트에 있는지 검증하여 참,거짓 반환
     * @param input 입력 값
     * @param numberList 숫자 리스트
     * @return 참(리스트 안에 숫자 있음) 거짓(리스트 안에 숫자 없음)
     */
    private static Boolean verifyContain(int input, List<Integer> numberList) {
        return !numberList.stream().filter(n -> n == input).findFirst().isEmpty();
    }

    /**
     * message를 출력
     * @param message 출력 될 내용
     */
    private static void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
