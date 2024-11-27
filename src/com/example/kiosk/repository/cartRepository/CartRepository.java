package com.example.kiosk.repository.cartRepository;

import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.exception.RepositoryException;

import java.util.List;

public interface CartRepository {
    /**
     * 장바구니에 상품 추가 (현재 장바구니에 있으면 수량만 증가)
     * @param menuItem (상품 이름, 가격, 수량)
     */
    void add(MenuItem menuItem);

    /**
     * 장바구니에서 상품 개수 감소 (개수가 0이면 장바구니에서 제거)
     * @param name 상품 이름
     * @throws RepositoryException
     */
    void remove(String name) throws RepositoryException;

    /**
     * 장바구니 초기화
     */
    void removeAll();

    /**
     * 장바구니에 있는 모든 상품 반환
     */
    List<CartItem> getCartItems();

    /**
     * 현재 장바구니에 있는 상품들의 총액 반환
     */
    double getTotalPrice();

    /**
     * 장바구니에 담긴 상품의 고유 번호를 통해 상품 이름 반환
     * @param index 장바구니에 담긴 상품의 고유 번호
     * @return 상품 이름
     */
    String getCartItemNameByIndex(int index);

    /**
     * 장바구니에 담긴 상품의 고유 번호들을 담은 리스트 반환
     * @return 장바구니에 담긴 상품의 고유 번호들을 담은 리스트
     */
    List<Integer> getCartItemNumbers();
}
