package domain;

public class Tuner {
    private String description;
    private Amplifier amplifier;
    private     double frequency;

	public Tuner(String description, Amplifier amplifier) {
		this.description = description;
	}

	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String setFrequency(double frequency) {
		this.frequency = frequency;
		return description + " setting frequency to " + frequency;
	}

	public String setAm() {
		return description + " setting AM mode";
	}

	public String setFm() {
		return description + " setting FM mode";
	}

	public String toString() {
		return description;
	}
}
