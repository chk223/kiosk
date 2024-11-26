package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;

public interface DiscountService {
    double calculateDiscountPrice(Grade grade, double price);
    void displayDiscountInfo();
    Grade getGradeByIndex(int index);
}
