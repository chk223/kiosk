package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;
import com.example.kiosk.service.Util.Format;

import java.util.Arrays;
import java.util.Comparator;

public class PercentageDiscountService implements DiscountService {

    @Override
    public double calculateDiscountPrice(Grade grade, double price) {
        double discountAmount = grade.getPercentageDiscount();
        double discountPrice = price * (1.0-discountAmount);
        return Format.changeRoundDoubleFormat(discountPrice);
    }

    @Override
    public void displayDiscountInfo() {
        System.out.println("할인 정보를 입력하세요.");
        System.out.println("[ 할인 등급 정보 ]");
        Arrays.stream(Grade.values()).sorted(Comparator.comparingInt(Grade::getNumber))
                .forEach(grade -> System.out.println(grade.getNumber()+". "+grade.getDescription()+": "+grade.getPercentageDiscount()*100+getDiscountMark()));
    }
    private String getDiscountMark() {
        return "%";
    }

    @Override
    public Grade getGradeByIndex(int index) {
        return Arrays.stream(Grade.values())
                .filter(grade -> grade.getNumber() == index)
                .findFirst()
                .orElse(null);

    }
}
