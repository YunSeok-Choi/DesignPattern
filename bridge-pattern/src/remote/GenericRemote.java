package remote;

import factory.TVFactory;

public class GenericRemote extends RemoteControl {

    public GenericRemote(TVFactory tvFactory) {
        super(tvFactory);
    }

    public String nextChannel() {
        int channel = this.getChannel();
        return this.setChannel(channel + 1);
    }

    public String prevChannel() {
        int channel = this.getChannel();
        return this.setChannel(channel - 1);
    }
}
