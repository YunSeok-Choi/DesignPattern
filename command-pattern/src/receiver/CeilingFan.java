package receiver;

public class CeilingFan {

	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
    public static final int OFF = 0;

    private String location = "";
    private int level;

	public CeilingFan(String location) {
		this.location = location;
	}
  
	public void high() {
		level = HIGH;
		System.out.println(location + " 천장 선풍기가 강으로 설정되었습니다");

	}

	public void medium() {
		level = MEDIUM;
		System.out.println(location + " 천장 선풍기가 중으로 설정되었습니다");
	}

	public void low() {
		level = LOW;
		System.out.println(location + " 천장 선풍기가 약으로 설정되었습니다");
	}

	public void off() {
		level = 0;
		System.out.println(location + " 천장 선풍기가 꺼졌습니다");
	}
 
	public int getSpeed() {
		return level;
	}
}
