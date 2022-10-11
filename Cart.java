// Sayyada Aisha Mehvish
// 501106795

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ListIterator;

public class Cart {
	private ArrayList<CartItem> item;

	public Cart() {
		this.item = new ArrayList<CartItem>();
	}

	public void addToCart(CartItem item) {
		this.item.add(item);
	}

	public void removeItem(CartItem i) {
		ListIterator<CartItem> iter = item.listIterator();
		while (iter.hasNext()) {
			CartItem item1 = iter.next();
			if (item1.getProduct().getName().equals(i.getProduct().getName())) {
				this.item.remove(i);
				break;
			}
		}
	}

	public void removeFromCart(String customerId, String productId) {
		if (item != null) {
			for (CartItem itemToRemove : item) {
				if (itemToRemove.getProduct().getId().equals(productId)) {
					item.remove(itemToRemove);
					return;
				}
			}
		}
	}

	public void printCart() {
		ListIterator<CartItem> iter = item.listIterator();
		while (iter.hasNext()) {
			CartItem item = iter.next();
			item.print();
		}
	}

	public ArrayList<CartItem> getItems()
	{
		return item;
	}

	public void setItems(ArrayList<CartItem> item)
	{
		this.item = item;
	}
}
