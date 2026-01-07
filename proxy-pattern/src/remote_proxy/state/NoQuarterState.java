package remote_proxy.state;

import remote_proxy.domain.GumballMachine;

public class NoQuarterState implements State {

    private static final long serialVersionUID = 2L;
    private transient GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public String insertQuarter() {
        gumballMachine.setState(gumballMachine.getHasQuarterState());
        return "동전을 넣었습니다";
    }

    @Override
    public String ejectQuarter() {
        return "동전을 넣지 않았습니다";
    }

    @Override
    public String turnCrank() {
        return "손잡이를 돌렸지만 동전이 없습니다";
    }

    @Override
    public String dispense() {
        return "먼저 돈을 내셔야 합니다";
    }

    @Override
    public String toString() {
        return "동전 투입 대기 중";
    }
}
