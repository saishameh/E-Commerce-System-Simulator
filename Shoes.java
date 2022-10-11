// Sayyada Aisha Mehvish
// 501106795

public class Shoes extends Product
{
    private int stock;
    int Brown6shoeStock;
    int Brown7shoeStock;
    int Brown8shoeStock;
    int Brown9shoeStock;
    int Brown10shoeStock;

    int Black6shoeStock;
    int Black7shoeStock;
    int Black8shoeStock;
    int Black9shoeStock;
    int Black10shoeStock;

    private int shoeSize;
    private String shoeColor;

  public Shoes(String name, String id, double price, int shoeSize, String shoeColor, int stock) 
   {
      super(name, id, price, 100000, Product.Category.SHOES);
      this.shoeSize = shoeSize;
      this.shoeColor = shoeColor;
      this.stock = stock;
   }

  public boolean validOptions(String productOptions)
  {
  	// check productOptions for Shoe size and color
  	// if it is one of these, return true, else return false
    super.validOptions(productOptions);
    if (productOptions.equals("6 Black") || productOptions.equals("7 Black") || 
    productOptions.equals("8 Black") || productOptions.equals("9 Black") || 
    productOptions.equals("10 Black") || productOptions.equals("6 Brown") || 
    productOptions.equals("7 Brown") || productOptions.equals("8 Brown") || 
    productOptions.equals("9 Brown") || productOptions.equals("10 Brown"))
    {
  	return true;
    }
    else{ return false;}
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "6 Black" etc

    switch(productOptions){
      case "6 Black" : Black6shoeStock = stock; break;
      case "7 Black" : Black7shoeStock = stock; break;
      case "8 Black" : Black8shoeStock = stock; break;
      case "9 Black" : Black9shoeStock = stock; break;
      case "10 Black" : Black10shoeStock = stock; break;

      case "6 Brown" : Brown6shoeStock = stock; break;
      case "7 Brown" : Brown7shoeStock = stock; break;
      case "8 Brown" : Brown8shoeStock = stock; break;
      case "9 Brown" : Brown9shoeStock = stock; break;
      case "10 Brown" : Brown10shoeStock = stock; break;
    }

    super.getStockCount(productOptions);
    int result = 0;
    if (productOptions.equals("6 Black"))
    {
      result = Black6shoeStock;
    }
    else if (productOptions.equals("7 Black"))
    {
      result = Black7shoeStock;
    } 
    else if (productOptions.equals("8 Black"))
    {
      result = Black8shoeStock;
    }
    else if (productOptions.equals("9 Black"))
    {
      result = Black9shoeStock;
    }
    else if (productOptions.equals("10 Black"))
    {
      result = Black10shoeStock;
    }
    else if (productOptions.equals("6 Brown"))
    {
      result = Brown6shoeStock;
    }
    else if (productOptions.equals("7 Brown"))
    {
      result = Brown7shoeStock;
    }
    else if (productOptions.equals("8 Brown"))
    {
      result = Brown8shoeStock;
    }
    else if (productOptions.equals("9 Brown"))
    {
      result = Brown9shoeStock;
    }
    else if (productOptions.equals("10 Brown"))
    {
      result = Brown10shoeStock;
    }   
    return result;
  }

  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "6 Black" etc

    switch(productOptions){
      case "6 Black" : Black6shoeStock = stock; break;
      case "7 Black" : Black7shoeStock = stock; break;
      case "8 Black" : Black8shoeStock = stock; break;
      case "9 Black" : Black9shoeStock = stock; break;
      case "10 Black" : Black10shoeStock = stock; break;

      case "6 Brown" : Brown6shoeStock = stock; break;
      case "7 Brown" : Brown7shoeStock = stock; break;
      case "8 Brown" : Brown8shoeStock = stock; break;
      case "9 Brown" : Brown9shoeStock = stock; break;
      case "10 Brown" : Brown10shoeStock = stock; break;
    }

    super.setStockCount(stockCount, productOptions);
    if (productOptions.equals("6 Black"))
    {
      stockCount = Black6shoeStock;
    }
    else if (productOptions.equals("7 Black"))
    {
      stockCount = Black7shoeStock;
    } 
    else if (productOptions.equals("8 Black"))
    {
      stockCount = Black8shoeStock;
    }
    else if (productOptions.equals("9 Black"))
    {
      stockCount = Black9shoeStock;
    }
    else if (productOptions.equals("10 Black"))
    {
      stockCount = Black10shoeStock;
    }
    else if (productOptions.equals("6 Brown"))
    {
      stockCount = Brown6shoeStock;
    }
    else if (productOptions.equals("7 Brown"))
    {
      stockCount = Brown7shoeStock;
    }
    else if (productOptions.equals("8 Brown"))
    {
      stockCount = Brown8shoeStock;
    }
    else if (productOptions.equals("9 Brown"))
    {
      stockCount = Brown9shoeStock;
    }
    else if (productOptions.equals("10 Brown"))
    {
      stockCount = Brown10shoeStock;
    }   
	}
  
  /*
   * When a shoe is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "6 Black" etc

    switch(productOptions){
      case "6 Black" : Black6shoeStock = stock; break;
      case "7 Black" : Black7shoeStock = stock; break;
      case "8 Black" : Black8shoeStock = stock; break;
      case "9 Black" : Black9shoeStock = stock; break;
      case "10 Black" : Black10shoeStock = stock; break;

      case "6 Brown" : Brown6shoeStock = stock; break;
      case "7 Brown" : Brown7shoeStock = stock; break;
      case "8 Brown" : Brown8shoeStock = stock; break;
      case "9 Brown" : Brown9shoeStock = stock; break;
      case "10 Brown" : Brown10shoeStock = stock; break;
    }

    super.reduceStockCount(productOptions);
    if (productOptions.equals("6 Black"))
    {
      Black6shoeStock-- ;
    }
    else if (productOptions.equals("7 Black"))
    {
      Black7shoeStock-- ;
    } 
    else if (productOptions.equals("8 Black"))
    {
      Black8shoeStock-- ;
    }
    else if (productOptions.equals("9 Black"))
    {
      Black9shoeStock-- ;
    }
    else if (productOptions.equals("10 Black"))
    {
      Black10shoeStock-- ;
    }
    else if (productOptions.equals("6 Brown"))
    {
      Brown6shoeStock-- ;
    }
    else if (productOptions.equals("7 Brown"))
    {
      Brown7shoeStock-- ;
    }
    else if (productOptions.equals("8 Brown"))
    {
      Brown8shoeStock-- ;
    }
    else if (productOptions.equals("9 Brown"))
    {
      Brown9shoeStock-- ;
    }
    else if (productOptions.equals("10 Brown"))
    {
      Brown10shoeStock-- ;
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
    System.out.printf("Shoe size: %-29s Color: %-20s", shoeSize, shoeColor);
  }

   
}