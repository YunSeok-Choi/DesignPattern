package remote;

import factory.TVFactory;
import tv.TV;

public abstract class RemoteControl {

    protected TV tv;
    protected TVFactory tvFactory;

    public RemoteControl(TVFactory tvFactory) {
        this.tvFactory = tvFactory;
    }

    public String on() {
        return tv.on();
    }

    public String off() {
        return tv.off();
    }

    public String setChannel(int channel) {
        return tv.tuneChannel(channel);
    }

    public int getChannel() {
        return tv.getChannel();
    }

    public void setTV(String type) {
        this.tv = tvFactory.getTV(type);
    }

    public TV getTV() {
        return tv;
    }
}
