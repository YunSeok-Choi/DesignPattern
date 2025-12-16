package display;

import weather.WeatherData;

public class ThirdPartyDisplay implements DisplayElement, Observer {

    private final WeatherData weatherData;

    public ThirdPartyDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
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
