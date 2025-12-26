package domain;

public class Amplifier {
	private String description;
	private Tuner tuner;
	private StreamingPlayer player;
	
	public Amplifier(String description) {
		this.description = description;
	}
 
	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String setStereoSound() {
		return description + " stereo mode on";
	}

	public String setSurroundSound() {
		return description + " surround sound on (5 speakers, 1 subwoofer)";
	}

	public String setVolume(int level) {
		return description + " setting volume to " + level;
	}

	public String setTuner(Tuner tuner) {
		this.tuner = tuner;
		return description + " setting tuner to " + tuner;
	}

	public String setStreamingPlayer(StreamingPlayer player) {
		this.player = player;
		return description + " setting Streaming player to " + player;
	}
 
	public String toString() {
		return description;
	}
}
