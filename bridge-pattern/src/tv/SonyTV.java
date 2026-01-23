package tv;

public class SonyTV extends TV {

    private int station = 0;

    @Override
    public String on() {
        return "Sony TV 전원을 켭니다";
    }

    @Override
    public String off() {
        return "Sony TV 전원을 끕니다";
    }

    @Override
    public String tuneChannel(int channel) {
        this.station = channel;
        return "Sony TV 채널을 " + this.station + "번으로 설정합니다";
    }

    @Override
    public int getChannel() {
        return station;
    }

    @Override
    public String toString() {
        return "Sony TV";
    }
}
