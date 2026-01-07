package remote_proxy.state;

import remote_proxy.domain.GumballMachine;

public class WinnerState implements State {

    private static final long serialVersionUID = 2L;
    private transient GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public String insertQuarter() {
		return "잠시만 기다려주세요. 껌볼이 나오고 있습니다";
	}

    @Override
	public String ejectQuarter() {
		return "잠시만 기다려주세요. 껌볼이 나오고 있습니다";
	}

    @Override
	public String turnCrank() {
		return "다시 돌려도 껌볼이 하나 더 나오지 않습니다!";
	}

    @Override
	public String dispense() {
		StringBuilder result = new StringBuilder();
		result.append("당첨입니다! 동전 하나로 껌볼 두 개를 받으세요\n");
		result.append(gumballMachine.releaseBall());

		if (gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState());
		} else {
			result.append("\n").append(gumballMachine.releaseBall());
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				result.append("\n이런, 껌볼이 다 떨어졌습니다!");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
		return result.toString();
	}

    @Override
	public String toString() {
		return "당첨! 동전 하나에 껌볼 두 개 배출 중";
	}
}
