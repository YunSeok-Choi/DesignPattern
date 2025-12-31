package composite;

import component.MenuComponent;
import iterator.CompositeIterator;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * Composite 패턴의 Composite 역할
 *
 * - 자식 MenuComponent들을 가질 수 있는 복합 객체
 * - 자식으로 MenuItem(Leaf)과 Menu(Composite) 모두를 가질 수 있음
 * - 트리 구조를 구성하는 핵심 클래스
 * - add(), remove(), getChild() 메서드로 자식들을 관리
 * - print() 호출 시 모든 자식들의 print()를 재귀적으로 호출 (Composite 패턴의 핵심)
 */
public class Menu extends MenuComponent {

    private Iterator<MenuComponent> iterator = null;  // 트리 순회용 iterator (지연 초기화)
    private ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();  // 자식 컴포넌트 리스트
    private String name;                              // 메뉴 그룹 이름
    private String description;                       // 메뉴 그룹 설명

	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * 자식 컴포넌트 추가
	 * MenuItem이나 다른 Menu를 자식으로 추가할 수 있음
	 */
    @Override
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}

	/**
	 * 자식 컴포넌트 제거
	 */
    @Override
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}

	/**
	 * 특정 위치의 자식 컴포넌트 반환
	 * @param i 자식 컴포넌트의 인덱스
	 */
    @Override
	public MenuComponent getChild(int i) {
		return menuComponents.get(i);
	}

    @Override
	public String getName() {
		return name;
	}

    @Override
	public String getDescription() {
		return description;
	}


	/**
	 * 트리 구조를 순회하는 Iterator 생성
	 * CompositeIterator를 사용하여 모든 하위 메뉴와 메뉴 항목을 깊이 우선 탐색
	 * 지연 초기화(Lazy Initialization) 패턴 적용
	 */
    @Override
	public Iterator<MenuComponent> createIterator() {
		if (iterator == null) {
			iterator = new CompositeIterator(menuComponents.iterator());
		}
		return iterator;
	}

	/**
	 * 메뉴 정보를 문자열로 반환
	 * Composite 패턴의 핵심: 모든 자식의 print()를 재귀적으로 호출
	 * - 자식이 MenuItem이면 메뉴 항목 정보 출력
	 * - 자식이 Menu이면 하위 메뉴 전체를 재귀적으로 출력
	 */
    @Override
	public String print() {
		StringBuilder result = new StringBuilder();
		result.append("\n").append(getName());
		result.append(", ").append(getDescription()).append("\n");
		result.append("---------------------\n");

		// 모든 자식의 print() 호출 (재귀적 처리)
		Iterator<MenuComponent> iterator = menuComponents.iterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = iterator.next();
			result.append(menuComponent.print()).append("\n");
		}
		return result.toString();
	}
}
