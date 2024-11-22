package com.example.kiosk.domain;

public enum Food {
    BURGER(1),DRINK(2),DESSERT(3);

    Food(int index) {
        this.index = index;
    }

    private final int index;

    /**
     * 인덱스를 통해 음식 종류 반환
     * @param index 인덱스
     * @return 음식 종류(Food)
     */
    public static Food findFoodByIndex(int index) {
        for(Food food: Food.values()) {
            if(food.getIndex() == index) return food;
        }
        throw new IllegalArgumentException("인덱스 범위를 벗어났습니다.");
    }

    public int getIndex() {
        return index;
    }
}
