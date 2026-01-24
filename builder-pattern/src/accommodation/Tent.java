package accommodation;

public class Tent extends Accommodation {

    private int siteNumber;

    private Tent() {
    }

    public int getSiteNumber() {
        return this.siteNumber;
    }

    @Override
    public String getLocation() {
        if (siteNumber == 0) return "";
        else return "사이트 번호 " + this.siteNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name = "텐트";
        private int year;
        private int month;
        private int day;
        private int nights;
        private int siteNumber;
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

        public Builder siteNumber(int siteNumber) {
            this.siteNumber = siteNumber;
            return this;
        }

        public Tent build() {
            Tent tent = new Tent();
            tent.name = this.name;
            tent.siteNumber = this.siteNumber;

            if (hasReservation) {
                Reservation reservation = new Reservation();
                reservation.setArrivalDate(year, month, day);
                reservation.setNights(nights);
                tent.reservation = reservation;
            }

            return tent;
        }
    }
}
