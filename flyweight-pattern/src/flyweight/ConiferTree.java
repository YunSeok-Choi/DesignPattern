package flyweight;

public class ConiferTree implements Tree {
	// 복잡한 줄기, 가지, 침엽 그래픽 데이터

	@Override
	public String display(int x, int y) {
		return TreeType.CONIFER.getDisplayName() + "가 위치한 좌표: " + x + ", " + y;
	}
}
