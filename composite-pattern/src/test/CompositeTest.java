package test;

import client.Waitress;
import component.MenuComponent;
import composite.Menu;
import leaf.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositeTest {

    private MenuComponent allMenus;
    private Waitress waitress;

    @BeforeEach
    void setUp() {
        // Given - 메뉴 구조 생성
        MenuComponent pancakeHouseMenu = new Menu("팬케이크 하우스 메뉴", "아침 식사");
        MenuComponent dinerMenu = new Menu("다이너 메뉴", "점심 식사");
        MenuComponent cafeMenu = new Menu("카페 메뉴", "저녁 식사");
        MenuComponent dessertMenu = new Menu("디저트 메뉴", "물론 디저트!");

        allMenus = new Menu("전체 메뉴", "모든 메뉴 통합");

        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        pancakeHouseMenu.add(new MenuItem(
                "K&B 팬케이크 아침식사",
                "스크램블 에그와 토스트를 곁들인 팬케이크",
                true,
                8500)
        );
        pancakeHouseMenu.add(new MenuItem(
                "레귤러 팬케이크 아침식사",
                "계란 후라이와 소시지를 곁들인 팬케이크",
                false,
                8500)
        );
        pancakeHouseMenu.add(new MenuItem(
                "블루베리 팬케이크",
                "신선한 블루베리와 블루베리 시럽으로 만든 팬케이크",
                true,
                10000)
        );
        pancakeHouseMenu.add(new MenuItem(
                "와플",
                "블루베리 또는 딸기를 선택할 수 있는 와플",
                true,
                10500)
        );
        dinerMenu.add(new MenuItem(
                "채식 BLT",
                "통밀빵에 (페이킨) 베이컨, 양상추, 토마토",
                true,
                8500)
        );
        dinerMenu.add(new MenuItem(
                "BLT",
                "통밀빵에 베이컨, 양상추, 토마토",
                false,
                8500)
        );
        dinerMenu.add(new MenuItem(
                "오늘의 수프",
                "감자 샐러드를 곁들인 오늘의 수프 한 그릇",
                false,
                9500)
        );
        dinerMenu.add(new MenuItem(
                "핫도그",
                "사우어크라우트, 렐리시, 양파, 치즈를 얹은 핫도그",
                false,
                9000)
        );
        dinerMenu.add(new MenuItem(
                "찐 야채와 현미밥",
                "현미밥 위에 찐 야채 모듬",
                true,
                11500)
        );
        dinerMenu.add(new MenuItem(
                "파스타",
                "마리나라 소스 스파게티와 사워도우 빵 한 조각",
                true,
                11000)
        );

        dinerMenu.add(dessertMenu);

        dessertMenu.add(new MenuItem(
                "애플 파이",
                "바닐라 아이스크림을 얹은 바삭한 크러스트의 애플 파이",
                true,
                4500)
        );
        dessertMenu.add(new MenuItem(
                "치즈케이크",
                "초콜릿 그레이엄 크러스트의 크리미한 뉴욕 치즈케이크",
                true,
                5500)
        );
        dessertMenu.add(new MenuItem(
                "소르베",
                "라즈베리와 라임 한 스쿱씩",
                true,
                5000)
        );

        cafeMenu.add(new MenuItem(
                "베지 버거와 에어 프라이",
                "통밀 번, 양상추, 토마토와 감자튀김을 곁들인 베지 버거",
                true,
                11500)
        );
        cafeMenu.add(new MenuItem(
                "오늘의 수프",
                "사이드 샐러드를 곁들인 오늘의 수프 한 컵",
                false,
                10500)
        );
        cafeMenu.add(new MenuItem(
                "부리토",
                "통 핀토 콩, 살사, 과카몰리를 곁들인 큰 부리토",
                true,
                12500)
        );

        waitress = new Waitress(allMenus);
    }

    @Test
    void testPrintVegetarianMenu() {
        // Given - 채식 메뉴 아이템들이 포함된 메뉴 구조가 설정됨

        // When - 채식 메뉴만 출력
        String result = waitress.printVegetarianMenu();

        // Then - 채식 메뉴만 포함되어야 함
        assertThat(result).contains("채식 메뉴");
        assertThat(result).contains("K&B 팬케이크 아침식사");
        assertThat(result).contains("블루베리 팬케이크");
        assertThat(result).contains("와플");
        assertThat(result).contains("채식 BLT");
        assertThat(result).contains("찐 야채와 현미밥");
        assertThat(result).contains("파스타");

        // Then - 비채식 메뉴는 포함되지 않아야 함
        assertThat(result).doesNotContain("레귤러 팬케이크 아침식사");
        assertThat(result).doesNotContain("핫도그");
    }

    @Test
    void testPrintAllMenus() {
        // Given - 전체 메뉴 구조가 설정됨

        // When - 전체 메뉴 출력
        String result = waitress.printMenu();

        // Then - 모든 메뉴가 포함되어야 함
        assertThat(result).contains("전체 메뉴");
        assertThat(result).contains("팬케이크 하우스 메뉴");
        assertThat(result).contains("다이너 메뉴");
        assertThat(result).contains("카페 메뉴");
        assertThat(result).contains("디저트 메뉴");
    }

    @Test
    void testMenuItemPrice() {
        // Given - 메뉴 아이템이 생성됨
        MenuItem menuItem = new MenuItem("테스트 메뉴", "테스트 설명", true, 10000);

        // When - 가격 조회
        double price = menuItem.getPrice();

        // Then - 올바른 가격이 반환되어야 함
        assertThat(price).isEqualTo(10000);
    }

    @Test
    void testMenuItemVegetarian() {
        // Given - 채식 메뉴 아이템이 생성됨
        MenuItem vegetarianItem = new MenuItem("채식 메뉴", "채식 설명", true, 10000);
        MenuItem nonVegetarianItem = new MenuItem("일반 메뉴", "일반 설명", false, 10000);

        // When - 채식 여부 확인
        boolean isVegetarian1 = vegetarianItem.isVegetarian();
        boolean isVegetarian2 = nonVegetarianItem.isVegetarian();

        // Then - 채식 여부가 올바르게 반환되어야 함
        assertThat(isVegetarian1).isTrue();
        assertThat(isVegetarian2).isFalse();
    }

    @Test
    void testCompositeStructure() {
        // Given - 메뉴와 메뉴 아이템이 생성됨
        MenuComponent menu = new Menu("테스트 메뉴", "테스트 설명");
        MenuComponent item1 = new MenuItem("아이템1", "설명1", true, 5000);
        MenuComponent item2 = new MenuItem("아이템2", "설명2", false, 6000);

        // When - 메뉴에 아이템 추가
        menu.add(item1);
        menu.add(item2);

        // Then - 자식 메뉴 아이템을 조회할 수 있어야 함
        assertThat(menu.getChild(0)).isEqualTo(item1);
        assertThat(menu.getChild(1)).isEqualTo(item2);
    }

    @Test
    void testMenuItemPrint() {
        // Given - 채식 메뉴 아이템이 생성됨
        MenuItem menuItem = new MenuItem("테스트 메뉴", "테스트 설명", true, 10000);

        // When - 메뉴 아이템 출력
        String result = menuItem.print();

        // Then - 올바른 형식으로 출력되어야 함
        assertThat(result).contains("테스트 메뉴");
        assertThat(result).contains("(채식)");
        assertThat(result).contains("10000.0원");
        assertThat(result).contains("테스트 설명");
    }
}
