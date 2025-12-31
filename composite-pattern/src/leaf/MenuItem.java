package leaf;

import component.MenuComponent;
import iterator.NullIterator;

import java.util.Iterator;

/**
 * Composite 패턴의 Leaf 역할
 * <p>
 * - 트리 구조의 말단 노드 (자식을 가질 수 없음)
 * - 개별 메뉴 항목을 표현
 * - add(), remove(), getChild() 같은 복합 객체 메서드는 상속받지만 사용하지 않음
 * - 실제 데이터(name, price 등)를 가지고 있는 객체
 */
public class MenuItem extends MenuComponent {

    private String name;          // 메뉴 이름
    private String description;   // 메뉴 설명
    private boolean vegetarian;   // 채식 여부
    private double price;         // 가격

    public MenuItem(
            String name,
            String description,
            boolean vegetarian,
            double price
    ) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new NullIterator();
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();
        result.append("  ").append(getName());
        if (isVegetarian()) {
            result.append("(채식)");
        }
        result.append(", ").append(getPrice()).append("원\n");
        result.append("     -- ").append(getDescription());
        return result.toString();
    }

}
