package domain;

public class StreamingPlayer {
    private String description;
    private int currentChapter;
    private Amplifier amplifier;
    private String movie;
	
	public StreamingPlayer(String description, Amplifier amplifier) {
		this.description = description;
		this.amplifier = amplifier;
	}
 
	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String play(String movie) {
		this.movie = movie;
		currentChapter = 0;
		return description + " playing \"" + movie + "\"";
	}

	public String play(int chapter) {
		if (movie == null) {
			return description + " can't play chapter " + chapter + " no movie selected";
		} else {
			currentChapter = chapter;
			return description + " playing chapter " + currentChapter + " of \"" + movie + "\"";
		}
	}

	public String stop() {
		currentChapter = 0;
		return description + " stopped \"" + movie + "\"";
	}

	public String pause() {
		return description + " paused \"" + movie + "\"";
	}

	public String setTwoChannelAudio() {
		return description + " set two channel audio";
	}

	public String setSurroundAudio() {
		return description + " set surround audio";
	}
 
	public String toString() {
		return description;
	}
}
