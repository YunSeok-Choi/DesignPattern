package flyweight;

import java.time.LocalDate;

public class DeciduousTree implements Tree {
	// 복잡한 줄기, 가지, 잎 그래픽 데이터

	@Override
	public String display(int x, int y) {
		return display(x, y, LocalDate.now());
	}

	public String display(int x, int y, LocalDate date) {
		StringBuilder sb = new StringBuilder();
		sb.append(TreeType.DECIDUOUS.getDisplayName()).append("가 위치한 좌표: ").append(x).append(", ").append(y);
		if (!this.isWithinRange(date)) {
			sb.append("\n현재 나무에 잎이 없습니다");
		}
		return sb.toString();
	}
}
