package test;

import factory.TVFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import remote.GenericRemote;
import remote.SpecialRemote;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("브릿지 패턴 테스트")
public class BridgePatternTest {

    private TVFactory tvFactory;

    @BeforeEach
    void setUp() {
        tvFactory = new TVFactory();
    }

    @Test
    @DisplayName("같은 리모컨으로 다른 TV를 제어할 수 있다")
    void testSameRemoteDifferentTV() {
        // Given - 제네릭 리모컨 생성
        GenericRemote remote = new GenericRemote(tvFactory);

        // When - LG TV 연결 후 제어
        remote.setTV("LG");
        String lgOn = remote.on();
        String lgChannel = remote.nextChannel();

        // Then - LG TV가 제어되어야 함
        assertThat(lgOn).isEqualTo("LG TV 전원을 켭니다");
        assertThat(lgChannel).isEqualTo("LG TV 채널을 2번으로 설정합니다");

        // When - 같은 리모컨으로 Sony TV 연결 후 제어
        remote.setTV("Sony");
        String sonyOn = remote.on();
        String sonyChannel = remote.nextChannel();

        // Then - Sony TV가 제어되어야 함
        assertThat(sonyOn).isEqualTo("Sony TV 전원을 켭니다");
        assertThat(sonyChannel).isEqualTo("Sony TV 채널을 1번으로 설정합니다");
    }

    @Test
    @DisplayName("다른 리모컨으로 같은 TV를 제어할 수 있다")
    void testDifferentRemoteSameTV() {
        // Given - 두 종류의 리모컨 생성
        GenericRemote genericRemote = new GenericRemote(tvFactory);
        SpecialRemote specialRemote = new SpecialRemote(tvFactory);

        // When - 둘 다 LG TV 연결
        genericRemote.setTV("LG");
        specialRemote.setTV("LG");

        // Then - 각 리모컨의 고유 기능으로 같은 TV를 제어할 수 있어야 함
        assertThat(genericRemote.nextChannel()).isEqualTo("LG TV 채널을 2번으로 설정합니다");
        assertThat(genericRemote.prevChannel()).isEqualTo("LG TV 채널을 1번으로 설정합니다");

        assertThat(specialRemote.up()).isEqualTo("LG TV 채널을 2번으로 설정합니다");
        assertThat(specialRemote.down()).isEqualTo("LG TV 채널을 1번으로 설정합니다");
    }

    @Test
    @DisplayName("추상화(리모컨)와 구현(TV)이 독립적으로 확장 가능하다")
    void testAbstractionAndImplementationIndependence() {
        // Given - 각각 다른 조합의 리모컨-TV 연결
        GenericRemote genericWithLG = new GenericRemote(tvFactory);
        GenericRemote genericWithSony = new GenericRemote(tvFactory);
        SpecialRemote specialWithLG = new SpecialRemote(tvFactory);
        SpecialRemote specialWithSony = new SpecialRemote(tvFactory);

        genericWithLG.setTV("LG");
        genericWithSony.setTV("Sony");
        specialWithLG.setTV("LG");
        specialWithSony.setTV("Sony");

        // When & Then - 모든 조합이 독립적으로 동작해야 함
        assertThat(genericWithLG.on()).isEqualTo("LG TV 전원을 켭니다");
        assertThat(genericWithSony.on()).isEqualTo("Sony TV 전원을 켭니다");
        assertThat(specialWithLG.on()).isEqualTo("LG TV 전원을 켭니다");
        assertThat(specialWithSony.on()).isEqualTo("Sony TV 전원을 켭니다");
    }
}
