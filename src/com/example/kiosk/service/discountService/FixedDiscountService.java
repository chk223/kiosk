package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;

import java.util.HashMap;
import java.util.Map;

public class FixedDiscountService implements DiscountService {

    @Override
    public double discount(Grade grade, double price) {
        double discountPrice = 0;
        switch (grade) {
            case BASIC -> discountPrice = price - Grade.BASIC.getFixedDiscount();
            case STUDENT -> discountPrice = price - Grade.STUDENT.getFixedDiscount();
            case KID -> discountPrice = price - Grade.KID.getFixedDiscount();
            case SOLDIER -> discountPrice = price - Grade.SOLDIER.getFixedDiscount();
            case SPECIAL -> discountPrice = price - Grade.SPECIAL.getFixedDiscount();
        }
        return discountPrice;
    }
    @Override
    public Map<Grade, Double> getDiscountAmount() {
        Map<Grade, Double> discountAmount = new HashMap<>();
        discountAmount.put(Grade.SPECIAL,Grade.SPECIAL.getFixedDiscount());
        discountAmount.put(Grade.SOLDIER,Grade.SOLDIER.getFixedDiscount());
        discountAmount.put(Grade.KID,Grade.KID.getFixedDiscount());
        discountAmount.put(Grade.STUDENT,Grade.STUDENT.getFixedDiscount());
        discountAmount.put(Grade.BASIC,Grade.BASIC.getFixedDiscount());
        return discountAmount;
    }

    @Override
    public String getDiscountMark() {
        return "W";
    }
}
