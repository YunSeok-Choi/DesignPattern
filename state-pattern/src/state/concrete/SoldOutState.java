package state.concrete;

import context.GumballMachine;
import state.State;

public class SoldOutState implements State {

    private GumballMachine gumballMachine;
 
    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public String insertQuarter() {
		return "동전을 넣을 수 없습니다, 껌볼이 매진되었습니다";
	}

    @Override
	public String ejectQuarter() {
		return "반환할 수 없습니다, 동전을 넣지 않았습니다";
	}

    @Override
	public String turnCrank() {
		return "손잡이를 돌렸지만 껌볼이 없습니다";
	}

    @Override
	public String dispense() {
		return "껌볼이 나오지 않았습니다";
	}

    @Override
	public String refill() {
		gumballMachine.setState(gumballMachine.getNoQuarterState());
		return "";
	}

    @Override
	public String toString() {
		return "매진";
	}
}
