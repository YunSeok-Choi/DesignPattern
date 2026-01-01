package display;

import weather.WeatherData;
import weather.WeatherSubject;

public class ForecastDisplay implements DisplayElement, Observer {

    private final WeatherSubject weatherSubject;
    private float temperature;

    public ForecastDisplay(WeatherSubject weatherSubject) {
        this.weatherSubject = weatherSubject;
        weatherSubject.registerObserver(this);
    }

    @Override
    public String display() {
        return Display.FORECAST.name() +
                " 온도: " + temperature;
    }

    @Override
    public void update() {
        this.temperature = weatherSubject.getTemperature();
    }

    public float getTemperature() {
        return temperature;
    }
}
