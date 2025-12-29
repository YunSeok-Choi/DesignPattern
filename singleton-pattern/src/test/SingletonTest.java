package test;

import domain.ChocolateBoiler;
import domain.ChocolateBoilerEnum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    public void testChocolateBoilerSingleton() {
        // Given & When - 두 번 getInstance() 호출
        ChocolateBoiler boiler1 = ChocolateBoiler.getInstance();
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();

        // Then - 같은 인스턴스여야 함
        assertThat(boiler1).isSameAs(boiler2);
        assertThat(System.identityHashCode(boiler1))
                .isEqualTo(System.identityHashCode(boiler2));
    }

    @Test
    public void testChocolateBoilerEnumSingleton() {
        // Given & When - Enum 싱글톤 인스턴스 접근
        ChocolateBoilerEnum boiler1 = ChocolateBoilerEnum.INSTANCE;
        ChocolateBoilerEnum boiler2 = ChocolateBoilerEnum.INSTANCE;

        // Then - 같은 인스턴스여야 함
        assertThat(boiler1).isSameAs(boiler2);
        assertThat(System.identityHashCode(boiler1))
                .isEqualTo(System.identityHashCode(boiler2));
    }

    @Test
    public void testChocolateBoilerWorkflow() {
        // Given - 초콜릿 보일러 인스턴스 생성
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();

        // When & Then - fill -> boil -> drain 순서로 동작
        String fillResult = boiler.fill();
        assertThat(fillResult).isEqualTo("보일러에 우유와 초콜릿 재료 추가");
        assertThat(boiler.isEmpty()).isFalse();

        String boilResult = boiler.boil();
        assertThat(boilResult).isEqualTo("재료를 끓임");
        assertThat(boiler.isBoiled()).isTrue();

        String drainResult = boiler.drain();
        assertThat(drainResult).isEqualTo("끓인 재료를 다음 단계로 넘김");
        assertThat(boiler.isEmpty()).isTrue();
    }

    @Test
    public void testChocolateBoilerEnumWorkflow() {
        // Given - Enum 초콜릿 보일러 인스턴스
        ChocolateBoilerEnum boiler = ChocolateBoilerEnum.INSTANCE;

        // When & Then - fill -> boil -> drain 순서로 동작
        String fillResult = boiler.fill();
        assertThat(fillResult).isEqualTo("보일러에 우유와 초콜릿 재료 추가");

        String boilResult = boiler.boil();
        assertThat(boilResult).isEqualTo("재료를 끓임");

        String drainResult = boiler.drain();
        assertThat(drainResult).isEqualTo("끓인 재료를 다음 단계로 넘김");
    }

    @Test
    public void testInvalidOperations() {
        // Given - 새로운 보일러 인스턴스
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        boiler.fill();

        // When & Then - 이미 찬 보일러에 fill 시도
        String fillAgain = boiler.fill();
        assertThat(fillAgain).isEqualTo("보일러가 이미 가득 차 있습니다");

        // When & Then - 끓이지 않은 상태에서 drain 시도
        String drainWithoutBoil = boiler.drain();
        assertThat(drainWithoutBoil).isEqualTo("보일러가 비어있거나 재료가 끓지 않았습니다");
    }
}
