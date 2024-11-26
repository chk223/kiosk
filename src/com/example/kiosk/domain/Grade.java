package com.example.kiosk.domain;

import java.util.List;

public enum Grade {
    SOLDIER("군인", 0.5,0.1,2),
    STUDENT("학생",0.2,0.03,4),
    KID("아동",0.3,0.05,3),
    BASIC("일반",0.0,0.0,5),
    SPECIAL("국가 유공자",1.0,0.2,1);
    private final String description;
    private final double fixedDiscount;
    private final double percentageDiscount;
    private final int number;
    Grade(String description, double fixedDiscount, double percentageDiscount, int number) {
        this.description = description;
        this.fixedDiscount = fixedDiscount;
        this.percentageDiscount = percentageDiscount;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public double getFixedDiscount() {
        return fixedDiscount;
    }

    public double getPercentageDiscount() {
        return percentageDiscount;
    }
}
