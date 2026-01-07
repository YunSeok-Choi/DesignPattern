package remote_proxy.state;

import remote_proxy.domain.GumballMachine;

public class SoldState implements State {

    private static final long serialVersionUID = 2L;
    private transient GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public String insertQuarter() {
		return "잠시만 기다려주세요. 껌볼이 나오고 있습니다";
	}

    @Override
	public String ejectQuarter() {
		return "이미 손잡이를 돌리셨습니다";
	}

    @Override
	public String turnCrank() {
		return "두 번 돌린다고 껌볼이 하나 더 나오지 않습니다!";
	}

    @Override
	public String dispense() {
		String result = gumballMachine.releaseBall();
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		} else {
			result += "\n이런, 껌볼이 다 떨어졌습니다!";
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
		return result;
	}

    @Override
	public String toString() {
		return "껌볼 배출 중";
	}
}
