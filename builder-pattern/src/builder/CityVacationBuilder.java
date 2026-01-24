package builder;

public class CityVacationBuilder extends VacationBuilder {

    public CityVacationBuilder() {
        this.name = "도시 여행";
    }

    @Override
    public VacationBuilder addEvent(String event) {
        this.events.add(event + " 공연 관람");
        return this;
    }
}
