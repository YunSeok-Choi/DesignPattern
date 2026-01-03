package state.concrete;

import context.GumballMachine;
import state.State;

import java.util.Random;

public class HasQuarterState implements State {

    private Random randomWinner = new Random(System.currentTimeMillis());
    private GumballMachine gumballMachine;
 
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

    @Override
	public String insertQuarter() {
		return "다른 동전을 넣을 수 없습니다";
	}

    @Override
	public String ejectQuarter() {
		gumballMachine.setState(gumballMachine.getNoQuarterState());
		return "동전이 반환됩니다";
	}

    @Override
	public String turnCrank() {
		int winner = randomWinner.nextInt(10);
		if ((winner == 0) && (gumballMachine.getCount() > 1)) {
			gumballMachine.setState(gumballMachine.getWinnerState());
		} else {
			gumballMachine.setState(gumballMachine.getSoldState());
		}
		return "손잡이를 돌렸습니다...";
	}

    @Override
    public String dispense() {
        return "껌볼이 나오지 않았습니다";
    }

    @Override
    public String refill() {
		return "";
	}

    @Override
	public String toString() {
		return "손잡이 회전 대기 중";
	}
}
