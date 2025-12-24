package receiver;

public class TV {

    private String location;
	private int channel;

	public TV(String location) {
		this.location = location;
	}

	public void on() {
		System.out.println(location + " TV가 켜졌습니다");
	}

	public void off() {
		System.out.println(location + " TV가 꺼졌습니다");
	}

	public void setInputChannel() {
		this.channel = 3;
		System.out.println(location + " 채널이 VCR로 설정되었습니다");
	}
}
