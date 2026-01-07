package remote_proxy.test;

import remote_proxy.client.GumballMonitor;
import remote_proxy.remote.GumballMachineRemote;
import java.rmi.*;

public class GumballMonitorTestDrive {

	public static void main(String[] args) {
		String[] location = {"rmi://santafe.mightygumball.com/gumballmachine",
		                     "rmi://boulder.mightygumball.com/gumballmachine",
		                     "rmi://austin.mightygumball.com/gumballmachine"};

		if (args.length >= 1)
        {
            location = new String[1];
            location[0] = "rmi://" + args[0] + "/gumballmachine";
        }

		GumballMonitor[] monitor = new GumballMonitor[location.length];

		System.out.println("=== 껌볼 자판기 모니터링 시작 ===\n");

		for (int i=0;i < location.length; i++) {
			try {
           		GumballMachineRemote machine =
					(GumballMachineRemote) Naming.lookup(location[i]);
           		monitor[i] = new GumballMonitor(machine);
        	} catch (Exception e) {
            	System.err.println("연결 오류 (" + location[i] + "): " + e.getMessage());
        	}
		}

		for (int i=0; i < monitor.length; i++) {
			if (monitor[i] != null) {
				System.out.println(monitor[i].report());
				System.out.println();
			}
		}
	}
}
