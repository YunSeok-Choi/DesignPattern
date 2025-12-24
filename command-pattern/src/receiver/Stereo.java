package receiver;

public class Stereo {

    private String location;

	public Stereo(String location) {
		this.location = location;
	}

	public void on() {
		System.out.println(location + " 오디오가 켜졌습니다");
	}

	public void off() {
		System.out.println(location + " 오디오가 꺼졌습니다");
	}

	public void setCD() {
		System.out.println(location + " 오디오 CD 입력으로 설정되었습니다");
	}

	public void setDVD() {
		System.out.println(location + " 오디오 DVD 입력으로 설정되었습니다");
	}

	public void setRadio() {
		System.out.println(location + " 오디오 라디오로 설정되었습니다");
	}

	public void setVolume(int volume) {
		// 볼륨 설정 코드
		// 유효 범위: 1-11 (11이 10보다 낫잖아요?)
		System.out.println(location + " 오디오 볼륨이 " + volume + "로 설정되었습니다");
	}
}
