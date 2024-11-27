package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;
import com.example.kiosk.Util.Format;

import java.util.Arrays;
import java.util.Comparator;


public class FixedDiscountService implements DiscountService {

    @Override
    public double calculateDiscountPrice(Grade grade, double price) {
        double discountAmount = grade.getFixedDiscount();
        double discountPrice = price -discountAmount;
        return Format.changeRoundDoubleFormat(discountPrice);
    }

    @Override
    public void displayDiscountInfo() {
        System.out.println("할인 정보를 입력하세요.");
        System.out.println("[ 할인 등급 정보 ]");
        Arrays.stream(Grade.values()).sorted(Comparator.comparingInt(Grade::getNumber))
                .forEach(grade -> System.out.println(grade.getNumber()+". "+grade.getDescription()+": "+grade.getFixedDiscount()+getDiscountMark()));
    }
    private String getDiscountMark() {
        return "W";
    }

    @Override
    public Grade getGradeByIndex(int index) {
        return Arrays.stream(Grade.values())
                .filter(grade -> grade.getNumber() == index)
                .findFirst()
                .orElse(null);
    }
}
