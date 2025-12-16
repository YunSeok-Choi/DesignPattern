package weather;

public interface WeatherSubject extends Subject {

    float getTemperature();

    float getHumidity();

    float getPressure();
}
