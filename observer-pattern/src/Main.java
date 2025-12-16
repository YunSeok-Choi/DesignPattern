import display.CurrentConditionsDisplay;
import display.ForecastDisplay;
import display.StatisticsDisplay;
import weather.WeatherData;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        weatherData.changeMeasurements(23, 65, 30.4f);
        System.out.println();
        weatherData.changeMeasurements(22, 70, 29.2f);
        System.out.println();
        weatherData.changeMeasurements(21, 75, 28.1f);
        System.out.println();

        weatherData.removeObserver(forecastDisplay);
        weatherData.changeMeasurements(20, 80, 27.5f);
    }
}
