package display;

import weather.Subject;
import weather.WeatherData;

public class CurrentConditionsDisplay implements DisplayElement, Observer {

    private final WeatherData weatherData;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(
                Display.CURRENT.name() +
                " 온도: " + temperature +
                ", 습도: " + humidity
        );
    }

    @Override
    public void update() {
        this.temperature = this.weatherData.getTemperature();
        this.humidity = this.weatherData.getHumidity();
        display();
    }

}
