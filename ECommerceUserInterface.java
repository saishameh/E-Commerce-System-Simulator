// Sayyada Aisha Mehvish
// 501106795

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {
	public static void main(String[] args) {
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
			try {
				{
					String action = scanner.nextLine();

					if (action == null || action.equals("")) {
						System.out.print("\n>");
						continue;
					} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
						return;

					else if (action.equalsIgnoreCase("PRODS")) // List all products for sale
					{
						amazon.printAllProducts();
					} else if (action.equalsIgnoreCase("BOOKS")) // List all books for sale
					{
						amazon.printAllBooks();
					} else if (action.equalsIgnoreCase("CUSTS")) // List all registered customers
					{
						amazon.printCustomers();
					} else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
					{
						amazon.printAllOrders();
					} else if (action.equalsIgnoreCase("SHIPPED")) // List all orders that have been shipped
					{
						amazon.printAllShippedOrders();
					} else if (action.equalsIgnoreCase("NEWCUST")) // Create a new registered customer
					{
						String name = "";
						String address = "";

						System.out.print("Name: ");
						if (scanner.hasNextLine())
							name = scanner.nextLine();

						System.out.print("\nAddress: ");
						if (scanner.hasNextLine())
							address = scanner.nextLine();

						amazon.createCustomer(name, address);

					} else if (action.equalsIgnoreCase("SHIP")) // ship an order to a customer
					{
						String orderNumber = "";

						System.out.print("Order Number: ");
						if (scanner.hasNextLine())
							orderNumber = scanner.nextLine();

						amazon.shipOrder(orderNumber);

					} else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders
																		// for this customer id
					{
						String customerId = "";

						System.out.print("Customer Id: ");
						// Get customer Id from scanner
						if (scanner.hasNextLine())
							customerId = scanner.nextLine();
						// Print all current orders and all shipped orders for this customer
						amazon.printOrderHistory(customerId);
					} else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
					{
						String productId = "";
						String customerId = "";

						System.out.print("Product Id: ");
						// Get product Id from scanner
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						System.out.print("\nCustomer Id: ");
						// Get customer Id from scanner
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}

						String options = "";

						// Order the product. Check for valid orderNumber string return and for error
						// message set in ECommerceSystem
						String success = amazon.orderProduct(productId, customerId, options);
						// if (success == null)
						{
							// System.out.println(amazon.getErrorMessage());
						}
						// // // Print Order Number string returned from method in ECommerceSystem

						System.out.println("Order #" + success);

					} else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format
																		// (Paperback, Hardcover or EBook)
					{
						String productId = "";
						String customerId = "";
						String options = "";

						System.out.print("Product Id: ");
						// get product id
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						System.out.print("\nCustomer Id: ");
						// get customer id
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}
						System.out.print("\nFormat [Paperback Hardcover EBook]: ");
						// get book forma and store in options string
						if (scanner.hasNextLine()) {
							options = scanner.nextLine();
						}
						// Order product. Check for error mesage set in ECommerceSystem
						String success = amazon.orderProduct(productId, customerId, options);
						// if (success == null)

						// System.out.println(amazon.getErrorMessage());

						// Print order number string if order number is not null

						System.out.println("Order #" + success);

					} else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and
																		// color
					{
						String productId = "";
						String customerId = "";
						String options = "";

						System.out.print("Product Id: ");
						// get product id
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						System.out.print("\nCustomer Id: ");
						// get customer id
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}

						System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");

						// get shoe size and store in options

						if (scanner.hasNextLine()) {
							options = scanner.nextLine() + " ";
						}

						System.out.print("\nColor: \"Black\" \"Brown\": ");

						// get shoe color and append to options

						if (scanner.hasNextLine()) {
							options += scanner.nextLine();
						}

						// order shoes

						String success = amazon.orderProduct(productId, customerId, options);
						System.out.println("Order #" + success);
					}

					else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
					{
						String orderNumber = "";

						System.out.print("Order Number: ");
						// get order number from scanner
						if (scanner.hasNextLine())
							orderNumber = scanner.nextLine();
						// cancel order. Check for error
						amazon.cancelOrder(orderNumber);

					} else if (action.equalsIgnoreCase("PRINTBYPRICE")) // print products by price
					{
						amazon.printByPrice();
					} else if (action.equalsIgnoreCase("PRINTBYNAME")) // print products by name (alphabetic)
					{
						amazon.printByName();
					} else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
					{
						amazon.sortCustomersByName();
					}

					else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) {
						String author = "";
						System.out.print("Author Name: ");
						if (scanner.hasNextLine()) {
							author = scanner.nextLine();
						}
						amazon.printBooksByAuthor(author);

					}

					else if (action.equalsIgnoreCase("ADDTOCART")) { // add products to cart for a customer
						String productId = "";
						String customerId = "";
						String options = "";

						System.out.print("Product Id: ");
						// get product id
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						System.out.print("\nCustomer Id: ");
						// get customer id
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}
						System.out.print("\nOptions: ");
						System.out.println(amazon.Voptions(productId));

						if (scanner.hasNextLine()) {
							options = scanner.nextLine();
						}

						amazon.addToCart(productId, customerId, options);

						System.out.println("Successfully added to cart");

					}

					else if (action.equalsIgnoreCase("REMCARTITEM")) { // remove product from a customer cart
						String productId = "";
						String customerId = "";

						System.out.print("Product Id: ");
						// get product id
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						System.out.print("\nCustomer Id: ");
						// get customer id
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}

						amazon.removeFromCart(customerId, productId);
						System.out.println("Successfully removed from cart");
					}

					else if (action.equalsIgnoreCase("PRINTCART")) { // prints the cart for a customer
						String customerId = "";

						System.out.print("Customer Id: ");
						// get customer id
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}

						amazon.printCart(customerId);
					}

					else if (action.equalsIgnoreCase("ORDERITEMS")) { // orders the items in the cart
						String customerId = "";

						System.out.print("Customer Id: ");
						// get customer id
						if (scanner.hasNextLine()) {
							customerId = scanner.nextLine();
						}

						amazon.orderItems(customerId);

						System.out.println("Items successfully ordered");

					}

					else if (action.equalsIgnoreCase("RATE")) { // rates a product
						String productId = "";

						System.out.print("Product Id: ");
						// get product id
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						int rating = 0;
						System.out.println("\nRating of the product [1 to 5]: ");
						// get rating
						if (scanner.hasNextLine()) {
							rating = scanner.nextInt();
						}

						amazon.addUserRating(productId, rating);
					}

					else if (action.equalsIgnoreCase("PRINTRATING")) { // prints the rating of a product
						String productId = "";

						System.out.print("Product Id: ");
						// get product id
						if (scanner.hasNextLine()) {
							productId = scanner.nextLine();
						}

						amazon.printRating(productId);
					}

					else if (action.equalsIgnoreCase("STATS")) { // prints the number of times a product is ordered
						amazon.productOrderStats();
					}

					System.out.print("\n>");

				}

			} catch (unknownCust message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}

			catch (unknownProd message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}

			catch (invalidOption message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}

			catch (prodOutOfStock message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}

			catch (invalidCustName message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}

			catch (invalidAddress message) {
				System.out.println(" Try again!");
				System.out.println(message.getMessage());
			}

			catch (invalidOrder message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}

			catch (invalidAuthorName message) {
				System.out.println("Try again!");
				System.out.println(message.getMessage());
			}
	}
}
