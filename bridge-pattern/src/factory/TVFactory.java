package factory;

import tv.LgTV;
import tv.SonyTV;
import tv.TV;

public class TVFactory {

    public TV getTV(String type) {
        if (type.equalsIgnoreCase("LG")) {
            return new LgTV();
        } else if (type.equalsIgnoreCase("Sony")) {
            return new SonyTV();
        } else {
            throw new IllegalArgumentException("지원하지 않는 TV 타입입니다: " + type);
        }
    }
}
