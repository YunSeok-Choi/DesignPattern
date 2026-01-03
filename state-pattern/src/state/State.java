package state;

public interface State {

    public String insertQuarter();

    public String ejectQuarter();

    public String turnCrank();

    public String dispense();

    public String refill();
}
