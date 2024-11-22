package com.example.kiosk.repository;

import com.example.kiosk.domain.Food;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    /**
     * 음식 정보 추가
     * @param menuItem 음식
     * @param foodKind 음식 종류
     */
    void save(MenuItem menuItem, Food foodKind);

    /**
     * 모든 버거 정보 반환
     * @return 버거 리스트
     */
    List<MenuItem> getBurger();
    /**
     * 모든 음료 정보 반환
     * @return 음료 리스트
     */
    List<MenuItem> getDrink();
    /**
     * 모든 디저트 정보 반환
     * @return 디저트 리스트
     */
    List<MenuItem> getDessert();
}
