package com.example.kiosk.service;

import com.example.kiosk.domain.Grade;
import com.example.kiosk.repository.MenuRepository;
import com.example.kiosk.service.Util.Format;
import com.example.kiosk.service.Util.VerifyInput;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.CartRepository;
import com.example.kiosk.service.discountService.DiscountService;

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
    public void processOrder(int chosenMenuNumber) {
        int menuRange = menuRepository.countMenus();
        int processIndex = menuRange + 1;
        int cancelIndex = menuRange + 2;
        if (chosenMenuNumber == processIndex) {//주문 진행(OrderList)
            checkOrderOption();
        } else if (chosenMenuNumber == cancelIndex) { // 주문 취소
            cancelOrder();
        }
    }

    private void cancelOrder() {
        clearCart();
    }

    private void checkOrderOption() {
        displayConfirmOrder();
        int chosenOrderOptions = chooseOrderOptions();
        if (chosenOrderOptions ==1) { //주문 요청 시
            processDiscount();
            cancelOrder();
        }
        else if(chosenOrderOptions ==3) { //주문 상품 삭제
            int cartItemRange = cartRepository.getCartItems().size();
            System.out.println("장바구니에서 삭제 할 상품의 번호를 선택하세요.");
            int targetIndex = VerifyInput.validateAndReturnInput(0, cartItemRange);
            if(targetIndex == 0) {
                return;
            } else{
                String removeTargetName = cartRepository.getCartItemNameByIndex(targetIndex);
                try {
                    System.out.println(removeTargetName+" 이 삭제되었습니다.");
                    removeItemToCart(removeTargetName);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    private void processDiscount() {
        discountService.displayDiscountInfo();
        int gradeIndex = VerifyInput.validateAndReturnInput(1, Grade.values().length);
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
    public void removeItemToCart(String itemName) throws Exception {
        cartRepository.remove(itemName);
    }


    public void clearCart() {
        cartRepository.removeAll();
    }

    public int chooseOrderOptions() {
        return VerifyInput.validateAndReturnInput(Format.confirm, Format.delete);//1 -> 주문 선택 2-> 메뉴판 선택 3-> 상품 주문 취소
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
        Format.displaySelectOneOfTheThreeOptions("주문", "메뉴판","상품 삭제");
    }

    public void displayCartItems() {
        cartRepository.getCartItems().forEach(cartItem -> {
            System.out.print(cartItem.getNumber() +". ");
            cartItem.displayCartItem();
    });
    }
}
