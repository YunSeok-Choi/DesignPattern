package aggregate;

import iterator.Iterator;
import iterator.PancakeHouseMenuIterator;
import model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class PancakeHouseMenu implements Menu {

    private List<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<MenuItem>();

        addItem("K&B 팬케이크 조식",
                "스크램블 에그와 토스트를 곁들인 팬케이크",
                true,
                8500);

        addItem("레귤러 팬케이크 조식",
                "계란 후라이와 소시지를 곁들인 팬케이크",
                false,
                8500);

        addItem("블루베리 팬케이크",
                "신선한 블루베리로 만든 팬케이크",
                true,
                10000);

        addItem("와플",
                "블루베리 또는 딸기를 선택할 수 있는 와플",
                true,
                10300);
    }

    public void addItem(String name, String description,
                        boolean vegetarian, double price)
    {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }

    public String toString() {
        return "팬케이크 하우스 메뉴";
    }

}
