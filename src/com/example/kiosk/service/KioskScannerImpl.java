package com.example.kiosk.service;
import com.example.kiosk.service.discountService.DiscountService;
import com.example.kiosk.domain.CartItem;
import com.example.kiosk.domain.Grade;
import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.CartRepository;
import com.example.kiosk.repository.MenuRepository;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class KioskScannerImpl implements KioskScanner {
    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;
    Scanner scanner = new Scanner(System.in);

    public KioskScannerImpl(MenuRepository menuRepository, CartRepository cartRepository) {
        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
    }
    public int input() throws Exception {
        try{
            System.out.print("옵션을 선택하세요: ");
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new Exception("입력이 잘못되었습니다");
        }
    }
    public void displayOrderMenu() {
        int size = menuRepository.getAllMenus().size();
        System.out.println(size+1+". Orders");
        System.out.println(size+2+". Cancel");
    }
    public void displayMenus() {
        List<Menu> menus = menuRepository.getAllMenus();
        System.out.println("[ MAIN MENU ]");
        menus.forEach(menu ->{
            System.out.println(menu.getNumber()+" "+menu.getName());
        });
        lastSentence("종료");
        if(!cartRepository.getCartItems().isEmpty()) {
            displayOrderMenu();
        }
    }
    @Override
    public String selectMenu() throws Exception {
        int maxProcessCount = 10000;
        int menuSize = menuRepository.getAllMenus().size();
        while(maxProcessCount>0){
            displayMenus();
            try {
                int input = input();
                if(input>=0 && input<=menuSize) {//람다 switch문으로.
                    System.out.println("첫 번째 입력: " + input);
                    return menuRepository.getMenuNameByIndex(input);
                }
                else if (input == menuSize+1) {
                    return "Order";
                }
                else if (input == menuSize+2) {
                    return "";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                maxProcessCount --;
            }
        }
        throw new Exception("입력 횟수 초과");
    }

    public void displayMenuItems(String name) throws Exception {
        List<MenuItem> items;
        try{
            items = menuRepository.findMenuItems(name);
        } catch (Exception e) {
            throw new Exception("해당 인덱스에 해당하는 이름이 없습니다!!");
        }
        System.out.println("[ "+name.toUpperCase()+" MENU ]");

        AtomicInteger index = new AtomicInteger(1);
        items.forEach(item -> {
            String blank = blankFormat(item.getItemName());
            System.out.println(index.getAndIncrement()+". "+item.getItemName()+blank+"|  W "+item.getPrice()+" |  "+ item.getDescription());
        });
        lastSentence("뒤로가기");
    }

    @Override
    public int selectMenuItem(String menuName) throws Exception {
        int maxProcessCount = 10000;
        Menu menu = menuRepository.findMenu(menuName);
        while (maxProcessCount > 0) {
            try {
                displayMenuItems(menuName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                maxProcessCount--;
            }
            try {
                int input = input();
                System.out.println("두 번째 입력: " + input);
                if(input == 0) return 0;
                if (input > 0 && input <= menu.getMenuItems().size()) {
                    return input;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                maxProcessCount--;
            }
        }
        throw new Exception("입력 횟수 초과");
    }

    @Override
    public void displayOrderList(List<CartItem> cartItems, double totalPrice) {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        AtomicInteger index = new AtomicInteger(1);
        cartItems.forEach(cartItem -> {
            String blank = blankFormat(cartItem.getName());
            System.out.println(index.getAndIncrement()+". "+cartItem.getName()+blank+"|  W "+cartItem.getPrice()+" |  "+ cartItem.getDescription());
        });
        for(CartItem cartItem : cartItems) {
            System.out.println(cartItem.getName()+" | W "+cartItem.getPrice()+" | "+cartItem.getDescription());
        }
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W "+totalPrice);
        System.out.println();
        System.out.println();
    }

    @Override
    public MenuItem addItemToCart(String menuName, int selectOption) throws Exception {
        int maxProcessCount = 10000;
        MenuItem menuItem = menuRepository.findMenuItemByIndex(menuName, selectOption);
        System.out.println("선택한 메뉴: "+ menuItem.getItemName()+" | W "+menuItem.getPrice()+" | "+menuItem.getDescription());
        while(maxProcessCount>0) {
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
            try {
                int input = selectOption("확인", "취소");
                if(input == 1) {
                    return menuItem;
                }
                else if(input ==2) {
                    return null;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                maxProcessCount--;
            }
        }
        throw new Exception("입력 횟수 초과");
    }

    @Override
    public int processOrder() throws Exception {
        int maxProcessCount = 10000;
        int input = 0;
        while(maxProcessCount>0) {
            try {
                input = selectOption("주문", "메뉴판");
                if(input == 1 || input == 2) return input;
            } catch (Exception e) {
                System.out.println(e.getMessage());;
                maxProcessCount--;
            }
        }
        throw new Exception("입력 횟수 초괴");
    }

    public Grade findGradeByIndex(int index) throws Exception {
        Grade[] grades = Grade.values();
        for(Grade grade : grades) {
            if(grade.getNumber() == index) return grade;
        }
        throw new Exception("인덱스와 일치하는 등급이 없습니다.");
    }

    @Override
    public Grade getDiscountInfo(Map<Grade, Double> discountAmount, String discountMark, double price) throws Exception {
        System.out.println("할인 정보를 입력하세요.");
        System.out.println(Grade.SPECIAL.getNumber()+"국가 유공자: " + discountAmount.get(Grade.SPECIAL)+ discountMark);
        System.out.println(Grade.SOLDIER.getNumber()+"군인: " + discountAmount.get(Grade.SOLDIER)+ discountMark);
        System.out.println(Grade.KID.getNumber()+"아동: " + discountAmount.get(Grade.KID)+ discountMark);
        System.out.println(Grade.STUDENT.getNumber()+"학생: " + discountAmount.get(Grade.STUDENT)+ discountMark);
        System.out.println(Grade.BASIC.getNumber()+"일반: " + discountAmount.get(Grade.BASIC)+ discountMark);
        int inputIndex = input();
        return findGradeByIndex(inputIndex);
    }

    /**
     * 1 또는 2의 값을 반환
     * @param firstOption 첫 번째 옵션, 값:1
     * @param secondOption 두 번째 옵션, 값:2
     * @return 정수
     * @throws Exception 정수 외 입력 예외처리
     */
    public int selectOption(String firstOption, String secondOption) throws Exception {
        System.out.println("1. "+firstOption+blankFormat("")+"2. "+secondOption);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new Exception("입력이 잘못되었습니다.");
        }
    }
    /**
     * 상품과 가격의 경계선을 일정하게 그어 주기 위한 공백설정
     * @param name 상품 이름
     * @return 공백 문자열
     */
    public String blankFormat(String name) {
        int lengthOfName = 20 - name.length();
        return " ".repeat(Math.max(0, lengthOfName));
    }

    /**
     * 입력 전 상황에 따른 문구 출력(종료 또는 뒤로가기)
     * @param action 상황
     */
    public void lastSentence(String action) {
        System.out.println("0.  "+ action);
    }
}
