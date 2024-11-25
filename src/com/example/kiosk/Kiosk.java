package com.example.kiosk;
import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.Grade;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.service.CartService;
import com.example.kiosk.service.KioskInit;
import com.example.kiosk.service.KioskManager;
import com.example.kiosk.service.KioskScanner;
import com.example.kiosk.service.discountService.DiscountService;

import java.util.List;
import java.util.Objects;

/**
 * 프로그램 순서 및 흐름 제어 담당
 */
public class Kiosk {
    private final KioskScanner kioskScanner;
    private final CartService cartService;
    private final KioskInit kioskInit;
    private final DiscountService discountService;
    public Kiosk(KioskScanner kioskScanner, CartService cartService, KioskInit kioskInit, DiscountService discountService) {
        this.kioskScanner = kioskScanner;
        this.cartService = cartService;
        this.kioskInit = kioskInit;
        this.discountService = discountService;
    }
    public void start() {
        int maximum_count = 1000000;
        kioskInit.init();
        while(maximum_count>0) {
            String menuName = "";
            MenuItem menuItem = null;
            int secondInput;
            // 첫 번째 입력 -> 값 반환
            try {
                menuName = kioskScanner.selectMenu();
                if(Objects.equals(menuName, "")) break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            if (!Objects.equals(menuName, "Order")) {//////////////
                // 두 번째 입력
                try {
                    secondInput = kioskScanner.selectMenuItem(menuName);
                    if(secondInput == 0) continue;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
                // 장바구니 담을지 여부
                try {
                    menuItem = kioskScanner.addItemToCart(menuName, secondInput);
                    System.out.println("menuItem: " + menuItem.getItemName());
                    if(menuItem != null) {
                        cartService.addItemToCart(menuItem);
                        System.out.println(menuItem.getItemName()+"이 장바구니에 추가되었습니다.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    maximum_count--;
                }
            }
            else{//주문할건지
                List<CartItem> cartItems = cartService.getCartItems();
                double totalPrice = cartService.getTotalPrice();
                kioskScanner.displayOrderList(cartItems,totalPrice);
                int input = 0;
                try {
                    input = kioskScanner.processOrder();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
                if(input ==1) {
                    try {
                        Grade grade = kioskScanner.getDiscountInfo(discountService.getDiscountAmount(), discountService.getDiscountMark(), totalPrice);
                        double discountPrice = discountService.discount(grade, totalPrice);
                        System.out.println("주문이 완료되었습니다. 금액은 W "+discountPrice+" 입니다.");
                        cartService.clearCart();
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
            }


        }
    }

}
