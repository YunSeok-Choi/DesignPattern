package iterator;

import model.MenuItem;

public class DinerMenuIterator implements Iterator<MenuItem> {

    private MenuItem[] items;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public MenuItem next() {
        return items[position++];
    }

    @Override
    public boolean hasNext() {
        return items.length > position;
    }
}
