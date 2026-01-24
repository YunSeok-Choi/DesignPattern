package accommodation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy년 M월 d일");

    private LocalDate arrivalDate;
    private int nights;

    public void setArrivalDate(int year, int month, int day) {
        this.arrivalDate = LocalDate.of(year, month, day);
    }

    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    public String getFormattedArrivalDate() {
        return arrivalDate.format(FORMATTER);
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getNights() {
        return this.nights;
    }
}
