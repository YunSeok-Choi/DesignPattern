package test;

import adapter.Goose;
import adapter.GooseAdapter;
import composite.Flock;
import decorator.QuackCounter;
import duck.DecoyDuck;
import duck.DuckCall;
import duck.MallardDuck;
import duck.Quackable;
import duck.RedheadDuck;
import duck.RubberDuck;
import factory.AbstractDuckFactory;
import factory.CountingDuckFactory;
import factory.DuckFactory;
import observer.Quackologist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("복합 패턴 테스트")
public class CombinePatternTest {

    @BeforeEach
    void setUp() {
        QuackCounter.resetQuacks();
    }

    @Nested
    @DisplayName("오리 기본 동작 테스트")
    class DuckBasicTest {

        @Test
        @DisplayName("청둥오리는 꽥꽥 소리를 낸다")
        void testMallardDuckQuack() {
            // Given - 청둥오리 생성
            Quackable mallardDuck = new MallardDuck();

            // When - 소리 내기
            String sound = mallardDuck.quack();

            // Then - 꽥꽥 소리가 나야 함
            assertThat(sound).isEqualTo("꽥꽥");
            assertThat(mallardDuck.toString()).isEqualTo("청둥오리");
        }

        @Test
        @DisplayName("붉은머리오리는 꽥꽥 소리를 낸다")
        void testRedheadDuckQuack() {
            // Given - 붉은머리오리 생성
            Quackable redheadDuck = new RedheadDuck();

            // When - 소리 내기
            String sound = redheadDuck.quack();

            // Then - 꽥꽥 소리가 나야 함
            assertThat(sound).isEqualTo("꽥꽥");
            assertThat(redheadDuck.toString()).isEqualTo("붉은머리오리");
        }

        @Test
        @DisplayName("고무오리는 삑삑 소리를 낸다")
        void testRubberDuckQuack() {
            // Given - 고무오리 생성
            Quackable rubberDuck = new RubberDuck();

            // When - 소리 내기
            String sound = rubberDuck.quack();

            // Then - 삑삑 소리가 나야 함
            assertThat(sound).isEqualTo("삑삑");
            assertThat(rubberDuck.toString()).isEqualTo("고무오리");
        }

        @Test
        @DisplayName("가짜오리는 조용하다")
        void testDecoyDuckQuack() {
            // Given - 가짜오리 생성
            Quackable decoyDuck = new DecoyDuck();

            // When - 소리 내기
            String sound = decoyDuck.quack();

            // Then - 조용해야 함
            assertThat(sound).isEqualTo("<< 조용 >>");
            assertThat(decoyDuck.toString()).isEqualTo("가짜오리");
        }

        @Test
        @DisplayName("오리호출기는 꽉 소리를 낸다")
        void testDuckCallQuack() {
            // Given - 오리호출기 생성
            Quackable duckCall = new DuckCall();

            // When - 소리 내기
            String sound = duckCall.quack();

            // Then - 꽉 소리가 나야 함
            assertThat(sound).isEqualTo("꽉");
            assertThat(duckCall.toString()).isEqualTo("오리호출기");
        }
    }

    @Nested
    @DisplayName("Adapter 패턴 테스트")
    class AdapterPatternTest {

        @Test
        @DisplayName("거위는 끼룩 소리를 낸다")
        void testGooseHonk() {
            // Given - 거위 생성
            Goose goose = new Goose();

            // When - 소리 내기
            String sound = goose.honk();

            // Then - 끼룩 소리가 나야 함
            assertThat(sound).isEqualTo("끼룩");
            assertThat(goose.toString()).isEqualTo("거위");
        }

        @Test
        @DisplayName("어댑터를 통해 거위가 오리처럼 동작한다")
        void testGooseAdapterQuack() {
            // Given - 거위 어댑터 생성
            Quackable gooseDuck = new GooseAdapter(new Goose());

            // When - quack() 호출 (내부적으로 honk() 실행)
            String sound = gooseDuck.quack();

            // Then - 거위 소리(끼룩)가 나야 함
            assertThat(sound).isEqualTo("끼룩");
            assertThat(gooseDuck.toString()).isEqualTo("오리인 척 하는 거위");
        }

        @Test
        @DisplayName("어댑터로 감싼 거위도 Quackable 타입으로 사용 가능하다")
        void testGooseAdapterIsQuackable() {
            // Given - 거위 어댑터 생성
            Quackable gooseDuck = new GooseAdapter(new Goose());

            // When & Then - Quackable 인터페이스의 모든 메서드 사용 가능
            assertThat(gooseDuck).isInstanceOf(Quackable.class);
            assertThat(gooseDuck.quack()).isNotNull();
        }
    }

