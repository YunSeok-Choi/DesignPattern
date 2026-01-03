package state.concrete;

import context.GumballMachine;
import state.State;

public class SoldState implements State {

    private GumballMachine gumballMachine;
 
    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public String insertQuarter() {
		return "잠시만 기다려 주세요, 이미 껌볼을 내보내고 있습니다";
	}

    @Override
	public String ejectQuarter() {
		return "죄송합니다, 이미 손잡이를 돌리셨습니다";
	}

    @Override
	public String turnCrank() {
		return "두 번 돌린다고 껌볼이 또 나오지 않습니다!";
	}

    @Override
	public String dispense() {
		StringBuilder result = new StringBuilder();
		result.append(gumballMachine.releaseBall());
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		} else {
			result.append("\n").append("이런, 껌볼이 다 떨어졌습니다!");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
		return result.toString();
	}

    @Override
	public String refill() {
		return "";
	}

    @Override
	public String toString() {
		return "껌볼 배출 중";
	}
}
