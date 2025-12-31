package iterator;

import component.MenuComponent;

import java.util.Iterator;

/**
 * Null Object 패턴을 활용한 Iterator
 *
 * - MenuItem(Leaf 노드)처럼 자식이 없는 객체를 위한 Iterator
 * - null을 반환하는 대신 빈 Iterator 객체를 반환
 * - 클라이언트 코드에서 null 체크를 하지 않아도 됨
 * - hasNext()는 항상 false, next()는 항상 null 반환
 */
public class NullIterator implements Iterator<MenuComponent> {

	/**
	 * 항상 null 반환 (순회할 요소가 없음)
	 */
	public MenuComponent next() {
		return null;
	}

	/**
	 * 항상 false 반환 (순회할 요소가 없음)
	 */
	public boolean hasNext() {
		return false;
	}

}
