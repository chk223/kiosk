package com.example.kiosk.service.cartService;

import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.Grade;
import com.example.kiosk.exception.RepositoryException;
import com.example.kiosk.repository.menuRepository.MenuRepository;
import com.example.kiosk.Util.Format;
import com.example.kiosk.Util.VerifyInput;
import com.example.kiosk.repository.cartRepository.CartRepository;
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
            clearCart();
        }
    }

    @Override
    public void removeItemToCart(String itemName) throws RepositoryException {
        cartRepository.remove(itemName);
    }

    /**
     * 장바구니 비우기
     */
    private void clearCart() {
        cartRepository.removeAll();
    }

    /**
     * 1. 주문 2. 메뉴판 3. 상품 삭제 => 옵션 중 하나 선택 후 처리
     */
    private void checkOrderOption() {
        displayConfirmOrder();
        int chosenOrderOptions = chooseOrderOptions();
        if (chosenOrderOptions ==1) { //주문 요청 시
            processDiscount();
            clearCart();
        }
        else if(chosenOrderOptions ==3) { //주문 상품 삭제
            System.out.println("장바구니에서 삭제 할 상품의 번호를 선택하세요. 0: 삭제 취소");
            int targetIndex = VerifyInput.validateNumberInList(cartRepository.getCartItemNumbers());
            if(targetIndex == 0) {
                return;
            } else{
                deleteItemFromCart(targetIndex);
            }
        }
    }

    /**
     * 해당 번호의 상품을 장바구니에서 삭제
     * @param targetIndex 장바구니에 담긴 상품의 번호
     */
    private void deleteItemFromCart(int targetIndex) {
        String targetNameForRemove = cartRepository.getCartItemNameByIndex(targetIndex);
        System.out.println(targetIndex+"번 입력, 삭제 할 상품 이름: " + targetNameForRemove);
        try {
            System.out.println(targetNameForRemove+" 이 삭제되었습니다.");
            removeItemToCart(targetNameForRemove);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 할인 로직 실행 후 주문 완료 처리
     */
    private void processDiscount() {
        discountService.displayDiscountInfo();
        int gradeIndex = VerifyInput.validateRangeAndReturnInput(1, Grade.values().length);
        Grade selectedGrade = discountService.getGradeByIndex(gradeIndex);
        if (selectedGrade != null) {
            double totalPrice = cartRepository.getTotalPrice();
            double discountPrice = discountService.calculateDiscountPrice(selectedGrade, totalPrice);
            System.out.println("주문이 완료되었습니다. 금액은 W " + discountPrice + " 입니다.");
        }
    }

    /**
     * 주문 할 것인지 여부 선택
     * @return 1. 주문 2. 메뉴판 3. 장바구니에서 상품 삭제
     */
    private int chooseOrderOptions() {
        return VerifyInput.validateRangeAndReturnInput(Format.confirm, Format.delete);//1 -> 주문 선택 2-> 메뉴판 선택 3-> 상품 주문 취소
    }

    /**
     * 주문 영수증이 다음과 같은지 확인하는 화면 출력==>
     * 1. 주문 2. 메뉴판 3. 상품삭제
     */
    private void displayConfirmOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        displayCartItems();
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + Format.changeRoundDoubleFormat(cartRepository.getTotalPrice()));
        System.out.println();
        Format.displaySelectOneOfTheThreeOptions("주문", "메뉴판","상품 삭제");
    }

    /**
     * 장바구니에 담긴 모든 상품 형식에 맞게 출력
     */
    private void displayCartItems() {
        cartRepository.getCartItems().forEach(CartItem::displayCartItem);
    }
}
