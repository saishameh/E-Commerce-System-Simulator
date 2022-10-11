// Sayyada Aisha Mehvish
// 501106795

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {

  private Map<String, Product> products = new TreeMap<String, Product>();
  private ArrayList<Product> productsList = new ArrayList<Product>();
  private ArrayList<Customer> customers = new ArrayList<Customer>();

  private Map<String, ArrayList<Integer>> ratings = new TreeMap<String, ArrayList<Integer>>(); //map which keeps product ids as keys and ratings as values
  private ArrayList<Integer> userRating = new ArrayList<Integer>();  //arraylist to keep ratings 

  private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
  private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

  // These variables are used to generate order numbers, customer id's, product
  // id's
  private int orderNumber = 500;
  private int customerId = 900;
  private int productId = 700;

  // Random number generator
  Random random = new Random();

  Cart cart = new Cart();

  public ECommerceSystem() {

    try {
      productsList = openFile("products.txt");
      for (Product p : productsList) {
        String id = p.getId();
        products.put(id, p); // added to the map of products
      }
    } catch (IOException e) {
      System.exit(1);
    }

    // Created some shoes.
    productsList.add(new Shoes("Shoes", generateProductId(), 45.0, 6, "Black", 9));
    productsList.add(new Shoes("Shoes", generateProductId(), 99.0, 8, "Brown", 6));
    productsList.add(new Shoes("Shoes", generateProductId(), 59.0, 10, "Black", 11));
    // Create some customers. Notice how generateCustomerId() method is used
    customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin", new Cart()));
    customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin", new Cart()));
    customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine", new Cart()));
    customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach", new Cart()));

    for (Product p : productsList) {
      String id = p.getId();
      ratings.put(id, p.getRating());
    }

  }

  private ArrayList<Product> openFile(String filename) throws IOException {
    File inputFile = new File(filename);
    Scanner in = new Scanner(inputFile);
    Product.Category category = Product.Category.GENERAL;

    while (in.hasNext()) {
      String temp;
      String name;
      String id;
      double price;
      String stock;

      temp = in.nextLine();
      name = in.nextLine();
      price = Double.parseDouble(in.nextLine());
      stock = (in.nextLine());
      id = generateProductId();

      if (!temp.equals("BOOKS")) {
        String extra = in.nextLine(); // empty line
        if (temp.equals("COMPUTERS"))
          category = Product.Category.COMPUTERS;
        else if (temp.equals("FURNITURE"))
          category = Product.Category.FURNITURE;
        if (temp.equals("CLOTHING"))
          category = Product.Category.CLOTHING;
        int intStock = Integer.parseInt(stock);
        productsList.add(new Product(name, id, price, intStock, category));
      } else {
        category = Product.Category.BOOKS;
        String[] splitCount = stock.split("\\s+"); // split to get stock count
        String information;
        information = in.nextLine();
        String[] splitInfo = information.split(":"); // split to get info about book title/author/year
        productsList.add(new Book(name, id, price, Integer.parseInt(splitCount[0]),
            Integer.parseInt(splitCount[1]),
            splitInfo[0], splitInfo[1], Integer.parseInt(splitInfo[2])));
      }
    }
    return productsList;
  }

  private String generateOrderNumber() {
    return "" + orderNumber++;
  }

  private String generateCustomerId() {
    return "" + customerId++;
  }

  private String generateProductId() {
    return "" + productId++;
  }

  public void printAllProducts() {

    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      Product p = products.get(key);
      p.print();
    }
  }

  // Print all products that are books. See getCategory() method in class Product
  public void printAllBooks() {
    Set<String> keySet = products.keySet();
    ;
    for (String key : keySet) {
      Product pr = products.get(key);
      if (pr.getCategory().equals(Product.Category.BOOKS)) {
        pr.print();
      }
    }

  }

  // Print all current orders2
  public void printAllOrders() {
    for (ProductOrder pOrder : orders)
      pOrder.print();
  }

  // Print all shipped orders
  public void printAllShippedOrders() {
    for (ProductOrder shipped : shippedOrders)
      shipped.print();
  }

  // Print all customers
  public void printCustomers() {
    for (Customer c : customers)
      c.print();
  }

  /*
   * Given a customer id, print all the current orders and shipped orders for them
   * (if any)
   */
  public void printOrderHistory(String customerId) throws unknownCust {
    // Make sure customer exists - check using customerId
    // If customer does not exist, set errMsg String and return false
    // see video for an appropriate error message string
    // ... code here
    Customer cust = null;
    boolean Cexists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        Cexists = true;
        cust = c;
      }
    }
    if (Cexists == false) {
      throw new unknownCust("Customer ID: " + customerId + " Not Found");
    }
    // Print current orders of this customer
    System.out.println("Current Orders of Customer " + customerId);
    // enter code here
    for (ProductOrder pOrder : orders) {
      if (pOrder.getCustomer().equals(cust))
        pOrder.print();
    }
    // Print shipped orders of this customer
    System.out.println("\nShipped Orders of Customer " + customerId);
    // enter code here
    for (ProductOrder sOrder : shippedOrders) {
      if (sOrder.getCustomer().equals(cust))
        sOrder.print();
    }
    // return true;
  }

  public String orderProduct(String productId, String customerId, String productOptions)
      throws unknownCust, unknownProd, invalidOption, prodOutOfStock {
    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    Customer cust = null;
    boolean Cexists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        Cexists = true;
        cust = c;
      }
    }
    if (Cexists == false) {
      throw new unknownCust("Customer ID: " + customerId + " Not Found");
    }
    // Check to see if product object with productId exists in array list of
    // products
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Product object
    Product p = null;
    boolean Pexists = false;

    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      Product prod = products.get(key);
      if (prod.getId().equals(productId)) {
        Pexists = true;
        p = prod;
      }
    }

    if (Pexists == false) {
      throw new unknownProd("Product ID: " + productId + " Not Found");
    }
    // Check if the options are valid for this product (e.g. Paperback or Hardcover
    // or EBook for Book product)
    // See class Product and class Book for the method vaidOptions()
    // If options are not valid, set errMsg string and return null;
    if (!p.validOptions(productOptions)) {
      throw new invalidOption(
          "Product " + p.getCategory() + " ProductId " + productId + " Invalid Options: " + productOptions);
    }

    // Check if the product has stock available (i.e. not 0)
    // See class Product and class Book for the method getStockCount()
    // If no stock available, set errMsg string and return null
    if (p.getStockCount(productOptions) <= 0) {
      throw new prodOutOfStock("Product " + productId + " is not in stock.");
    }

    // Create a ProductOrder, (make use of generateOrderNumber() method above)
    // reduce stock count of product by 1 (see class Product and class Book)
    // Add to orders list and return order number string
    p.reduceStockCount(productOptions);

    // Product productID=p.getId();
    // Customer customerID = c.getId();
    String orderNum = generateOrderNumber();
    orders.add(new ProductOrder(orderNum, p, cust, productOptions));
    return orderNum; // replace this line
  }

  /*
   * Create a new Customer object and add it to the list of customers
   */

  public void createCustomer(String name, String address) throws invalidCustName, invalidAddress {
    // Check name parameter to make sure it is not null or ""
    // If it is not a valid name, set errMsg (see video) and return false
    // Repeat this check for address parameter
    if (name.equals(null) || name.equals("")) {
      throw new invalidCustName("Invalid Customer Name");
    }
    if (address.equals("null") || address.equals("")) {
      throw new invalidAddress("Invalid Customer Address");
    }
    // Create a Customer object and add to array list
    customers.add(new Customer(generateCustomerId(), name, address, cart));
  }

  public void shipOrder(String orderNumber) throws invalidOrder {
    // Check if order number exists first. If it doesn't, set errMsg to a message
    // (see video)
    // and return false
    // Retrieve the order from the orders array list, remove it, then add it to the
    // shippedOrders array list
    // return a reference to the order
    int index = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));
    if (index == -1) {
      throw new invalidOrder("Order " + orderNumber + " Not Found");
    }
    ProductOrder order = orders.get(index);
    orders.remove(index);
    shippedOrders.add(order);

  }

  /*
   * Cancel a specific order based on order number
   */
  public void cancelOrder(String orderNumber) throws invalidOrder {
    // Check if order number exists first. If it doesn't, set errMsg to a message
    // (see video)
    // and return false
    // Check if order number exists
    int index = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));
    if (index == -1) {
      throw new invalidOrder("Order " + orderNumber + " Not Found");
    }
    ProductOrder order = orders.get(index);
    orders.remove(index);
  }

  // Print products by increasing price
  public void printByPrice() {

    ArrayList<Product> arrayToSort = new ArrayList<Product>();
    for (Product P : products.values()) {
      arrayToSort.add(P);
    }
    class priceComparator implements Comparator<Product> {
      public int compare(Product a, Product b) {
        return (int) (a.getPrice() - b.getPrice());
      }
    }
    Collections.sort(arrayToSort, new priceComparator());

    for (Product p : arrayToSort) {
      p.print();
    }
  }

  // Print products alphabetically by product name
  public void printByName() {
    ArrayList<Product> arrayToSort = new ArrayList<Product>();
    for (Product P : products.values()) {
      arrayToSort.add(P);
    }
    class nameComparator implements Comparator<Product> {
      public int compare(Product a, Product b) {
        String name1 = a.getName().toUpperCase();
        String name2 = b.getName().toUpperCase();
        return name1.compareTo(name2);
      }
    }
    Collections.sort(arrayToSort, new nameComparator());
    for (Product p : arrayToSort) {
      p.print();
    }
  }

  // Sort products alphabetically by customer name
  public void sortCustomersByName() {
    Collections.sort(customers);
  }

  // Sort books in increasing order of the same author
  public void printBooksByAuthor(String author) {

    Product p = null;
    ArrayList<Book> books = new ArrayList<Book>();

    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      p = products.get(key);
      if (p.getName().equals("Book")) {
        books.add((Book) p);
      }
    }

    ArrayList<Book> booksByAuthor = new ArrayList<Book>();
    for (Book b : books) {
      if (b.getAuthorName().equals(author)) {
        booksByAuthor.add(b);
      }
    }

    class nameComparator implements Comparator<Book> {
      public int compare(Book a, Book b) {
        return a.getyear() - b.getyear();
      }
    }

    Collections.sort(booksByAuthor, new nameComparator());
    for (Book b : booksByAuthor) {
      b.print();
    }
  }

  public void addToCart(String productId, String customerId, String options)
      throws unknownProd, unknownCust {
    Product p = null;
    boolean Pexists = false;

    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      Product prod = products.get(key);
      if (prod.getId().equals(productId)) {
        p = prod;
        Pexists = true;

      }
    }

    if (Pexists == false) {
      throw new unknownProd("Product ID: " + productId + " Not Found");
    }

    Customer cust = null;
    boolean Cexists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        Cexists = true;
        cust = c;
      }
    }
    if (Cexists == false) {
      throw new unknownCust("Customer ID: " + customerId + " Not Found");
    }

    CartItem item = new CartItem(p, options);
    cust.getCart().addToCart(item);
  }

  public void removeFromCart(String customerId, String productId) throws unknownCust {
    Customer cust = null;
    boolean Cexists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        Cexists = true;
        cust = c;
      }
    }

    if (Cexists == false) {
      throw new unknownCust("Customer ID: " + customerId + " Not Found");
    }

    cust.getCart().removeFromCart(customerId, productId);
  }

  public void printCart(String customerId) throws unknownCust {
    Customer cust = null;
    boolean Cexists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        Cexists = true;
        cust = c;
      }
    }
    if (Cexists == false) {
      throw new unknownCust("Customer ID: " + customerId + " Not Found");

    }

    cust.getCart().printCart();
  }

  public void orderItems(String customerId) throws unknownCust {
    Customer cust = null;
    boolean Cexists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        Cexists = true;
        cust = c;
      }
    }
    if (Cexists == false) {
      throw new unknownCust("Customer ID: " + customerId + " Not Found");

    } else {
      ArrayList<CartItem> Items = cust.getCart().getItems(); 
      ArrayList<CartItem> allItems = new ArrayList<CartItem>();
      allItems.addAll(Items); // adds all the items 
      for (CartItem C : allItems) {
        String orderNumber = orderProduct(C.getProduct().getId(), customerId, C.getProductOptions());
        cust.getCart().removeFromCart(customerId, C.getProduct().getId());
        System.out.println("Order #" + orderNumber + " has been placed.");

      }
    }

  }

  public String Voptions(String productId) { // to display the valid product options for a product
    Product p = null;
    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      Product prod = products.get(key);
      if (prod.getId().equals(productId)) {
        p = prod;

        if (p.getCategory().equals(Product.Category.BOOKS))
          return ("\nBook Options: Paperback  HardCover EBook");
        else if (p.getCategory().equals(Product.Category.SHOES))
          return ("\nShoe Options: 6 Black, 8 Brown, 10 Black");

      }
    }
    return "\nHit Enter";
  }

  public void productOrderStats() {

    ArrayList<String> prodID = new ArrayList<String>();
    Map<String, Integer> prods = new TreeMap<String, Integer>(); // map which keeps product id as keys and number of times ordered as values
    int numOrder = 0; // number of times a product was ordered

    for (ProductOrder pO : orders) {
      prodID.add(pO.getProduct().getId());
    }

    for (String pId : prodID) {
      if (prods.containsKey(pId)) {
        numOrder = prods.get(pId);
        numOrder++;
        prods.put(pId, numOrder);
      }

      else {
        prods.put(pId, 1);
      }

    }

    ArrayList<String> temp = new ArrayList<String>();
    Set<String> keySet = prods.keySet();
    for (String key : keySet) {
      int count = 0;
      for (ProductOrder pOrder : orders) {
        if (key.equals(pOrder.getProduct().getId())) {
          if (!temp.contains(pOrder.getProduct().getId())) { // checks if the product has been ordered
            String name = pOrder.getProduct().getName();
            temp.add(pOrder.getProduct().getId());
            count = prods.get(key);  // gets the number of times a product was ordered 
            System.out.printf("\n Product Id: %-5s Name: %-20s Ordered: %8s", key, name, count);
          }

          else {
            continue;
          }
        }
      }
    }
  }

  public void addUserRating(String productId, int RATE) throws unknownProd {

    Product p = null;
    boolean Pexists = false;

    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      Product prod = products.get(key);
      if (prod.getId().equals(productId)) {
        p = prod;
        Pexists = true;
      }
    }

    if (Pexists == false) {
      throw new unknownProd();
    }
    userRating.add(RATE);

    ratings.put(productId, userRating);
    System.out.println("Successfully added rating " + RATE + " to product with ID: " + productId);
  }

  public void printRating(String productId) throws unknownProd {
    Product p = null;
    boolean Pexists = false;

    Set<String> keySet = products.keySet();
    for (String key : keySet) {
      Product prod = products.get(key);
      if (prod.getId().equals(productId)) {
        p = prod;
        Pexists = true;
      }
    }

    if (Pexists == false) {
      throw new unknownProd("Product ID: " + productId + " Not Found");
    }

    Set<String> TEMP = ratings.keySet();
    for (String key : TEMP) {
      if (key.equals(productId)) {
        ArrayList<Integer> rate = ratings.get(key);
        System.out.println("Product with ID " + key + " has rating of " + rate);
      }
    }
  }

}

