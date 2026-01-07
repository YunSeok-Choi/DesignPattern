package remote_proxy.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import remote_proxy.client.GumballMonitor;
import remote_proxy.domain.GumballMachine;
import remote_proxy.remote.GumballMachineRemote;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("껌볼 모니터 테스트")
class GumballMonitorTest {

    private GumballMachine machine;
    private GumballMonitor monitor;

    @BeforeEach
    void setUp() throws RemoteException {
        machine = new GumballMachine("서울_강남", 100);
        monitor = new GumballMonitor(machine);
    }

    @Test
    @DisplayName("모니터 리포트는 위치 정보를 포함해야 함")
    void testReportIncludesLocation() {
        String report = monitor.report();

        assertTrue(report.contains("껌볼 자판기 위치"));
        assertTrue(report.contains("서울_강남"));
    }

    @Test
    @DisplayName("모니터 리포트는 재고 정보를 포함해야 함")
    void testReportIncludesInventory() {
        String report = monitor.report();

        assertTrue(report.contains("현재 재고"));
        assertTrue(report.contains("100"));
        assertTrue(report.contains("개"));
    }

    @Test
    @DisplayName("모니터 리포트는 현재 상태를 포함해야 함")
    void testReportIncludesState() {
        String report = monitor.report();

        assertTrue(report.contains("현재 상태"));
        assertTrue(report.contains("동전 투입 대기 중"));
    }

    @Test
    @DisplayName("상태 변경 후 모니터 리포트 확인")
    void testReportAfterStateChange() {
        machine.insertQuarter();

        String report = monitor.report();

        assertTrue(report.contains("손잡이 회전 대기 중"));
    }

    @Test
    @DisplayName("재고 변경 후 모니터 리포트 확인")
    void testReportAfterInventoryChange() throws RemoteException {
        machine.insertQuarter();
        machine.turnCrank();

        String report = monitor.report();

        // 재고가 감소했는지 확인 (당첨 시 2개, 아니면 1개 감소)
        assertTrue(
            report.contains("99") ||
            report.contains("98")  // 당첨 시
        );
    }

    @Test
    @DisplayName("품절 상태 모니터링")
    void testReportSoldOut() throws RemoteException {
        GumballMachine emptyMachine = new GumballMachine("부산_해운대", 0);
        GumballMonitor emptyMonitor = new GumballMonitor(emptyMachine);

        String report = emptyMonitor.report();

        assertTrue(report.contains("품절"));
        assertTrue(report.contains("0 개"));
    }

    @Test
    @DisplayName("여러 자판기 모니터링")
    void testMultipleMachines() throws RemoteException {
        GumballMachine machine1 = new GumballMachine("서울", 50);
        GumballMachine machine2 = new GumballMachine("부산", 30);
        GumballMachine machine3 = new GumballMachine("대구", 20);

        GumballMonitor monitor1 = new GumballMonitor(machine1);
        GumballMonitor monitor2 = new GumballMonitor(machine2);
        GumballMonitor monitor3 = new GumballMonitor(machine3);

        String report1 = monitor1.report();
        String report2 = monitor2.report();
        String report3 = monitor3.report();

        assertTrue(report1.contains("서울") && report1.contains("50"));
        assertTrue(report2.contains("부산") && report2.contains("30"));
        assertTrue(report3.contains("대구") && report3.contains("20"));
    }

    @Test
    @DisplayName("모니터 리포트 형식 검증")
    void testReportFormat() {
        String report = monitor.report();

        // 리포트가 null이 아니고 빈 문자열이 아니어야 함
        assertNotNull(report);
        assertFalse(report.isEmpty());

        // 각 라인이 올바른 형식인지 확인
        String[] lines = report.split("\n");
        assertTrue(lines.length >= 3); // 최소 3줄 (위치, 재고, 상태)
    }

    @Test
    @DisplayName("재고 보충 후 모니터링")
    void testReportAfterRefill() throws RemoteException {
        // 재고를 모두 소진
        for (int i = 0; i < 100; i++) {
            machine.insertQuarter();
            machine.turnCrank();
            if (machine.getCount() == 0) break;
        }

        assertTrue(monitor.report().contains("품절"));

        // 재고 보충
        machine.refill(50);

        String report = monitor.report();
        assertTrue(report.contains("50"));
        assertTrue(report.contains("동전 투입 대기 중"));
    }

    @Test
    @DisplayName("모니터는 원격 객체의 변경사항을 실시간으로 반영")
    void testReportReflectsRealTimeChanges() throws RemoteException {
        // 초기 상태
        String report1 = monitor.report();
        assertTrue(report1.contains("100"));

        // 구매 1
        machine.insertQuarter();
        machine.turnCrank();

        // 변경된 상태 확인
        String report2 = monitor.report();
        assertNotEquals(report1, report2);

        // 구매 2
        machine.insertQuarter();
        machine.turnCrank();

        // 다시 변경된 상태 확인
        String report3 = monitor.report();
        assertNotEquals(report2, report3);
    }
}
