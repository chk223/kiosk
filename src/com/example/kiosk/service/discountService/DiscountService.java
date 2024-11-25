package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;

import java.util.Map;

public interface DiscountService {
    double discount(Grade grade, double price);
    Map<Grade, Double> getDiscountAmount();
    String getDiscountMark();
}
