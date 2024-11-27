package com.example.kiosk.service.cartService;

public interface CartService {
    /**
     * 메뉴 범위보다 큰 값을 받았을 때(Order 또는 Cancel에 해당하는 번호) 주문 또는 취소 로직 진행
     * @param input 입력 값
     */
    void processOrder(int input);

    /**
     * 장바구니에서 상품 이름을 통해 상품 삭제
     * @param itemName 상품 이름
     * @throws Exception 장바구니에 해당 이름의 상품이 없을 때 예외처리
     */
    void removeItemToCart(String itemName) throws Exception;

    /**
     * 장바구니가 비었는지 여부
     * @return 참: 장바구니가 비었음 거짓: 장바구니에 상품이 있음
     */
    Boolean isCartsEmpty();
}