    @Nested
    @DisplayName("Decorator 패턴 테스트")
    class DecoratorPatternTest {

        @Test
        @DisplayName("QuackCounter로 감싸면 quack 횟수가 카운트된다")
        void testQuackCounter() {
            // Given - QuackCounter로 감싼 오리 생성
            Quackable duck = new QuackCounter(new MallardDuck());

            // When - 3번 소리 내기
            duck.quack();
            duck.quack();
            duck.quack();

            // Then - 카운트가 3이어야 함
            assertThat(QuackCounter.getQuacks()).isEqualTo(3);
        }

        @Test
        @DisplayName("QuackCounter로 감싸도 원래 소리가 그대로 반환된다")
        void testQuackCounterPreservesSound() {
            // Given - QuackCounter로 감싼 오리 생성
            Quackable mallard = new QuackCounter(new MallardDuck());
            Quackable rubber = new QuackCounter(new RubberDuck());

            // When - 소리 내기
            String mallardSound = mallard.quack();
            String rubberSound = rubber.quack();

            // Then - 원래 소리가 그대로 반환되어야 함
            assertThat(mallardSound).isEqualTo("꽥꽥");
            assertThat(rubberSound).isEqualTo("삑삑");
        }

        @Test
        @DisplayName("여러 오리의 quack 횟수가 누적된다")
        void testQuackCounterAccumulates() {
            // Given - 여러 오리를 QuackCounter로 감싸서 생성
            Quackable mallard = new QuackCounter(new MallardDuck());
            Quackable redhead = new QuackCounter(new RedheadDuck());
            Quackable rubber = new QuackCounter(new RubberDuck());

            // When - 각 오리가 소리를 냄
            mallard.quack();
            mallard.quack();
            redhead.quack();
            rubber.quack();
            rubber.quack();
            rubber.quack();

            // Then - 총 카운트는 6이어야 함
            assertThat(QuackCounter.getQuacks()).isEqualTo(6);
        }

        @Test
        @DisplayName("QuackCounter를 리셋하면 카운트가 0이 된다")
        void testQuackCounterReset() {
            // Given - 오리가 소리를 냄
            Quackable duck = new QuackCounter(new MallardDuck());
            duck.quack();
            duck.quack();
            assertThat(QuackCounter.getQuacks()).isEqualTo(2);

            // When - 카운터 리셋
            QuackCounter.resetQuacks();

            // Then - 카운트가 0이어야 함
            assertThat(QuackCounter.getQuacks()).isEqualTo(0);
        }

        @Test
        @DisplayName("QuackCounter로 감싸도 toString은 원래 오리 이름을 반환한다")
        void testQuackCounterToString() {
            // Given - QuackCounter로 감싼 오리 생성
            Quackable duck = new QuackCounter(new MallardDuck());

            // When - toString 호출
            String name = duck.toString();

            // Then - 원래 오리 이름이 반환되어야 함
            assertThat(name).isEqualTo("청둥오리");
        }
    }

    @Nested
    @DisplayName("Composite 패턴 테스트")
    class CompositePatternTest {

        @Test
        @DisplayName("Flock은 여러 오리의 소리를 모두 포함한다")
        void testFlockQuack() {
            // Given - 오리 무리 생성
            Flock flock = new Flock();
            flock.add(new MallardDuck());
            flock.add(new RubberDuck());
            flock.add(new DuckCall());

            // When - 무리가 소리를 냄
            String sounds = flock.quack();

            // Then - 모든 오리의 소리가 포함되어야 함
            assertThat(sounds).contains("꽥꽥");
            assertThat(sounds).contains("삑삑");
            assertThat(sounds).contains("꽉");
        }

        @Test
        @DisplayName("Flock 안에 Flock을 넣을 수 있다")
        void testNestedFlock() {
            // Given - 중첩된 오리 무리 생성
            Flock mainFlock = new Flock();
            Flock subFlock = new Flock();

            subFlock.add(new MallardDuck());
            subFlock.add(new MallardDuck());

            mainFlock.add(new RubberDuck());
            mainFlock.add(subFlock);

            // When - 무리가 소리를 냄
            String sounds = mainFlock.quack();

            // Then - 모든 오리의 소리가 포함되어야 함
            assertThat(sounds).isEqualTo("삑삑\n꽥꽥\n꽥꽥");
        }

