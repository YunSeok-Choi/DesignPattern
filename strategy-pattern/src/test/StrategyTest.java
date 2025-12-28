package test;

import behavior.AxeBehavior;
import behavior.BowAndArrowBehavior;
import behavior.SwordBehavior;
import role.Character;
import role.King;
import role.Knight;
import role.Troll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrategyTest {

    @Test
    public void testKingWithSword() {
        // Given - 검을 사용하는 왕 캐릭터 생성
        Character king = new King(new SwordBehavior());

        // When - 전투 수행
        String result = king.fight();

        // Then - 검을 사용해야 함
        assertThat(king.getName()).isEqualTo("KING");
        assertThat(result).isEqualTo("검 사용");
    }

    @Test
    public void testKnightWithBowAndArrow() {
        // Given - 활을 사용하는 기사 캐릭터 생성
        Character knight = new Knight(new BowAndArrowBehavior());

        // When - 전투 수행
        String result = knight.fight();

        // Then - 활을 사용해야 함
        assertThat(knight.getName()).isEqualTo("KNIGHT");
        assertThat(result).isEqualTo("활 쏘기 사용");
    }

    @Test
    public void testTrollWithAxe() {
        // Given - 도끼를 사용하는 트롤 캐릭터 생성
        Character troll = new Troll(new AxeBehavior());

        // When - 전투 수행
        String result = troll.fight();

        // Then - 도끼를 사용해야 함
        assertThat(troll.getName()).isEqualTo("TROLL");
        assertThat(result).isEqualTo("도끼 사용");
    }

    @Test
    public void testChangeWeapon() {
        // Given - 활을 사용하는 기사 캐릭터 생성
        Character knight = new Knight(new BowAndArrowBehavior());

        // When - 무기를 검으로 변경
        knight.changeWeapon(new SwordBehavior());
        String result = knight.fight();

        // Then - 검을 사용해야 함
        assertThat(result).isEqualTo("검 사용");
    }

    @Test
    public void testMultipleWeaponChanges() {
        // Given - 검을 사용하는 왕 캐릭터 생성
        Character king = new King(new SwordBehavior());

        // When & Then - 여러 번 무기를 변경하며 테스트
        assertThat(king.fight()).isEqualTo("검 사용");

        king.changeWeapon(new BowAndArrowBehavior());
        assertThat(king.fight()).isEqualTo("활 쏘기 사용");

        king.changeWeapon(new AxeBehavior());
        assertThat(king.fight()).isEqualTo("도끼 사용");
    }
}
