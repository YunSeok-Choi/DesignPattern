package remote_proxy.state;

import remote_proxy.domain.GumballMachine;
import java.util.Random;

public class HasQuarterState implements State {

	private static final long serialVersionUID = 2L;
    private Random randomWinner = new Random(System.currentTimeMillis());
    private transient GumballMachine gumballMachine;

	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

    @Override
	public String insertQuarter() {
		return "이미 동전이 투입되어 있습니다";
	}

    @Override
	public String ejectQuarter() {
		gumballMachine.setState(gumballMachine.getNoQuarterState());
		return "동전이 반환되었습니다";
	}

    @Override
	public String turnCrank() {
		int winner = randomWinner.nextInt(10);
		if (winner == 0) {
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
	public String toString() {
		return "손잡이 회전 대기 중";
	}
}
