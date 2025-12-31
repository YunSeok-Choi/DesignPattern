package component;

import java.util.*;

/**
 * Composite 패턴의 Component 역할
 *
 * - Leaf(MenuItem)와 Composite(Menu)의 공통 인터페이스를 정의
 * - 클라이언트가 단일 객체와 복합 객체를 동일하게 다룰 수 있도록 함
 * - 기본 구현은 UnsupportedOperationException을 던지고,
 *   필요한 클래스에서 오버라이드하여 사용
 */
public abstract class MenuComponent {

	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

    public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		throw new UnsupportedOperationException();
	}

	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}

	public abstract Iterator<MenuComponent> createIterator();

	public String print() {
		throw new UnsupportedOperationException();
	}
}