        @Test
        @DisplayName("빈 Flock은 빈 문자열을 반환한다")
        void testEmptyFlock() {
            // Given - 빈 오리 무리 생성
            Flock flock = new Flock();

            // When - 무리가 소리를 냄
            String sounds = flock.quack();

            // Then - 빈 문자열이어야 함
            assertThat(sounds).isEmpty();
        }

        @Test
        @DisplayName("Flock의 toString은 오리 무리를 반환한다")
        void testFlockToString() {
            // Given - 오리 무리 생성
            Flock flock = new Flock();

            // When - toString 호출
            String name = flock.toString();

            // Then - 오리 무리가 반환되어야 함
            assertThat(name).isEqualTo("오리 무리");
        }
    }

    @Nested
    @DisplayName("Abstract Factory 패턴 테스트")
    class FactoryPatternTest {

        @Test
        @DisplayName("DuckFactory는 일반 오리를 생성한다")
        void testDuckFactory() {
            // Given - 일반 오리 팩토리 생성
            AbstractDuckFactory factory = new DuckFactory();

            // When - 오리들 생성
            Quackable mallard = factory.createMallardDuck();
            Quackable redhead = factory.createRedheadDuck();
            Quackable duckCall = factory.createDuckCall();
            Quackable rubber = factory.createRubberDuck();

            // Then - 각 오리가 올바른 소리를 내야 함
            assertThat(mallard.quack()).isEqualTo("꽥꽥");
            assertThat(redhead.quack()).isEqualTo("꽥꽥");
            assertThat(duckCall.quack()).isEqualTo("꽉");
            assertThat(rubber.quack()).isEqualTo("삑삑");
        }

        @Test
        @DisplayName("CountingDuckFactory는 카운팅되는 오리를 생성한다")
        void testCountingDuckFactory() {
            // Given - 카운팅 오리 팩토리 생성
            AbstractDuckFactory factory = new CountingDuckFactory();

            // When - 오리들 생성하고 소리 내기
            Quackable mallard = factory.createMallardDuck();
            Quackable redhead = factory.createRedheadDuck();
            Quackable duckCall = factory.createDuckCall();
            Quackable rubber = factory.createRubberDuck();

            mallard.quack();
            redhead.quack();
            duckCall.quack();
            rubber.quack();

            // Then - 카운트가 4여야 함
            assertThat(QuackCounter.getQuacks()).isEqualTo(4);
        }

        @Test
        @DisplayName("DuckFactory로 생성한 오리는 카운팅되지 않는다")
        void testDuckFactoryNoCount() {
            // Given - 일반 오리 팩토리 생성
            AbstractDuckFactory factory = new DuckFactory();

            // When - 오리들 생성하고 소리 내기
            Quackable mallard = factory.createMallardDuck();
            mallard.quack();
            mallard.quack();
            mallard.quack();

            // Then - 카운트가 0이어야 함 (카운팅 안 됨)
            assertThat(QuackCounter.getQuacks()).isEqualTo(0);
        }

