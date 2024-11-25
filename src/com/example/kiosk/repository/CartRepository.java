package com.example.kiosk.repository;

import com.example.kiosk.domain.CartItem;

import java.util.List;

public interface CartRepository {
    /**
     * 장바구니에 상품 추가 (현재 장바구니에 있으면 수량만 증가)
     * @param cartItem (상품 이름, 가격, 수량)
     */
    void add(CartItem cartItem);

    /**
     * 장바구니에서 상품 개수 감소 (개수가 0이면 장바구니에서 제거)
     * @param name 상품 이름
     * @throws Exception
     */
    void remove(String name) throws Exception;

    /**
     * 장바구니 초기화
     */
    void removeAll();

    /**
     * 장바구니에 있는 모든 상품의 이름, 수량, 가격 반환
     */
    List<CartItem> getCartItems();

    /**
     * 현재 장바구니에 있는 상품들의 총액 반환
     */
    double getPrice();
}
