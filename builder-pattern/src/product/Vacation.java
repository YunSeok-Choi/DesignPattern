package product;

import accommodation.Accommodation;

import java.util.ArrayList;
import java.util.List;

public class Vacation {

    private String name;
    private List<Accommodation> accommodations = new ArrayList<>();
    private List<String> events = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public List<Accommodation> getAccommodations() {
        return this.accommodations;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<String> getEvents() {
        return this.events;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append("---- ").append(this.name).append(" ----\n");
        for (Accommodation a : accommodations) {
            display.append(a);
        }
        for (String e : events) {
            display.append(e).append("\n");
        }
        return display.toString();
    }
}
