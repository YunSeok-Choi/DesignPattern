package domain;

public class PopcornPopper {
    private String description;
	
	public PopcornPopper(String description) {
		this.description = description;
	}
 
	public String on() {
		return description + " on";
	}

	public String off() {
		return description + " off";
	}

	public String pop() {
		return description + " popping popcorn!";
	}
 
  
        public String toString() {
                return description;
        }
}
