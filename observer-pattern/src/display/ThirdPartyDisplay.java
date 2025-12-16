package display;

import weather.WeatherData;
import weather.WeatherSubject;

public class ThirdPartyDisplay implements DisplayElement, Observer {

    private final WeatherSubject weatherSubject;

    public ThirdPartyDisplay(WeatherSubject weatherSubject) {
        this.weatherSubject = weatherSubject;
        weatherSubject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(Display.THIRD_PARTY.name());
    }

    @Override
    public void update() {
        display();
    }
}
