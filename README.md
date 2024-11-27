# kiosk
> 프로젝트 설명


본 프로젝트는 콘솔 환경에서 키오스크를 구현하였습니다.

사용자로부터 입력값을 받고, 올바른 값을 입력 한 경우 결과 값을 출력합니다.

사용자는 메뉴 선택, 상품 선택을 할 수 있으며, 선택한 상품은 장바구니에 담겨집니다.

사용자는 장바구니에 담긴 아이템을 주문할 수 있으며, 선택 한 등급에 따라 할인을 받을 수 있습니다.

> 사용 방법

1. 메뉴 선택

   ![image](https://github.com/user-attachments/assets/d454f709-bd5a-4522-977c-7377757b880f)



초기 메뉴 화면에서 출력된 메뉴의 번호를 입력하면 선택한 메뉴에 해당하는 상품 출력한다. 0번 입력 시 초기 메뉴 화면으로 돌아갑니다.
   
2. 상품 선택

![image](https://github.com/user-attachments/assets/f0e7f81a-7c62-4fcf-85cf-630013711637)

출력된 상품의 번호를 선택하면 장바구니에 담을지 여부를 출력합니다.

 
3. 장바구니 담을지 여부 선택

 ![image](https://github.com/user-attachments/assets/6b3131c9-d59f-4f79-b23f-02827c0e54c2)

확인을 선택하면 상품이 장바구니에 추가되고 안내 문구가 출력됩니다. 그 후 초기화면으로 돌아갑니다. 취소를 선택하면 초기 화면으로 돌아갑니다.

4. 장바구니에 상품이 있을 경우, 메뉴 활성화

![image](https://github.com/user-attachments/assets/fbb5bfc6-3965-43b5-b20e-f7c7fa282989)

장바구니에 상품이 있다면, 초기 화면에서 Orders, Cancel 목록이 활성화됩니다.

Cancel을 선택 한 경우, 장바구니를 초기화합니다.

Orders를 선택 한 경우, 장바구니에 담긴 상품들과 수량, 가격을 출력합니다.

주문, 메뉴판, 상품 삭제 선택지를 출력합니다.


![image](https://github.com/user-attachments/assets/faa0a029-c90a-4926-8840-9f0c7eb5758e)



5. 장바구니에 있는 상품 삭제

![image](https://github.com/user-attachments/assets/1e882a73-1fd8-485f-8ef2-6a495cc1060e)

 삭제 옵션을 선택하면 삭제 할 상품의 번호를 입력 받는다. 0을 입력 시 삭제 과정이 취소됩니다.

 해당 인덱스의 상품의 개수가 2개 이상인 경우, 상품의 개수가 줄어들고, 1개일 경우 상품이 장바구니 목록에서 삭제됩니다.

6. 장바구니에 있는 상품 구매

![image](https://github.com/user-attachments/assets/d830a346-cd68-4f81-9dcd-b7bd8eada9e5)

주문 옵션을 선택하면 할인 등급 정보를 출력해줍니다. 

메뉴판 옵션을 선택 시 초기 화면으로 돌아갑니다.


7. 할인 적용
   
![image](https://github.com/user-attachments/assets/3b54671d-e0c1-4352-8282-35fd7f14761c)

할인 비율이 적용된 금액이 출력되고, 초기 화면으로 돌아갑니다.
