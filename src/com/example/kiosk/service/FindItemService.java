package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;

public interface FindItemService {
    /**
     * 주어진 번호에 해당하는 버거 반환
     * @param index 주어진 번호
     * @return 버거
     */
    MenuItem findBurger(int index);

    /**
     * 주어진 번호에 해당하는 음료 반환
     * @param index 주어진 번호
     * @return 음료
     */
    MenuItem findDrink(int index);

    /**
     * 주어진 번호에 해당하는 디저트 반환
     * @param index 주어진 번호
     * @return 디저트
     */
    MenuItem findDessert(int index);
}
