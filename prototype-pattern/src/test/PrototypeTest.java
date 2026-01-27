package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prototype.Dragon;
import prototype.Drakon;
import prototype.Monster;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프로토타입 패턴 테스트")
public class PrototypeTest {

    @Test
    @DisplayName("복제된 객체는 원본과 다른 인스턴스이다")
    void testCopyCreatesDifferentInstance() throws CloneNotSupportedException {
        // Given
        Monster dragon = new Dragon("드래곤", true);

        // When
        Monster copy = dragon.copy();

        // Then
        assertThat(copy).isNotSameAs(dragon);
    }

    @Test
    @DisplayName("복제된 객체는 원본과 같은 속성을 가진다")
    void testCopyHasSameProperties() throws CloneNotSupportedException {
        // Given
        Monster dragon = new Dragon("드래곤", true);

        // When
        Monster copy = dragon.copy();

        // Then
        assertThat(copy.getName()).isEqualTo(dragon.getName());
        assertThat(copy.hasWings()).isEqualTo(dragon.hasWings());
        assertThat(copy.canBreatheFire()).isEqualTo(dragon.canBreatheFire());
        assertThat(copy.getNumHeads()).isEqualTo(dragon.getNumHeads());
    }

    @Test
    @DisplayName("복제본 수정이 원본에 영향을 주지 않는다")
    void testModifyingCopyDoesNotAffectOriginal() throws CloneNotSupportedException {
        // Given
        Monster original = new Dragon("원본 드래곤", true);
        Monster copy = original.copy();

        // When
        copy.setName("복제된 드래곤");

        // Then
        assertThat(original.getName()).isEqualTo("원본 드래곤");
        assertThat(copy.getName()).isEqualTo("복제된 드래곤");
    }

    @Test
    @DisplayName("Drakon은 독을 뿜을 수 있다")
    void testDrakonSpitPoison() {
        // Given
        Drakon drakon = new Drakon("히드라", 3, false);

        // When
        String result = drakon.spitPoison();

        // Then
        assertThat(result).isEqualTo("히드라이(가) 독을 뿜는다");
    }
}
