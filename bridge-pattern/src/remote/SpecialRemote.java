package remote;

import factory.TVFactory;

public class SpecialRemote extends RemoteControl {

    public SpecialRemote(TVFactory tvFactory) {
        super(tvFactory);
    }

    public String up() {
        int channel = this.getChannel();
        return this.setChannel(channel + 1);
    }

    public String down() {
        int channel = this.getChannel();
        return this.setChannel(channel - 1);
    }
}
