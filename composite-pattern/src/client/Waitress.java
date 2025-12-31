package client;

import component.MenuComponent;

import java.util.Iterator;

/**
 * Composite 패턴의 Client 역할
 *
 * - MenuComponent 인터페이스를 통해 객체들과 상호작용
 * - 단일 객체(MenuItem)와 복합 객체(Menu)를 구분하지 않고 동일하게 처리
 * - Composite 패턴의 핵심 장점: 클라이언트 코드가 단순해짐
 */
public class Waitress {

    private MenuComponent allMenus;  // 최상위 Menu (전체 메뉴 구조의 루트)

	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}

	/**
	 * 전체 메뉴 출력
	 * MenuComponent의 print()만 호출하면 됨
	 * - 내부적으로 재귀적으로 모든 하위 메뉴가 출력됨
	 * - 단일 객체든 복합 객체든 동일한 방식으로 처리 (Composite 패턴의 핵심)
	 */
	public String printMenu() {
		return allMenus.print();
	}

	/**
	 * 채식 메뉴만 출력
	 * Iterator를 사용하여 모든 MenuComponent를 순회하며 채식 메뉴만 필터링
	 *
	 * 동작 방식:
	 * 1. CompositeIterator로 전체 트리 구조를 순회
	 * 2. 각 component가 MenuItem인지 Menu인지 구분하지 않음
	 * 3. isVegetarian() 호출:
	 *    - MenuItem이면 채식 여부 반환
	 *    - Menu이면 UnsupportedOperationException 발생 (catch로 무시)
	 */
	public String printVegetarianMenu() {
		Iterator<MenuComponent> iterator = allMenus.createIterator();

		StringBuilder result = new StringBuilder();
		result.append("\n채식 메뉴\n----\n");
		while (iterator.hasNext()) {
			MenuComponent menuComponent = iterator.next();
			try {
				// MenuItem이면 채식 여부 확인 가능
				if (menuComponent.isVegetarian()) {
					result.append(menuComponent.print()).append("\n");
				}
			} catch (UnsupportedOperationException e) {
				// Menu는 isVegetarian()이 없으므로 예외 발생 (무시)
			}
		}
		return result.toString();
	}
}
