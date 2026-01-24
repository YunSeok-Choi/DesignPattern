package accommodation;

public abstract class Accommodation {

    protected String name;
    protected Reservation reservation;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getLocation();

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append("숙소: ").append(name);
        if (this.reservation != null) {
            display.append("\n예약 정보 - 도착일: ")
                    .append(reservation.getFormattedArrivalDate())
                    .append(", ")
                    .append(reservation.getNights())
                    .append("박");
        }
        if (!this.getLocation().isEmpty()) {
            display.append(", ").append(this.getLocation());
        }
        display.append("\n");
        return display.toString();
    }
}