        @Test
        @DisplayName("팩토리를 교체하면 생성되는 오리 타입이 바뀐다")
        void testFactoryInterchangeability() {
            // Given - 두 종류의 팩토리
            AbstractDuckFactory normalFactory = new DuckFactory();
            AbstractDuckFactory countingFactory = new CountingDuckFactory();

            // When - 각 팩토리로 오리 생성
            Quackable normalDuck = normalFactory.createMallardDuck();
            Quackable countingDuck = countingFactory.createMallardDuck();

            normalDuck.quack();
            countingDuck.quack();

            // Then - 카운팅 팩토리로 만든 오리만 카운트됨
            assertThat(QuackCounter.getQuacks()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Observer 패턴 테스트")
    class ObserverPatternTest {

        @Test
        @DisplayName("Quackologist는 오리가 소리낼 때 알림을 받는다")
        void testQuackologistObservation() {
            // Given - 오리와 관찰자 생성
            MallardDuck duck = new MallardDuck();
            Quackologist quackologist = new Quackologist();
            duck.registerObserver(quackologist);

            // When - 오리가 소리를 냄
            duck.quack();

            // Then - 관찰자의 update 결과 확인
            String observation = quackologist.update(duck);
            assertThat(observation).isEqualTo("오리학자: 청둥오리이(가) 소리를 냈습니다.");
        }

        @Test
        @DisplayName("Quackologist의 toString은 오리학자를 반환한다")
        void testQuackologistToString() {
            // Given - 관찰자 생성
            Quackologist quackologist = new Quackologist();

            // When - toString 호출
            String name = quackologist.toString();

            // Then - 오리학자가 반환되어야 함
            assertThat(name).isEqualTo("오리학자");
        }

        @Test
        @DisplayName("Flock에 등록된 관찰자는 모든 오리를 관찰한다")
        void testFlockObserver() {
            // Given - 오리 무리와 관찰자 생성
            Flock flock = new Flock();
            MallardDuck mallard = new MallardDuck();
            RubberDuck rubber = new RubberDuck();

            flock.add(mallard);
            flock.add(rubber);

            Quackologist quackologist = new Quackologist();
            flock.registerObserver(quackologist);

            // When - 무리가 소리를 냄
            flock.quack();

            // Then - 각 오리에 대한 관찰 결과를 확인할 수 있음
            assertThat(quackologist.update(mallard)).contains("청둥오리");
            assertThat(quackologist.update(rubber)).contains("고무오리");
        }

        @Test
        @DisplayName("어댑터로 감싼 거위도 관찰 가능하다")
        void testAdaptedGooseObservation() {
            // Given - 거위 어댑터와 관찰자 생성
            GooseAdapter gooseDuck = new GooseAdapter(new Goose());
            Quackologist quackologist = new Quackologist();
            gooseDuck.registerObserver(quackologist);

            // When - 거위(어댑터)가 소리를 냄
            gooseDuck.quack();

            // Then - 관찰자가 거위를 관찰함
            String observation = quackologist.update(gooseDuck);
            assertThat(observation).contains("오리인 척 하는 거위");
        }
    }

    @Nested
    @DisplayName("통합 시뮬레이션 테스트")
    class IntegrationTest {

        @Test
        @DisplayName("DuckSimulator 시나리오 테스트")
        void testDuckSimulatorScenario() {
            // Given - CountingDuckFactory로 오리들 생성
            AbstractDuckFactory duckFactory = new CountingDuckFactory();

            Quackable redheadDuck = duckFactory.createRedheadDuck();
            Quackable duckCall = duckFactory.createDuckCall();
            Quackable rubberDuck = duckFactory.createRubberDuck();
            Quackable gooseDuck = new GooseAdapter(new Goose());

            // 오리 무리 생성
            Flock flockOfDucks = new Flock();
            flockOfDucks.add(redheadDuck);
            flockOfDucks.add(duckCall);
            flockOfDucks.add(rubberDuck);
            flockOfDucks.add(gooseDuck);

            // 청둥오리 무리 생성
            Flock flockOfMallards = new Flock();
            flockOfMallards.add(duckFactory.createMallardDuck());
            flockOfMallards.add(duckFactory.createMallardDuck());
            flockOfMallards.add(duckFactory.createMallardDuck());
            flockOfMallards.add(duckFactory.createMallardDuck());

            flockOfDucks.add(flockOfMallards);

            // 관찰자 등록
            Quackologist quackologist = new Quackologist();
            flockOfDucks.registerObserver(quackologist);

            // When - 전체 무리가 소리를 냄
            String sounds = flockOfDucks.quack();

            // Then - 모든 소리가 포함되어야 함
            assertThat(sounds).contains("꽥꽥"); // 청둥오리, 붉은머리오리
            assertThat(sounds).contains("꽉");   // 오리호출기
            assertThat(sounds).contains("삑삑"); // 고무오리
            assertThat(sounds).contains("끼룩"); // 거위

            // 카운팅된 오리만 카운트 (거위는 제외)
            // 붉은머리오리(1) + 오리호출기(1) + 고무오리(1) + 청둥오리(4) = 7
            assertThat(QuackCounter.getQuacks()).isEqualTo(7);
        }

        @Test
        @DisplayName("모든 패턴이 함께 동작한다")
        void testAllPatternsTogether() {
            // Given - 모든 패턴 활용
            // Factory 패턴
            AbstractDuckFactory factory = new CountingDuckFactory();

            // Adapter 패턴
            Quackable goose = new GooseAdapter(new Goose());

            // Decorator 패턴 (Factory 내부에서 적용됨)
            Quackable mallard = factory.createMallardDuck();

            // Composite 패턴
            Flock flock = new Flock();
            flock.add(mallard);
            flock.add(goose);

            // Observer 패턴
            Quackologist observer = new Quackologist();
            flock.registerObserver(observer);

            // When - 실행
            String sounds = flock.quack();

            // Then - 모든 패턴이 정상 동작
            assertThat(sounds).contains("꽥꽥");  // 오리 소리
            assertThat(sounds).contains("끼룩");  // 어댑터된 거위 소리
            assertThat(QuackCounter.getQuacks()).isEqualTo(1); // 데코레이터 카운트
            assertThat(observer.update(mallard)).contains("청둥오리"); // 옵저버 동작
        }
    }
}
