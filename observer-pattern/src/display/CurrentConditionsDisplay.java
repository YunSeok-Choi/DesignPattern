package display;

import weather.WeatherSubject;

public class CurrentConditionsDisplay implements DisplayElement, Observer {

    private final WeatherSubject weatherSubject;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(WeatherSubject weatherSubject) {
        this.weatherSubject = weatherSubject;
        weatherSubject.registerObserver(this);
    }

    @Override
    public String display() {
        return Display.CURRENT.name() +
                " 온도: " + temperature +
                ", 습도: " + humidity;
    }

    @Override
    public void update() {
        this.temperature = weatherSubject.getTemperature();
        this.humidity = weatherSubject.getHumidity();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

}
