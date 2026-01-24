package builder;

import accommodation.Accommodation;
import product.Vacation;

import java.util.ArrayList;
import java.util.List;

public abstract class VacationBuilder {

    protected String name;
    protected List<Accommodation> accommodations = new ArrayList<>();
    protected List<String> events = new ArrayList<>();

    public VacationBuilder addAccommodation(Accommodation accommodation) {
        this.accommodations.add(accommodation);
        return this;
    }

    public abstract VacationBuilder addEvent(String event);

    public Vacation build() {
        Vacation vacation = new Vacation();
        vacation.setName(name);
        vacation.setAccommodations(accommodations);
        vacation.setEvents(events);
        return vacation;
    }
}
