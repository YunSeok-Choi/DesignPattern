package receiver;

public class GarageDoor {

    private String location;

	public GarageDoor(String location) {
		this.location = location;
	}

	public void up() {
		System.out.println(location + " 차고 문이 열렸습니다");
	}

	public void down() {
		System.out.println(location + " 차고 문이 닫혔습니다");
	}

	public void stop() {
		System.out.println(location + " 차고 문이 정지했습니다");
	}

	public void lightOn() {
		System.out.println(location + " 차고 조명이 켜졌습니다");
	}

	public void lightOff() {
		System.out.println(location + " 차고 조명이 꺼졌습니다");
	}
}
