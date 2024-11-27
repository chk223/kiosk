package com.example.kiosk.service.discountService;

import com.example.kiosk.domain.Grade;

public interface DiscountService {
    /**
     * 등급과 총액을 받고, 등급에 따른 할인을 적용해서 할인된 가격 반환
     * @param grade 사용자가 선택한 등급
     * @param price 주문 할 상품들의 총액
     * @return 할인 적용 된 가격
     */
    double calculateDiscountPrice(Grade grade, double price);

    /**
     * 등급에 따른 할인 정보 출력
     */
    void displayDiscountInfo();

    /**
     * 사용자가 선택한 등급의 인덱스를 통해 해당 등급 정보 반환
     * @param index 사용자가 선택한 등급의 번호
     * @return 번호에 해당하는 등급 정보
     */
    Grade getGradeByIndex(int index);
}
