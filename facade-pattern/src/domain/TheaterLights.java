package domain;

public class TheaterLights {
    private String description;

	public TheaterLights(String description) {
		this.description = description;
	}

	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String dim(int level) {
		return description + " dimming to " + level  + "%";
	}

	public String toString() {
		return description;
	}
}
