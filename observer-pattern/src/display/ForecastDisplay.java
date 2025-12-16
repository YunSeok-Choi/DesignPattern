package display;

import weather.WeatherData;

public class ForecastDisplay implements DisplayElement, Observer {

    private final WeatherData weatherData;
    private float temperature;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(
                Display.FORECAST.name() +
                " 온도: " + temperature
        );
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        display();
    }
}
