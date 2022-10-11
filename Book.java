// Sayyada Aisha Mehvish
// 501106795

/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product 
{
  private String author;
  private String title;
  private int year;

  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
  
  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS 
     super(name, id, price, 100000, Product.Category.BOOKS);
     this.title = title;
     this.author = author;
     this.paperbackStock = paperbackStock;
     this.hardcoverStock = hardcoverStock;
     this.year = year;
  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
    super.validOptions(productOptions);
    if (productOptions.equals("Paperback") || productOptions.equals("Hardcover") || productOptions.equals("EBook"))
    {
  	return true;
    }
    else{ return false;}
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
    super.getStockCount(productOptions);
    int x = super.getStockCount(productOptions);
    int result = 0;
    if (productOptions.equals("Paperback"))
    {
      result = paperbackStock;
    }
    else if (productOptions.equals("Hardcover"))
    {
      result = hardcoverStock;
    } 
    else if (productOptions.equals("EBook"))
    {
      result = x;
    }
    return result;
  }

  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
     super.setStockCount(stockCount, productOptions);
    super.getStockCount(productOptions);
    //  int result = 0;
     if (productOptions.equals("Paperback"))
     {
       stockCount = paperbackStock;
     }
     else if (productOptions.equals("Hardcover"))
     {
       stockCount = hardcoverStock;
     } 
     else if (productOptions.equals("EBook"))
     {
       super.getStockCount(productOptions);
     } 
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
     super.reduceStockCount(productOptions);
     if (productOptions.equals("Paperback"))
     {
       paperbackStock--;
     }
     else if (productOptions.equals("Hardcover"))
     {
       hardcoverStock--;
     } 
     else if (productOptions.equals("EBook"))
     {
       int x = super.getStockCount(productOptions);
       x --;
     } 

	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video

    super.print(); 
    System.out.printf("Book Title: %-29s Author: %-20s Year: %3s" , title, author, year);
  }

  public void setAuthorName(String author)
	{
		this.author = author;;
	}

  public String getAuthorName()
	{
		return author;
	}

  public void setyear(int year)
  {
    this.year = year;
  }

  public int getyear() 
  {
    return year;
  }
    
  }