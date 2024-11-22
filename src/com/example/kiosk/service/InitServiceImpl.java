package com.example.kiosk.service;

import com.example.kiosk.domain.Food;
import com.example.kiosk.domain.MenuItem;
import com.example.kiosk.repository.MenuItemRepository;

import java.util.Arrays;

public class InitServiceImpl implements InitService{
    private final MenuItemRepository menuItemRepository;

    public InitServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void init() {
        initBurger();
        initDrink();
        initDessert();
    }

    private void initBurger() {
        Object[][] initData =
                {{"ShackBurger",6.9,"토마토, 양상추, 쉑소스가 토핑된 치즈버거"},
                        {"SmokeShack",8.9,"베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"},
                        {"Cheeseburger",6.9,"포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"},
                        {"Hamburger",5.4,"비프패티를 기반으로 야채가 들어간 기본버거"}};
        saveMenuItem(initData,Food.BURGER);
    }



    private void initDrink() {
        Object[][] initData =
                {{"Americano",4.9,"신선한 원두를 사용해 은은한 향이 느껴지는 아메리카노"},
                        {"ChocolateLatte",5.4,"진한 다크 초코를 녹여 만든 초콜릿 라떼"},
                        {"GrapefruitAde",4.5,"상큼한 자몽이 첨가된 상큼 톡톡 자몽에이드"},
                        {"AppleHoneyTea",4.2,"상큼한 사과와 달콤한 꿀이 첨가된 애플허니티"}};
        saveMenuItem(initData,Food.DRINK);
    }

    private void initDessert() {
        Object[][] initData =
                {{"FriedPotatoes",3.8,"쫄깃하고 짭짤한 그 맛! 감자튀김!"},
                        {"PieceChicken",2.1,"바삭하고 육즙이 가득 담긴 치킨 한 조각"},
                        {"Croissant",4.2,"바삭한 식감에 고소한 버터 향이 나는 크로아상"},
                        {"StrawberryCake",6.1,"새콤달콤 딸기가 토핑된 딸기 생크림 케이크"}};
        saveMenuItem(initData,Food.DESSERT);
    }
    private void saveMenuItem(Object[][] initData, Food foodKind) {
        Arrays.stream(initData).parallel().forEachOrdered(data-> {//병렬 스트림을 사용하여 많은 데이터를 효율적으로 처리, 순서를 위해 ordered추가
            try{
                MenuItem menuItem = new MenuItem((String)data[0],(double)data[1],(String)data[2]);
                menuItemRepository.save(menuItem, foodKind);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
