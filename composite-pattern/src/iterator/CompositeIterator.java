package iterator;

import component.MenuComponent;

import java.util.*;

/**
 * Composite 패턴의 트리 구조를 순회하는 Iterator
 *
 * - 깊이 우선 탐색(DFS, Depth-First Search) 방식으로 트리 순회
 * - Stack을 사용하여 현재 탐색 중인 각 레벨의 Iterator를 관리
 * - Menu(Composite)를 만나면 그 자식들의 Iterator를 스택에 push
 * - MenuItem(Leaf)를 만나면 NullIterator가 push되지만 즉시 pop됨
 */
public class CompositeIterator implements Iterator<MenuComponent> {

    private Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    @Override
    public MenuComponent next() {
        if (hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent component = iterator.next();
            stack.push(component.createIterator());  // DFS를 위해 자식 Iterator를 스택에 추가
            return component;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator<MenuComponent> iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();               // 현재 레벨 순회 완료, 상위 레벨로 돌아감
                return hasNext();          // 재귀 호출로 백트래킹
            } else {
                return true;
            }
        }
    }

}
