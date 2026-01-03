package state.concrete;

import context.GumballMachine;
import state.State;

public class WinnerState implements State {
    private GumballMachine gumballMachine;
 
    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public String insertQuarter() {
		return "잠시만 기다려 주세요, 이미 껌볼을 내보내고 있습니다";
	}

    @Override
	public String ejectQuarter() {
		return "잠시만 기다려 주세요, 이미 껌볼을 내보내고 있습니다";
	}

    @Override
	public String turnCrank() {
		return "다시 돌린다고 껌볼이 또 나오지 않습니다!";
	}

    @Override
	public String dispense() {
		StringBuilder result = new StringBuilder();
		result.append(gumballMachine.releaseBall());
		if (gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState());
		} else {
			result.append("\n").append(gumballMachine.releaseBall());
			result.append("\n").append("당첨입니다! 동전 하나로 껌볼 2개를 받으실 수 있습니다");
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
            	result.append("\n").append("이런, 껌볼이 다 떨어졌습니다!");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
		return result.toString();
	}

    @Override
	public String refill() {
		return "";
	}

    @Override
	public String toString() {
		return "당첨! 동전 하나로 껌볼 2개 배출 중";
	}
}
