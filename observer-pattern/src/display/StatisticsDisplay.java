package display;

import weather.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDisplay implements DisplayElement, Observer {

    private final WeatherData weatherData;
    private List<Float> temperatures;
    private float maxTemperature = Float.NEGATIVE_INFINITY;
    private float minTemperature = Float.POSITIVE_INFINITY;
    private float sumTemperature;
    private float averageTemperature;

    public StatisticsDisplay(WeatherData weatherData) {
        this.temperatures = new ArrayList<>();
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(
                Display.STATISTICS.name() +
                        " 평균/최대/최소 온도 : " +
                        averageTemperature + "/" + maxTemperature + "/" + minTemperature
        );
    }

    @Override
    public void update() {
        float temperature = weatherData.getTemperature();
        this.temperatures.add(temperature);

        this.sumTemperature += temperature;
        this.maxTemperature = Math.max(temperature, maxTemperature);
        this.minTemperature = Math.min(temperature, minTemperature);
        this.averageTemperature = sumTemperature / temperatures.size();

        display();
    }
}
