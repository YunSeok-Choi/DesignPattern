package test;

import context.GumballMachine;
import state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.concrete.HasQuarterState;
import state.concrete.NoQuarterState;
import state.concrete.SoldOutState;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {

    private GumballMachine gumballMachine;

    @BeforeEach
    void setUp() {
        // Given - 껌볼 10개가 들어있는 머신 생성
        gumballMachine = new GumballMachine(10);
    }

    @Test
    void testInitialState() {
        // Given - 껌볼 머신이 생성됨

        // When - 초기 상태 확인
        String machineInfo = gumballMachine.toString();

        // Then - NoQuarterState이어야 함
        assertThat(machineInfo).contains("재고: 10개의 껌볼");
        assertThat(machineInfo).contains("동전 대기 중");
    }

    @Test
    void testInsertQuarterAndTurnCrank() {
        // Given - 껌볼 머신이 준비됨

        // When - 동전을 넣고 손잡이를 돌림
        String insertResult = gumballMachine.insertQuarter();
        String turnResult = gumballMachine.turnCrank();

        // Then - 동전이 들어가고 껌볼이 나와야 함
        assertThat(insertResult).contains("동전을 넣었습니다");
        assertThat(turnResult).contains("손잡이를 돌렸습니다");
        assertThat(turnResult).contains("껌볼이 슬롯을 통해 나옵니다");

        // Then - 1개 또는 2개 줄어들어야 함 (일반/당첨)
        assertThat(gumballMachine.getCount()).isBetween(8, 9);
    }

    @Test
    void testMultiplePurchases() {
        // Given - 껌볼 머신이 준비됨

        // When - 여러 번 구매
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        // Then - 최소 2개, 최대 4개 줄어들어야 함 (당첨 여부에 따라)
        // 일반 구매 2번: 10 - 2 = 8개
        // 당첨 1번, 일반 1번: 10 - 3 = 7개
        // 당첨 2번: 10 - 4 = 6개
        assertThat(gumballMachine.getCount()).isBetween(6, 8);
    }

    @Test
    void testTurnCrankWithoutQuarter() {
        // Given - 껌볼 머신이 준비됨

        // When - 동전 없이 손잡이를 돌림
        String turnResult = gumballMachine.turnCrank();

        // Then - 에러 메시지가 반환되어야 함
        assertThat(turnResult).contains("동전이 없습니다");
        assertThat(gumballMachine.getCount()).isEqualTo(10); // 껌볼 수 변화 없음
    }

    @Test
    void testEjectQuarter() {
        // Given - 동전을 넣음
        gumballMachine.insertQuarter();

        // When - 동전 반환
        String ejectResult = gumballMachine.ejectQuarter();

        // Then - 동전이 반환되어야 함
        assertThat(ejectResult).contains("반환");
    }

    @Test
    void testInsertQuarterTwice() {
        // Given - 동전을 한 번 넣음
        gumballMachine.insertQuarter();

        // When - 동전을 또 넣으려고 시도
        String insertResult = gumballMachine.insertQuarter();

        // Then - 에러 메시지가 반환되어야 함
        assertThat(insertResult).contains("다른 동전을 넣을 수 없습니다");
    }

    @Test
    void testSoldOutState() {
        // Given - 껌볼 1개만 있는 머신
        GumballMachine machine = new GumballMachine(1);

        // When - 껌볼을 모두 소진
        machine.insertQuarter();
        machine.turnCrank();

        // Then - SoldOut 상태가 되어야 함
        assertThat(machine.toString()).contains("매진");
        assertThat(machine.getCount()).isEqualTo(0);
    }

    @Test
    void testCannotInsertQuarterWhenSoldOut() {
        // Given - 껌볼이 모두 소진된 머신
        GumballMachine machine = new GumballMachine(1);
        machine.insertQuarter();
        machine.turnCrank();

        // When - SoldOut 상태에서 동전을 넣으려고 시도
        String insertResult = machine.insertQuarter();

        // Then - 에러 메시지가 반환되어야 함
        assertThat(insertResult).contains("매진");
    }

    @Test
    void testRefill() {
        // Given - 껌볼이 적은 머신
        GumballMachine machine = new GumballMachine(2);

        // When - 껌볼 재충전
        String refillResult = machine.refill(5);

        // Then - 껌볼 개수가 증가해야 함
        assertThat(refillResult).contains("재충전");
        assertThat(refillResult).contains("7");
        assertThat(machine.getCount()).isEqualTo(7);
    }

    @Test
    void testStateTransitions() {
        // Given - 껌볼 머신이 준비됨

        // When - NoQuarterState
        assertThat(gumballMachine.getState()).isInstanceOf(NoQuarterState.class);

        // When - 동전 삽입 후 HasQuarterState
        gumballMachine.insertQuarter();
        assertThat(gumballMachine.getState()).isInstanceOf(HasQuarterState.class);

        // When - 손잡이를 돌린 후 NoQuarterState로 복귀
        gumballMachine.turnCrank();
        assertThat(gumballMachine.getState()).isInstanceOf(NoQuarterState.class);
    }

    @Test
    void testMachineToString() {
        // Given - 껌볼 3개가 들어있는 머신
        GumballMachine machine = new GumballMachine(3);

        // When - toString 호출
        String result = machine.toString();

        // Then - 머신 정보가 올바르게 출력되어야 함
        assertThat(result).contains("강력한 껌볼 머신");
        assertThat(result).contains("재고: 3개의 껌볼");
        assertThat(result).contains("동전 대기 중");
    }

    @Test
    void testSingleGumballToString() {
        // Given - 껌볼 1개가 들어있는 머신
        GumballMachine machine = new GumballMachine(1);

        // When - toString 호출
        String result = machine.toString();

        // Then - 1개의 껌볼이어야 함
        assertThat(result).contains("재고: 1개의 껌볼");
    }

    @Test
    void testCompleteScenario() {
        // Given - 껌볼 5개가 들어있는 머신
        GumballMachine machine = new GumballMachine(5);

        // When - 시나리오: 2번 구매
        machine.insertQuarter();
        machine.turnCrank();
        machine.insertQuarter();
        machine.turnCrank();

        // Then - 껌볼이 3개 남아야 함 (일반 구매인 경우)
        // 또는 2개 이하 (당첨된 경우 한 번이라도 있으면)
        assertThat(machine.getCount()).isLessThanOrEqualTo(3);
        assertThat(machine.getState()).isInstanceOf(NoQuarterState.class);
    }

    @Test
    void testWinnerStateRandomChance() {
        // Given - 껌볼이 충분히 있는 머신 (당첨 시 2개 필요)
        // HasQuarterState에서 10% 확률로 WinnerState로 전환됨
        GumballMachine machine = new GumballMachine(100);

        // When - 여러 번 시도 (통계적으로 최소 한 번은 당첨되어야 함)
        int initialCount = machine.getCount();
        boolean wonAtLeastOnce = false;
        int normalPurchases = 0;
        int winnerPurchases = 0;

        for (int i = 0; i < 50; i++) {
            int beforeCount = machine.getCount();
            machine.insertQuarter();
            String result = machine.turnCrank();
            int afterCount = machine.getCount();

            int dispensed = beforeCount - afterCount;

            if (dispensed == 2) {
                // 껌볼 2개가 나왔으면 당첨
                wonAtLeastOnce = true;
                winnerPurchases++;
                assertThat(result).contains("당첨");
            } else if (dispensed == 1) {
                // 일반 구매
                normalPurchases++;
            }
        }

        // Then - 50번 중 최소 1번은 당첨되어야 함 (통계적 기대값)
        // 10% 확률로 50번 시도 시 당첨 안 될 확률: (0.9)^50 ≈ 0.005 (0.5%)
        assertThat(wonAtLeastOnce).isTrue();
        assertThat(winnerPurchases).isGreaterThan(0);
        assertThat(normalPurchases).isGreaterThan(0);
    }

    @Test
    void testWinnerStateDispensesTwoGumballs() {
        // Given - 껌볼이 충분히 있는 머신
        GumballMachine machine = new GumballMachine(100);

        // When - 당첨될 때까지 반복 (최대 100번)
        boolean foundWinner = false;
        for (int i = 0; i < 100 && !foundWinner; i++) {
            int beforeCount = machine.getCount();
            machine.insertQuarter();
            String result = machine.turnCrank();
            int afterCount = machine.getCount();

            // Then - 당첨 시 껌볼이 2개 줄어들어야 함
            if (result.contains("당첨")) {
                assertThat(beforeCount - afterCount).isEqualTo(2);
                assertThat(result).contains("껌볼 2개");
                foundWinner = true;
            }
        }

        // 100번 안에 최소 한 번은 당첨되어야 함
        assertThat(foundWinner).isTrue();
    }

    @Test
    void testWinnerStateWithLowInventory() {
        // Given - 껌볼이 2개만 있는 머신
        // 당첨되려면 껌볼이 2개 이상 있어야 함
        GumballMachine machine = new GumballMachine(2);

        // When - 여러 번 시도
        boolean canWin = true;
        for (int i = 0; i < 20 && machine.getCount() >= 2; i++) {
            int beforeCount = machine.getCount();
            machine.insertQuarter();
            String result = machine.turnCrank();
            int afterCount = machine.getCount();

            // Then - 껌볼이 2개 이상일 때만 당첨 가능
            if (beforeCount >= 2) {
                // 당첨 가능
                if (result.contains("당첨")) {
                    assertThat(beforeCount - afterCount).isEqualTo(2);
                } else {
                    assertThat(beforeCount - afterCount).isEqualTo(1);
                }
            }
        }
    }

    @Test
    void testWinnerStateSoldOutAfterWin() {
        // Given - 껌볼이 정확히 2개 있는 머신
        GumballMachine machine = new GumballMachine(2);

        // When - 당첨될 때까지 반복
        boolean wonAndSoldOut = false;
        for (int i = 0; i < 50 && machine.getCount() == 2; i++) {
            machine.insertQuarter();
            String result = machine.turnCrank();

            // Then - 2개 남은 상태에서 당첨 시 매진되어야 함
            if (result.contains("당첨")) {
                assertThat(machine.getCount()).isEqualTo(0);
                assertThat(machine.getState()).isInstanceOf(SoldOutState.class);
                wonAndSoldOut = true;
                break;
            }
        }

        // 만약 당첨이 안 났다면 일반 구매로 소진
        if (!wonAndSoldOut && machine.getCount() < 2) {
            // 일반 구매로 1개 소진된 경우
            if (machine.getCount() == 1) {
                machine.insertQuarter();
                machine.turnCrank();
                assertThat(machine.getCount()).isEqualTo(0);
            }
        }
    }

    @Test
    void testMultiplePurchasesWithPossibleWins() {
        // Given - 껌볼이 충분히 있는 머신
        GumballMachine machine = new GumballMachine(20);
        int initialCount = 20;

        // When - 10번 구매
        int totalDispensed = 0;
        for (int i = 0; i < 10; i++) {
            int beforeCount = machine.getCount();
            machine.insertQuarter();
            machine.turnCrank();
            int afterCount = machine.getCount();
            totalDispensed += (beforeCount - afterCount);
        }

        // Then - 총 10~20개 사이의 껌볼이 나와야 함
        // (모두 일반이면 10개, 모두 당첨이면 20개)
        assertThat(totalDispensed).isBetween(10, 20);
        assertThat(machine.getCount()).isEqualTo(initialCount - totalDispensed);
    }
}
