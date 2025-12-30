package aggregate;

import iterator.DinerMenuIterator;
import iterator.Iterator;
import model.MenuItem;

public class DinerMenu implements Menu {

    private static final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("채식 BLT",
                "베이컨 대용품과 상추, 토마토를 곁들인 통밀빵", true, 8500);
        addItem("BLT",
                "베이컨과 상추, 토마토를 곁들인 통밀빵", false, 8500);
        addItem("오늘의 스프",
                "오늘의 스프와 감자 샐러드", false, 9500);
        addItem("핫도그",
                "사우어크라우트, 양념, 양파, 치즈를 올린 핫도그",
                false, 8800);
        addItem("찐 야채와 현미밥",
                "현미밥 위에 찐 야채", true, 11500);
        addItem("파스타",
                "마리나라 소스 스파게티와 사워도우 빵 한 조각",
                true, 11200);
    }

    public void addItem(String name, String description,
                        boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems < MAX_ITEMS) {
            menuItems[numberOfItems] = menuItem;
            numberOfItems = numberOfItems + 1;
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    @Override
    public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
