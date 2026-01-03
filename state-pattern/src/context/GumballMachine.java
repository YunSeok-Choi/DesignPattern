package context;

import state.*;
import state.concrete.*;

public class GumballMachine {
 
	private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;
    private State winnerState;

    private State state = soldOutState;
    private int count = 0;
 
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
		winnerState = new WinnerState(this);

		this.count = numberGumballs;
 		if (numberGumballs > 0) {
			state = noQuarterState;
		} 
	}
 
	public String insertQuarter() {
		return state.insertQuarter();
	}

	public String ejectQuarter() {
		return state.ejectQuarter();
	}

	public String turnCrank() {
		String turnResult = state.turnCrank();
		String dispenseResult = state.dispense();
		if (dispenseResult.isEmpty()) {
			return turnResult;
		}
		return turnResult + "\n" + dispenseResult;
	}

	public void setState(State state) {
		this.state = state;
	}
 
	public String releaseBall() {
		if (count > 0) {
			count = count - 1;
		}
		return "껌볼이 슬롯을 통해 나옵니다...";
	}

	public int getCount() {
		return count;
	}

	public String refill(int count) {
		this.count += count;
		String refillResult = state.refill();
		String message = "껌볼 머신이 재충전되었습니다; 새로운 개수: " + this.count;
		if (refillResult.isEmpty()) {
			return message;
		}
		return message + "\n" + refillResult;
	}

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    @Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("\n강력한 껌볼 머신, Inc.");
		result.append("\n자바 기반 스탠딩 껌볼 모델 #2004");
		result.append("\n재고: " + count + "개의 껌볼");
		result.append("\n");
		result.append("머신 상태: " + state + "\n");
		return result.toString();
	}
}
