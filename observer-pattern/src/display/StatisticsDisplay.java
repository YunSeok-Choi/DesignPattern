package display;

import weather.WeatherData;
import weather.WeatherSubject;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDisplay implements DisplayElement, Observer {

    private final WeatherSubject weatherSubject;
    private List<Float> temperatures;
    private float maxTemperature = Float.NEGATIVE_INFINITY;
    private float minTemperature = Float.POSITIVE_INFINITY;
    private float sumTemperature;
    private float averageTemperature;

    public StatisticsDisplay(WeatherSubject weatherSubject) {
        this.temperatures = new ArrayList<>();
        this.weatherSubject = weatherSubject;
        weatherSubject.registerObserver(this);
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
        float temperature = weatherSubject.getTemperature();
        this.temperatures.add(temperature);

        this.sumTemperature += temperature;
        this.maxTemperature = Math.max(temperature, maxTemperature);
        this.minTemperature = Math.min(temperature, minTemperature);
        this.averageTemperature = sumTemperature / temperatures.size();

        display();
    }
}
