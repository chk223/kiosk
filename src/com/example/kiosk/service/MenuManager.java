package com.example.kiosk.service;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.exception.RepositoryException;
import com.example.kiosk.repository.cartRepository.CartRepository;
import com.example.kiosk.repository.menuRepository.MenuRepository;
import com.example.kiosk.Util.Format;
import com.example.kiosk.Util.VerifyInput;

import java.util.Collection;

public class MenuManager {
    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;

    public MenuManager(MenuRepository menuRepository, CartRepository cartRepository) {
        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
    }

    /**
     * 메뉴 이름을 통해 메뉴 추가 혹은 해당 이름을 가진 메뉴 찾기
     * @param name 메뉴 이름
     */
    public Menu addMenu(String name) {
        return menuRepository.findMenu(name);
    }

    /**
     * 메뉴 이름을 통해 메뉴 삭제
     * @param name 메뉴 이름
     */
    public void removeMenu(String name) {
        menuRepository.removeMenu(name);
    }

    /**
     * 모든 메뉴 출력(키오스크 초기 화면)
     */
    public void displayMenus() {
        System.out.println("[ MAIN MENU ]");
        Collection<Menu> menus = menuRepository.getAllMenus();
        menus.forEach(menu -> {
            System.out.println(menu.getNumber() + " " + menu.getName());
        });
        Format.lastSentence("종료");
    }

    /**
     * 메뉴의 총 개수 반환
     * @return 총 메뉴 개수
     */
    public int getMenuCount() {
        return menuRepository.getAllMenus().size();
    }

    /**
     * 메뉴 번호에 해당하는 메뉴의 상품 출력 및 상품 선택
     * @param menuIndex 메뉴 번호
     */
    public void serviceShopping(int menuIndex) {
        Menu menu = getMenuByIndex(menuIndex);
        menu.displayMenuItems();
        int menuItemIndex = VerifyInput.validateRangeAndReturnInput(0,menuRepository.countMenuItems(menuIndex));
        if(menuItemIndex != 0) {
            processMenuItemSelection(menu,menuItemIndex);
        }
    }

    /**
     * 선택한 상품 번호를 통해 장바구니에 추가(혹은 이전 단계로 돌아가기)
     * @param menu 선택 된 메뉴
     * @param menuItemIndex 선택 된 메뉴에서 선택 한 상품 번호
     */
    private void processMenuItemSelection(Menu menu, int menuItemIndex) {
        MenuItem menuItem = menu.getMenuItemByIndex(menuItemIndex);
        System.out.print("선택한 메뉴: ");
        menu.displaySpecificMenuItem(menuItemIndex);
        System.out.println();
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        Format.displaySelectOneOfTheTwoOptions("확인", "취소");
        int verified = VerifyInput.validateRangeAndReturnInput(Format.confirm, Format.cancel);
        if (verified == 1) {
            cartRepository.add(menuItem);
            System.out.println("\n" + menuItem.getItemName() + " 이 장바구니에 추가되었습니다.");
            System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        }
    }

    /**
     * 메뉴 번호를 통해 메뉴 찾기
     * @param menuIndex 메뉴 번호
     * @return 번호로 찾은 메뉴 객체
     */
    private Menu getMenuByIndex(int menuIndex) {
        return menuRepository.getAllMenus().stream()
                .filter(menu -> menu.getNumber() == menuIndex).findFirst()
                .orElseThrow(() -> new RepositoryException(menuIndex));
    }
}