class unknownCust extends RuntimeException {
  public unknownCust(String message) {
    super(message);
  }

  public unknownCust() {
    super();
  }
}

class unknownProd extends RuntimeException {
  public unknownProd(String message) {
    super(message);
  }

  public unknownProd() {
    super();
  }
}

class invalidOption extends RuntimeException {
  public invalidOption(String message) {
    super(message);
  }

  public invalidOption() {
    super();
  }
}

class prodOutOfStock extends RuntimeException {
  public prodOutOfStock(String message) {
    super(message);
  }

  public prodOutOfStock() {
    super();
  }
}

class invalidCustName extends RuntimeException {
  public invalidCustName(String message) {
    super(message);
  }

  public invalidCustName() {
    super();
  }
}

class invalidAddress extends RuntimeException {
  public invalidAddress(String message) {
    super(message);
  }

  public invalidAddress() {
    super();
  }
}

class invalidOrder extends RuntimeException {
  public invalidOrder(String message) {
    super(message);
  }

  public invalidOrder() {
    super();
  }
}

class invalidAuthorName extends RuntimeException {
  public invalidAuthorName(String message) {
    super(message);
  }

  public invalidAuthorName() {
    super();
  }
}

class invalidCategory extends RuntimeException {
  public invalidCategory(String message) {
    super(message);
  }

  public invalidCategory() {
    super();
  }
}
