package remote_proxy.state;

import remote_proxy.domain.GumballMachine;

public class SoldOutState implements State {

    private static final long serialVersionUID = 2L;
    private transient GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public String insertQuarter() {
		return "동전을 넣을 수 없습니다. 기계에 껌볼이 없습니다";
	}

    @Override
	public String ejectQuarter() {
		return "동전을 넣지 않았습니다";
	}

    @Override
	public String turnCrank() {
		return "손잡이를 돌렸지만 껌볼이 없습니다";
	}

    @Override
	public String dispense() {
		return "껌볼이 배출되지 않았습니다";
	}

    @Override
	public String toString() {
		return "품절";
	}
}
