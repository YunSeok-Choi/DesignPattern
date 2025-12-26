package domain;

public class CdPlayer {
	private String description;
    private int currentTrack;
    private Amplifier amplifier;
    private String title;
	
	public CdPlayer(String description, Amplifier amplifier) {
		this.description = description;
		this.amplifier = amplifier;
	}
 
	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String eject() {
		title = null;
		return description + " eject";
	}

	public String play(String title) {
		this.title = title;
		currentTrack = 0;
		return description + " playing \"" + title + "\"";
	}

	public String play(int track) {
		if (title == null) {
			return description + " can't play track " + currentTrack +
					", no cd inserted";
		} else {
			currentTrack = track;
			return description + " playing track " + currentTrack;
		}
	}

	public String stop() {
		currentTrack = 0;
		return description + " stopped";
	}

	public String pause() {
		return description + " paused \"" + title + "\"";
	}
 
	public String toString() {
		return description;
	}
}
