package remote_proxy.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import remote_proxy.domain.GumballMachine;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("껌볼 자판기 테스트")
class GumballMachineTest {

    private GumballMachine machine;

    @BeforeEach
    void setUp() throws RemoteException {
        machine = new GumballMachine("테스트_위치", 5);
    }

    @Test
    @DisplayName("초기 상태는 동전 투입 대기 중이어야 함")
    void testInitialState() throws RemoteException {
        assertEquals("동전 투입 대기 중", machine.getState().toString());
        assertEquals(5, machine.getCount());
    }

    @Test
    @DisplayName("동전을 넣으면 손잡이 회전 대기 상태로 전환")
    void testInsertQuarter() {
        String result = machine.insertQuarter();
        assertEquals("동전을 넣었습니다", result);
        assertEquals("손잡이 회전 대기 중", machine.getState().toString());
    }

    @Test
    @DisplayName("동전을 넣고 반환하면 다시 동전 투입 대기 상태로")
    void testEjectQuarter() {
        machine.insertQuarter();
        String result = machine.ejectQuarter();
        assertEquals("동전이 반환되었습니다", result);
        assertEquals("동전 투입 대기 중", machine.getState().toString());
    }

    @Test
    @DisplayName("동전 없이 반환 시도하면 에러 메시지")
    void testEjectQuarterWithoutInsert() {
        String result = machine.ejectQuarter();
        assertEquals("동전을 넣지 않았습니다", result);
    }

    @Test
    @DisplayName("동전을 넣고 손잡이를 돌리면 껌볼 배출")
    void testTurnCrank() throws RemoteException {
        machine.insertQuarter();
        String result = machine.turnCrank();

        // 결과에 "손잡이를 돌렸습니다"와 "껌볼이 굴러 나옵니다" 포함
        assertTrue(result.contains("손잡이를 돌렸습니다"));
        assertTrue(result.contains("껌볼이 굴러 나옵니다"));

        // 재고가 1개 감소
        assertEquals(4, machine.getCount());

        // 다시 동전 투입 대기 상태로
        assertEquals("동전 투입 대기 중", machine.getState().toString());
    }

    @Test
    @DisplayName("동전 없이 손잡이를 돌리면 에러 메시지")
    void testTurnCrankWithoutQuarter() {
        String result = machine.turnCrank();
        assertTrue(result.contains("동전이 없습니다"));
    }

    @Test
    @DisplayName("재고가 0이 되면 품절 상태로 전환")
    void testSoldOut() throws RemoteException {
        GumballMachine smallMachine = new GumballMachine("테스트", 1);

        smallMachine.insertQuarter();
        smallMachine.turnCrank();

        assertEquals(0, smallMachine.getCount());
        assertEquals("품절", smallMachine.getState().toString());
    }

    @Test
    @DisplayName("품절 상태에서는 동전을 넣을 수 없음")
    void testInsertQuarterWhenSoldOut() throws RemoteException {
        GumballMachine emptyMachine = new GumballMachine("테스트", 0);

        String result = emptyMachine.insertQuarter();
        assertEquals("동전을 넣을 수 없습니다. 기계에 껌볼이 없습니다", result);
    }

    @Test
    @DisplayName("재고 보충 후 동전 투입 대기 상태로 복구")
    void testRefill() throws RemoteException {
        GumballMachine emptyMachine = new GumballMachine("테스트", 0);
        assertEquals("품절", emptyMachine.getState().toString());

        emptyMachine.refill(10);

        assertEquals(10, emptyMachine.getCount());
        assertEquals("동전 투입 대기 중", emptyMachine.getState().toString());
    }

    @Test
    @DisplayName("위치 정보 조회")
    void testGetLocation() throws RemoteException {
        assertEquals("테스트_위치", machine.getLocation());
    }

    @Test
    @DisplayName("toString()은 기계 정보를 포함해야 함")
    void testToString() {
        String info = machine.toString();
        assertTrue(info.contains("Java 기반 껌볼 자판기"));
        assertTrue(info.contains("재고: 5 개"));
        assertTrue(info.contains("기계 상태:"));
    }

    @Test
    @DisplayName("여러 번 동전을 넣고 껌볼 구매")
    void testMultiplePurchases() throws RemoteException {
        // 첫 번째 구매
        machine.insertQuarter();
        machine.turnCrank();
        assertEquals(4, machine.getCount());

        // 두 번째 구매
        machine.insertQuarter();
        machine.turnCrank();
        assertEquals(3, machine.getCount());

        // 세 번째 구매
        machine.insertQuarter();
        machine.turnCrank();
        assertEquals(2, machine.getCount());

        // 모든 구매 후 동전 투입 대기 상태
        assertEquals("동전 투입 대기 중", machine.getState().toString());
    }
}
