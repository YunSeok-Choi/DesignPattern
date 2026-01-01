package test;

import display.CurrentConditionsDisplay;
import display.ForecastDisplay;
import display.StatisticsDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weather.WeatherData;

import static org.assertj.core.api.Assertions.assertThat;

public class ObserverTest {

    private WeatherData weatherData;
    private CurrentConditionsDisplay currentConditionsDisplay;
    private ForecastDisplay forecastDisplay;
    private StatisticsDisplay statisticsDisplay;

    @BeforeEach
    void setUp() {
        // Given - WeatherData와 Observer들 생성
        weatherData = new WeatherData();
        currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        forecastDisplay = new ForecastDisplay(weatherData);
        statisticsDisplay = new StatisticsDisplay(weatherData);
    }

    @Test
    void testObserverUpdate() {
        // Given - Observer들이 등록되어 있음

        // When - 측정값 변경
        weatherData.changeMeasurements(23, 65, 30.4f);

        // Then - 모든 Observer가 업데이트되어야 함
        assertThat(currentConditionsDisplay.getTemperature()).isEqualTo(23f);
        assertThat(currentConditionsDisplay.getHumidity()).isEqualTo(65f);
        assertThat(forecastDisplay.getTemperature()).isEqualTo(23f);
        assertThat(statisticsDisplay.getAverageTemperature()).isEqualTo(23f);
    }

    @Test
    void testMultipleUpdates() {
        // Given - Observer들이 등록되어 있음

        // When - 여러 번 측정값 변경
        weatherData.changeMeasurements(23, 65, 30.4f);
        weatherData.changeMeasurements(22, 70, 29.2f);
        weatherData.changeMeasurements(21, 75, 28.1f);

        // Then - 마지막 값으로 업데이트되어야 함
        assertThat(currentConditionsDisplay.getTemperature()).isEqualTo(21f);
        assertThat(currentConditionsDisplay.getHumidity()).isEqualTo(75f);
        assertThat(forecastDisplay.getTemperature()).isEqualTo(21f);
    }

    @Test
    void testStatisticsCalculation() {
        // Given - Observer들이 등록되어 있음

        // When - 여러 번 측정값 변경
        weatherData.changeMeasurements(23, 65, 30.4f);
        weatherData.changeMeasurements(22, 70, 29.2f);
        weatherData.changeMeasurements(21, 75, 28.1f);

        // Then - 통계가 올바르게 계산되어야 함
        assertThat(statisticsDisplay.getAverageTemperature()).isEqualTo((23f + 22f + 21f) / 3);
        assertThat(statisticsDisplay.getMaxTemperature()).isEqualTo(23f);
        assertThat(statisticsDisplay.getMinTemperature()).isEqualTo(21f);
    }

    @Test
    void testRemoveObserver() {
        // Given - Observer들이 등록되어 있음
        weatherData.changeMeasurements(23, 65, 30.4f);

        // When - ForecastDisplay를 제거하고 측정값 변경
        weatherData.removeObserver(forecastDisplay);
        weatherData.changeMeasurements(20, 80, 27.5f);

        // Then - ForecastDisplay는 업데이트되지 않아야 함
        assertThat(forecastDisplay.getTemperature()).isEqualTo(23f); // 이전 값 유지
        // Then - 다른 Observer들은 정상 업데이트
        assertThat(currentConditionsDisplay.getTemperature()).isEqualTo(20f);
        assertThat(statisticsDisplay.getMaxTemperature()).isEqualTo(23f);
        assertThat(statisticsDisplay.getMinTemperature()).isEqualTo(20f);
    }

    @Test
    void testDisplayFormat() {
        // Given - Observer들이 등록되어 있음

        // When - 측정값 변경
        weatherData.changeMeasurements(23, 65, 30.4f);

        // Then - Display 형식이 올바르게 반환되어야 함
        assertThat(currentConditionsDisplay.display()).contains("CURRENT");
        assertThat(currentConditionsDisplay.display()).contains("온도: 23.0");
        assertThat(currentConditionsDisplay.display()).contains("습도: 65.0");

        assertThat(forecastDisplay.display()).contains("FORECAST");
        assertThat(forecastDisplay.display()).contains("온도: 23.0");

        assertThat(statisticsDisplay.display()).contains("STATISTICS");
        assertThat(statisticsDisplay.display()).contains("평균/최대/최소 온도");
    }

    @Test
    void testObserverRegistration() {
        // Given - WeatherData만 생성

        // When - 새로운 Observer 등록
        WeatherData newWeatherData = new WeatherData();
        CurrentConditionsDisplay newDisplay = new CurrentConditionsDisplay(newWeatherData);

        // Then - 생성자에서 자동으로 등록되어야 함
        newWeatherData.changeMeasurements(25, 60, 30.0f);
        assertThat(newDisplay.getTemperature()).isEqualTo(25f);
        assertThat(newDisplay.getHumidity()).isEqualTo(60f);
    }

    @Test
    void testPullMethod() {
        // Given - Observer들이 등록되어 있음 (Pull 방식 사용)

        // When - 측정값 변경
        weatherData.changeMeasurements(23, 65, 30.4f);

        // Then - Observer들이 필요한 데이터만 가져와야 함
        // CurrentConditionsDisplay는 temperature와 humidity만 사용
        assertThat(currentConditionsDisplay.getTemperature()).isEqualTo(23f);
        assertThat(currentConditionsDisplay.getHumidity()).isEqualTo(65f);

        // ForecastDisplay는 temperature만 사용
        assertThat(forecastDisplay.getTemperature()).isEqualTo(23f);

        // StatisticsDisplay는 temperature만 사용
        assertThat(statisticsDisplay.getAverageTemperature()).isEqualTo(23f);
    }
}
