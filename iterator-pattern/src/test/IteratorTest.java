package test;

import aggregate.DinerMenu;
import aggregate.Menu;
import aggregate.PancakeHouseMenu;
import client.Waitress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IteratorTest {

    private Menu pancakeHouseMenu;
    private Menu dinerMenu;
    private Waitress waitress;

    @BeforeEach
    public void setUp() {
        pancakeHouseMenu = new PancakeHouseMenu();
        dinerMenu = new DinerMenu();
        waitress = new Waitress(pancakeHouseMenu, dinerMenu);
    }

    @Test
    public void testPrintMenu() {
        // Given - 두 메뉴가 설정된 웨이트리스

        // When - 전체 메뉴 출력
        String result = waitress.printMenu();

        // Then - 아침 메뉴와 점심 메뉴가 모두 출력되어야 함
        assertThat(result).contains("메뉴");
        assertThat(result).contains("아침 메뉴");
        assertThat(result).contains("점심 메뉴");
        assertThat(result).contains("K&B 팬케이크 조식");
        assertThat(result).contains("채식 BLT");
    }

    @Test
    public void testPrintVegetarianMenu() {
        // Given - 두 메뉴가 설정된 웨이트리스

        // When - 채식 메뉴만 출력
        String result = waitress.printVegetarianMenu();

        // Then - 채식 메뉴만 포함되어야 함
        assertThat(result).contains("채식 메뉴");
        assertThat(result).contains("K&B 팬케이크 조식");
        assertThat(result).contains("블루베리 팬케이크");
        assertThat(result).contains("채식 BLT");
        assertThat(result).contains("찐 야채와 현미밥");

        // 비채식 메뉴는 포함되지 않아야 함
        assertThat(result).doesNotContain("레귤러 팬케이크 조식");
        assertThat(result).doesNotContain("핫도그");
    }

    @Test
    public void testIsItemVegetarian() {
        // Given - 두 메뉴가 설정된 웨이트리스

        // When & Then - 채식 메뉴 확인
        assertThat(waitress.isItemVegetarian("K&B 팬케이크 조식")).isTrue();
        assertThat(waitress.isItemVegetarian("블루베리 팬케이크")).isTrue();
        assertThat(waitress.isItemVegetarian("채식 BLT")).isTrue();

        // When & Then - 비채식 메뉴 확인
        assertThat(waitress.isItemVegetarian("레귤러 팬케이크 조식")).isFalse();
        assertThat(waitress.isItemVegetarian("BLT")).isFalse();
        assertThat(waitress.isItemVegetarian("핫도그")).isFalse();
    }

}

