package tv;

public class LgTV extends TV {

    private int channel = 1;

    @Override
    public String on() {
        return "LG TV 전원을 켭니다";
    }

    @Override
    public String off() {
        return "LG TV 전원을 끕니다";
    }

    @Override
    public String tuneChannel(int channel) {
        this.channel = channel;
        return "LG TV 채널을 " + this.channel + "번으로 설정합니다";
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "LG TV";
    }
}
