package builder;

public class OutdoorsVacationBuilder extends VacationBuilder {

    public OutdoorsVacationBuilder() {
        this.name = "야외 여행";
    }

    @Override
    public VacationBuilder addEvent(String event) {
        this.events.add("하이킹: " + event);
        return this;
    }
}
