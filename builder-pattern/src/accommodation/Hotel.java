package accommodation;

import java.time.LocalDate;

public class Hotel extends Accommodation {

    private int roomNumber;

    private Hotel() {}

    public int getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public String getLocation() {
        if (roomNumber == 0) return "";
        else return "객실 번호 " + this.roomNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name = "호텔";
        private int year;
        private int month;
        private int day;
        private int nights;
        private int roomNumber;
        private boolean hasReservation = false;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder arrivalDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hasReservation = true;
            return this;
        }

        public Builder nights(int nights) {
            this.nights = nights;
            return this;
        }

        public Builder roomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Hotel build() {
            Hotel hotel = new Hotel();
            hotel.name = this.name;
            hotel.roomNumber = this.roomNumber;

            if (hasReservation) {
                Reservation reservation = new Reservation();
                reservation.setArrivalDate(year, month, day);
                reservation.setNights(nights);
                hotel.reservation = reservation;
            }

            return hotel;
        }
    }
}
