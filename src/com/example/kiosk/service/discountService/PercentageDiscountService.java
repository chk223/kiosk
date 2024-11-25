package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;

import java.util.HashMap;
import java.util.Map;

public class PercentageDiscountService implements DiscountService {
    @Override
    public double discount(Grade grade, double price) {
        double discountPrice = 0;
        switch (grade) {
            case BASIC -> discountPrice = price * (1.0 - Grade.BASIC.getPercentageDiscount());
            case STUDENT -> discountPrice = price * (1.0 - Grade.STUDENT.getPercentageDiscount());
            case KID -> discountPrice = price * (1.0 - Grade.KID.getPercentageDiscount());
            case SOLDIER -> discountPrice = price * (1.0 - Grade.SOLDIER.getPercentageDiscount());
            case SPECIAL -> discountPrice = price * (1.0 - Grade.SPECIAL.getPercentageDiscount());
        }
        return discountPrice;
    }

    @Override
    public Map<Grade, Double> getDiscountAmount() {
        Map<Grade, Double> discountAmount = new HashMap<>();
        discountAmount.put(Grade.SPECIAL,Grade.SPECIAL.getPercentageDiscount()*100);
        discountAmount.put(Grade.SOLDIER,Grade.SOLDIER.getPercentageDiscount()*100);
        discountAmount.put(Grade.KID,Grade.KID.getPercentageDiscount()*100);
        discountAmount.put(Grade.STUDENT,Grade.STUDENT.getPercentageDiscount()*100);
        discountAmount.put(Grade.BASIC,Grade.BASIC.getPercentageDiscount()*100);
        return discountAmount;
    }

    @Override
    public String getDiscountMark() {
        return "%";
    }
}
