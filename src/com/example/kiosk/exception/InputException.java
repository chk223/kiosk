package com.example.kiosk.exception;

import java.util.InputMismatchException;

public class InputException extends InputMismatchException {
    public InputException() {
      super("입력 값이 잘못되었습니다.");
    }
}
