package aggregate;

import iterator.Iterator;
import model.MenuItem;

public interface Menu {
	public Iterator<MenuItem> createIterator();
}
