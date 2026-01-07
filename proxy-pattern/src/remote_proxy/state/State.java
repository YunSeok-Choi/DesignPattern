package remote_proxy.state;

import java.io.*;

public interface State extends Serializable {

    public String insertQuarter();

    public String ejectQuarter();

    public String turnCrank();

    public String dispense();
}
