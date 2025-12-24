package receiver;

public class Hottub {

    private boolean on;
	private int temperature;

	public Hottub() {
	}

	public void on() {
		on = true;
	}

	public void off() {
		on = false;
	}

	public void bubblesOn() {
		if (on) {
			System.out.println("욕조에 거품이 생성되고 있습니다!");
		}
	}

	public void bubblesOff() {
		if (on) {
			System.out.println("욕조 거품이 꺼졌습니다");
		}
	}

	public void jetsOn() {
		if (on) {
			System.out.println("욕조 제트가 켜졌습니다");
		}
	}

	public void jetsOff() {
		if (on) {
			System.out.println("욕조 제트가 꺼졌습니다");
		}
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public void heat() {
		temperature = 105;
		System.out.println("욕조가 105도까지 가열되고 있습니다");
	}

	public void cool() {
		temperature = 98;
		System.out.println("욕조가 98도로 냉각되고 있습니다");
	}

}
