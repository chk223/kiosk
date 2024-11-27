package com.example.kiosk.repository.menuRepository;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;

import java.util.List;

public interface MenuRepository {
    /**
     * 해당 이름을 가진 메뉴 반환
     * @param name 메뉴 이름
     * @return 해당 이름을 가진 메뉴
     */
    Menu findMenu(String name);

    /**
     * 해당 이름을 가진 메뉴 제거
     * @param name 메뉴 이름
     */
    void removeMenu(String name);

    /**
     * 모든 메뉴 반환
     * @return 모든 메뉴를 담고 있는 리스트
     */
    List<Menu> getAllMenus();

    /**
     * 메뉴에 MenuItem 추가
     * @param menu
     * @param menuItem
     */
    void saveItem(Menu menu, MenuItem menuItem);

    /**
     * 메뉴에서 해당 이름을 가진 MenuItem 제거
     * @param menu 메뉴
     * @param itemName 삭제할 MenuItem의 이름
     */
    void removeItem(Menu menu, String itemName);

    /**
     * 메뉴인덱스에 해당하는 메뉴에서 MenuItem인덱스에 해당하는 MenuItem 반환
     * @param menuIndex 메뉴 인덱스
     * @param menuItemIndex MenuItem인덱스
     * @return 해당하는 MenuItem
     */
    MenuItem getSpecificMenuItem(int menuIndex, int menuItemIndex);

    /**
     * 메뉴의 총 개수 반환
     * @return 메뉴의 총 개수
     */
    int countMenus();

    /**
     * 메뉴 인덱스에 해당하는 메뉴가 갖고 있는 MenuItem의 총 개수 반환
     * @param menuIndex 메뉴 인덱스
     * @return MenuItem의 총 개수
     */
    int countMenuItems(int menuIndex);
}
