package remote_proxy.test;

import remote_proxy.domain.GumballMachine;
import remote_proxy.remote.GumballMachineRemote;
import java.rmi.*;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachineRemote gumballMachine = null;
		int count;

		if (args.length < 2) {
			System.out.println("사용법: GumballMachine <호스트명> <재고수량>");
 			System.exit(1);
		}

		try {
			count = Integer.parseInt(args[1]);

			gumballMachine =
				new GumballMachine(args[0], count);
			Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachine);

			System.out.println("껌볼 자판기 서버 시작됨");
			System.out.println("위치: " + args[0]);
			System.out.println("초기 재고: " + count + " 개");
		} catch (Exception e) {
			System.err.println("서버 시작 오류: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
