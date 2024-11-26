package com.example.kiosk.service;

import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.CartRepository;
import com.example.kiosk.repository.MenuRepository;
import com.example.kiosk.service.Util.Format;
import com.example.kiosk.service.Util.VerifyInput;

import java.util.Collection;

public class MenuManager {
    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;

    public MenuManager(MenuRepository menuRepository, CartRepository cartRepository) {
        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
    }

    public void addMenu(String name) {
        menuRepository.findMenu(name);
    }

    public void removeMenu(String name) {
        menuRepository.removeMenu(name);
    }

    public void displayMenus() {
        System.out.println("[ MAIN MENU ]");
        Collection<Menu> menus = menuRepository.getAllMenus();
        menus.forEach(menu -> {
            System.out.println(menu.getNumber() + " " + menu.getName());
        });
        Format.lastSentence("종료");
    }

    public int getMenuCount() {
        return menuRepository.getAllMenus().size();
    }

    public void displayMenuItem(int menuIndex) {
        Menu menu = getMenuByIndex(menuIndex);
        menu.displayMenuItems();

        int menuItemIndex = VerifyInput.verify(0,menuRepository.countMenuItems(menuIndex));
        if(menuItemIndex != 0) {
            processMenuItemSelection(menu,menuItemIndex);
        }
    }

    private void processMenuItemSelection(Menu menu, int menuItemIndex) {
        MenuItem menuItem = menu.getMenuItemByIndex(menuItemIndex);
        System.out.print("선택한 메뉴: ");
        menu.displaySpecificMenuItem(menuItemIndex);
        System.out.println();
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        Format.displaySelectOption("확인", "취소");
        int verified = VerifyInput.verify(Format.confirm, Format.cancel);
        if (verified == 1) {
            cartRepository.add(menuItem);
            System.out.println("\n" + menuItem.getItemName() + " 이 장바구니에 추가되었습니다.");
            System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        }
    }

    private Menu getMenuByIndex(int menuIndex) {
        return menuRepository.getAllMenus().stream()
                .filter(menu -> menu.getNumber() == menuIndex).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu index: " + menuIndex));
    }
}
