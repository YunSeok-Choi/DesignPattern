package domain;

public class Screen {
    private String description;

	public Screen(String description) {
		this.description = description;
	}

	public String up() {
		return description + " going up";
	}

	public String down() {
		return description + " going down";
	}


	public String toString() {
		return description;
	}
}
