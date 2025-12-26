package domain;

public class Projector {
    private String description;
    private StreamingPlayer player;
	
	public Projector(String description, StreamingPlayer player) {
		this.description = description;
		this.player = player;
	}
 
	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String wideScreenMode() {
		return description + " in widescreen mode (16x9 aspect ratio)";
	}

	public String tvMode() {
		return description + " in tv mode (4x3 aspect ratio)";
	}
  
        public String toString() {
                return description;
        }
}
