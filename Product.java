import java.util.ArrayList;

// Sayyada Aisha Mehvish
// 501106795

/*
 * class Product defines a product for sale by the system. 
 * 
 * A product belongs to one of the 5 categories below. 
 * 
 * Some products also have various options (e.g. size, color, format, style, ...). The options can affect
 * the stock count(s). In this generic class Product, product options are not used in get/set/reduce stockCount() methods  
 * 
 * Some products
 */
public class Product {
	public static enum Category {
		GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES
	};

	private String name;
	private String id;
	private Category category;
	private double price;
	private int stockCount;
	private ArrayList<Integer> ratings;

	public Product() {
		this.name = "Product";
		this.id = "001";
		this.category = Category.GENERAL;
		this.stockCount = 0;
	}

	public Product(String id) {
		this("Product", id, 0, 0, Category.GENERAL);
	}

	public Product(String name, String id, double price, int stock, Category category) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
	}

	public Product(String name, String id, double price, int stock, Category category, ArrayList<Integer> ratings) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
		this.ratings = ratings;
	}

	/*
	 * This method always returns true in class Product. In subclasses, this method
	 * will be overridden
	 * and will check to see if the options specified are valid for this product.
	 */
	public boolean validOptions(String productOptions) {
		if (productOptions.equals("null") || productOptions.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Integer> getRating() {
		return ratings;
	}

	public void setRatings(ArrayList<Integer> ratings) {
		this.ratings = ratings;
	}

	/*
	 * Return the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may
	 * be used
	 * in subclasses.
	 */
	public int getStockCount(String productOptions) {
		return stockCount;
	}

	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may
	 * be used
	 * in subclasses.
	 */
	public void setStockCount(int stockCount, String productOptions) {
		this.stockCount = stockCount;
	}

	/*
	 * Reduce the number of items currently in stock for this product by 1 (called
	 * when a product has
	 * been ordered by a customer)
	 * Note: in this general class, the productOptions parameter is not used. It may
	 * be used
	 * in subclasses.
	 */
	public void reduceStockCount(String productOptions) {
		stockCount--;
	}

	public void print() {
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}

	/*
	 * Two products are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other) {
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}


	public int getUserRating(int index) {
		/* using get(index) method of arraylist returns the reating */
		return this.ratings.get(index);
	}

	public int getAvgUserRating() {
		// initialize varibales to store sum and avg of ratings
		int avgRating, sumOfRatings = 0;
		for (int rating : ratings) {
			sumOfRatings += rating;
		}
		/* calculate average sumOfRatings / total number of ratings */
		avgRating = sumOfRatings / this.ratings.size();
		return avgRating;
 
	}

}
