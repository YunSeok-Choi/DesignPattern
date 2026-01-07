package remote_proxy.domain;

import remote_proxy.remote.GumballMachineRemote;
import remote_proxy.state.*;
import java.rmi.*;
import java.rmi.server.*;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
    private static final long serialVersionUID = 2L;
    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;
    private State winnerState;

    private State state;
    private int count = 0;
    private String location;

    public GumballMachine(String location, int numberGumballs) throws RemoteException {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
        this.location = location;
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
        return turnResult + "\n" + dispenseResult;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String releaseBall() {
        String result = "껌볼이 굴러 나옵니다...";
        if (count != 0) {
            count = count - 1;
        }
        return result;
    }

    public void refill(int count) {
        this.count = count;
        state = noQuarterState;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getLocation() {
        return location;
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
        StringBuffer result = new StringBuffer();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava 기반 껌볼 자판기 모델 #2014");
        result.append("\n재고: " + count + " 개");
        result.append("\n");
        result.append("기계 상태: " + state + "\n");
        return result.toString();
    }
}
