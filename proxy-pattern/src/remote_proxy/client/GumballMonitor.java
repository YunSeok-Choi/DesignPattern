package remote_proxy.client;

import remote_proxy.remote.GumballMachineRemote;
import java.rmi.*;

public class GumballMonitor {

    private GumballMachineRemote machine;

    public GumballMonitor(GumballMachineRemote machine) {
        this.machine = machine;
    }

    public String report() {
        try {
            StringBuilder result = new StringBuilder();
            result.append("껌볼 자판기 위치: ").append(machine.getLocation()).append("\n");
            result.append("현재 재고: ").append(machine.getCount()).append(" 개\n");
            result.append("현재 상태: ").append(machine.getState());
            return result.toString();
        } catch (RemoteException e) {
            return "원격 오류 발생: " + e.getMessage();
        }
    }
}
