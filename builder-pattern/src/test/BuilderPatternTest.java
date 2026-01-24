package test;

import accommodation.Hotel;
import accommodation.Tent;
import builder.CityVacationBuilder;
import builder.OutdoorsVacationBuilder;
import builder.VacationBuilder;
import product.Vacation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("빌더 패턴 테스트")
public class BuilderPatternTest {

    @Test
    @DisplayName("중첩 빌더로 복잡한 객체를 명확하게 생성할 수 있다")
    void testNestedBuilder() {
        // Given & When - 중첩 빌더로 여행 구성
        Vacation vacation = new CityVacationBuilder()
                .addAccommodation(
                        Hotel.builder()
                                .name("그랜드 호텔")
                                .arrivalDate(2024, 8, 1)
                                .nights(3)
                                .roomNumber(501)
                                .build()
                )
                .addAccommodation(
                        Hotel.builder()
                                .name("시티 호텔")
                                .arrivalDate(2024, 8, 4)
                                .nights(2)
                                .roomNumber(302)
                                .build()
                )
                .addEvent("태양의 서커스")
                .build();

        // Then - 모든 구성요소가 포함되어야 함
        assertThat(vacation.getName()).isEqualTo("도시 여행");
        assertThat(vacation.getAccommodations()).hasSize(2);
        assertThat(vacation.toString())
                .contains("그랜드 호텔", "객실 번호 501")
                .contains("시티 호텔", "객실 번호 302")
                .contains("태양의 서커스 공연 관람");
    }

    @Test
    @DisplayName("같은 빌더 인터페이스로 다른 종류의 객체를 생성할 수 있다")
    void testDifferentBuildersWithSameInterface() {
        // Given & When - 도시 여행
        Vacation cityVacation = new CityVacationBuilder()
                .addAccommodation(
                        Hotel.builder()
                                .name("럭셔리 호텔")
                                .build()
                )
                .addEvent("뮤지컬")
                .build();

        // Given & When - 야외 여행
        Vacation outdoorsVacation = new OutdoorsVacationBuilder()
                .addAccommodation(
                        Tent.builder()
                                .name("2인용 텐트")
                                .build()
                )
                .addEvent("설악산")
                .build();

        // Then - 각 빌더에 맞는 결과가 생성되어야 함
        assertThat(cityVacation.getName()).isEqualTo("도시 여행");
        assertThat(cityVacation.toString()).contains("럭셔리 호텔", "뮤지컬 공연 관람");

        assertThat(outdoorsVacation.getName()).isEqualTo("야외 여행");
        assertThat(outdoorsVacation.toString()).contains("2인용 텐트", "하이킹: 설악산");
    }

    @Test
    @DisplayName("중첩 빌더를 통해 예약 정보가 포함된 숙소를 추가할 수 있다")
    void testAccommodationWithReservation() {
        // Given & When - 예약 정보와 함께 숙소 추가
        Vacation vacation = new OutdoorsVacationBuilder()
                .addAccommodation(
                        Tent.builder()
                                .name("가족용 텐트")
                                .arrivalDate(2024, 7, 15)
                                .nights(5)
                                .siteNumber(34)
                                .build()
                )
                .build();

        // Then - 예약 정보가 포함되어야 함
        String result = vacation.toString();
        assertThat(result).contains("가족용 텐트");
        assertThat(result).contains("2024년 7월 15일");
        assertThat(result).contains("5박");
        assertThat(result).contains("사이트 번호 34");
    }
}
