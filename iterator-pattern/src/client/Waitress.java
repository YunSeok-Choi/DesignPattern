package client;

import aggregate.Menu;
import model.MenuItem;
import iterator.Iterator;

public class Waitress {

    private Menu pancakeHouseMenu;
    private Menu dinerMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public String printMenu() {
        StringBuilder result = new StringBuilder();
        Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();

        result.append("메뉴\n----\n아침 메뉴\n");
        result.append(printMenuItems(pancakeIterator));
        result.append("\n점심 메뉴\n");
        result.append(printMenuItems(dinerIterator));

        return result.toString();
    }

    private String printMenuItems(Iterator<MenuItem> iterator) {
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            result.append(menuItem.getName()).append(", ");
            result.append(menuItem.getPrice()).append("원 -- ");
            result.append(menuItem.getDescription()).append("\n");
        }
        return result.toString();
    }

    public String printVegetarianMenu() {
        StringBuilder result = new StringBuilder();
        result.append("채식 메뉴\n----\n");
        result.append(printVegetarianMenuItems(pancakeHouseMenu.createIterator()));
        result.append(printVegetarianMenuItems(dinerMenu.createIterator()));
        return result.toString();
    }

    public boolean isItemVegetarian(String name) {
        Iterator<MenuItem> breakfastIterator = pancakeHouseMenu.createIterator();
        if (isVegetarian(name, breakfastIterator)) {
            return true;
        }
        Iterator<MenuItem> dinnerIterator = dinerMenu.createIterator();
        if (isVegetarian(name, dinnerIterator)) {
            return true;
        }
        return false;
    }


    private String printVegetarianMenuItems(Iterator<MenuItem> iterator) {
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            if (menuItem.isVegetarian()) {
                result.append(menuItem.getName());
                result.append("\t\t").append(menuItem.getPrice()).append("원\n");
                result.append("\t").append(menuItem.getDescription()).append("\n");
            }
        }
        return result.toString();
    }

    private boolean isVegetarian(String name, Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            if (menuItem.getName().equals(name)) {
                if (menuItem.isVegetarian()) {
                    return true;
                }
            }
        }
        return false;
    }
}
