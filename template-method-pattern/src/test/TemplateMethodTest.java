package test;

import concrete.Coffee;
import concrete.Tea;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateMethodTest {

    @Test
    public void testCoffeeWithCondiments() {
        // Given - 첨가물을 원하는 커피 음료 생성
        Coffee coffee = new Coffee();

        // When - 커피 레시피를 준비
        String result = coffee.prepareRecipe();

        // Then - 모든 단계가 올바른 순서로 실행되어야 함
        assertThat(result).contains("물 끓이는 중");
        assertThat(result).contains("필터로 커피를 우려내는 중");
        assertThat(result).contains("컵에 따르는 중");
        assertThat(result).contains("설탕과 우유를 추가하는 중");
    }

    @Test
    public void testCoffeeWithoutCondiments() {
        // Given - 첨가물을 원하지 않는 커피 음료 생성
        Coffee coffee = new Coffee(false);

        // When - 커피 레시피를 준비
        String result = coffee.prepareRecipe();

        // Then - 첨가물 추가 단계가 실행되지 않아야 함
        assertThat(result).contains("물 끓이는 중");
        assertThat(result).contains("필터로 커피를 우려내는 중");
        assertThat(result).contains("컵에 따르는 중");
        assertThat(result).doesNotContain("설탕과 우유를 추가하는 중");
    }

    @Test
    public void testTeaWithCondiments() {
        // Given - 첨가물을 원하는 차 음료 생성
        Tea tea = new Tea();

        // When - 차 레시피를 준비
        String result = tea.prepareRecipe();

        // Then - 모든 단계가 올바른 순서로 실행되어야 함
        assertThat(result).contains("물 끓이는 중");
        assertThat(result).contains("차를 우려내는 중");
        assertThat(result).contains("컵에 따르는 중");
        assertThat(result).contains("레몬을 추가하는 중");
    }

    @Test
    public void testTeaWithoutCondiments() {
        // Given - 첨가물을 원하지 않는 차 음료 생성
        Tea tea = new Tea(false);

        // When - 차 레시피를 준비
        String result = tea.prepareRecipe();

        // Then - 첨가물 추가 단계가 실행되지 않아야 함
        assertThat(result).contains("물 끓이는 중");
        assertThat(result).contains("차를 우려내는 중");
        assertThat(result).contains("컵에 따르는 중");
        assertThat(result).doesNotContain("레몬을 추가하는 중");
    }
}
