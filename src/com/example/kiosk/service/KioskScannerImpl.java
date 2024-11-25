package com.example.kiosk.service;
import com.example.kiosk.domain.Menu;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.MenuRepository;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class KioskScannerImpl implements KioskScanner {
    private final MenuRepository menuRepository;
    Scanner scanner = new Scanner(System.in);

    public KioskScannerImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    @Override
    public void printSelectedMenuItem(String name, int index) throws Exception {
        try{
            MenuItem menuItem = menuRepository.findMenuItemByIndex(name, index);
            System.out.println("선택한 메뉴: "+ menuItem.getItemName()+" | W "+menuItem.getPrice()+" | "+menuItem.getDescription());
            System.out.println();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Override
    public void printMenu() {
        List<Menu> menus = menuRepository.getAllMenus();
        System.out.println("[ MAIN MENU ]");
        menus.forEach(menu ->{
            System.out.println(menu.getNumber()+" "+menu.getName());
        });
    }

    @Override
    public void printMenuItems(String name) throws Exception {
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
    @Override
    public void lastSentence(String action) {
        System.out.println("0.  "+ action);
    }

    @Override
    public int input() throws Exception {
        System.out.print("출력 된 번호를 선택하세요: ");
        try{
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        } catch (Exception e) {
            scanner.nextLine();
            throw new Exception("입력이 잘못되었습니다! 출력된 숫자 중 하나를 골라주세요.");
        }
    }
}
