package com.example.kiosk.service;

import com.example.kiosk.domain.Grade;
import com.example.kiosk.repository.MenuRepository;
import com.example.kiosk.service.Util.Format;
import com.example.kiosk.service.Util.VerifyInput;
import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.CartRepository;
import com.example.kiosk.service.discountService.DiscountService;

import java.util.Collection;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final DiscountService discountService;

    public CartServiceImpl(CartRepository cartRepository, MenuRepository menuRepository, DiscountService discountService) {
        this.cartRepository = cartRepository;
        this.menuRepository = menuRepository;
        this.discountService = discountService;
    }

    @Override
    public Boolean isCartsEmpty() {
        return cartRepository.getCartItems().isEmpty();
    }

    @Override
    public void processOrder(int input) {
        int countMenus = menuRepository.countMenus();
        int processIndex = countMenus + 1;
        int cancelIndex = countMenus + 2;
        if (input == processIndex) {//주문 진행
            checkOrderOption();
        } else if (input == cancelIndex) { // 주문 취소
            cancelOrder();
        }
    }

    private void cancelOrder() {
        clearCart();
    }

    private void checkOrderOption() {
        displayConfirmOrder();
        if (confirmOrder()) { //주문 요청 시
            processDiscount();
            cancelOrder();
        }
    }

    private void processDiscount() {
        discountService.displayDiscountInfo();
        int gradeIndex = VerifyInput.verify(1, Grade.values().length);
        Grade selectedGrade = discountService.getGradeByIndex(gradeIndex);
        if (selectedGrade != null) {
            double totalPrice = cartRepository.getTotalPrice();
            double discountPrice = discountService.calculateDiscountPrice(selectedGrade, totalPrice);
            System.out.println("주문이 완료되었습니다. 금액은 W " + discountPrice + " 입니다.");
        }
    }


    @Override
    public void addItemToCart(int menuIndex, int menuItemIndex) {
        MenuItem menuItem = menuRepository.getSpecificMenuItem(menuIndex, menuItemIndex);
        cartRepository.add(menuItem);
        System.out.println(menuItem.getItemName() + " 이 장바구니에 추가되었습니다.");
    }

    @Override
    public void removeItemToCart(MenuItem menuItem) throws Exception {
        cartRepository.remove(menuItem.getItemName());
    }


    public void clearCart() {
        cartRepository.removeAll();
    }

    public Boolean confirmOrder() {
        int verified = VerifyInput.verify(Format.confirm, Format.cancel);
        return verified == Format.confirm;//1 -> 주문 선택 2-> 메뉴판 선택
    }

    private void displayConfirmOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        displayCartItems();
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + cartRepository.getTotalPrice());
        System.out.println();
        Format.displaySelectOption("주문", "메뉴판");
    }

    public void displayCartItems() {
        Collection<CartItem> cartItems = cartRepository.getCartItems();
        for (CartItem cartItem : cartItems) {
            cartItem.displayCartItem();
        }
    }
}
