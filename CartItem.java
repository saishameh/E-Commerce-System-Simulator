// Sayyada Aisha Mehvish
// 501106795

public class CartItem {
    
    private String productOptions;
	private Product product;
	// private String productID;
    // private String productName;
    // private int quantity;
    // private double price;

    public CartItem(Product product,String productOptions)
    {
        this.product = product;
        this.productOptions = productOptions;
    }

	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}

	public String getProductOptions()
	{
		return productOptions;
	}

	public void setProductOptions(String productOptions)
	{
		this.productOptions = productOptions;
	}

	public void print()
	{
		System.out.printf("\nProduct Id: %3s Product Name: %12s Price: %7.1f Options: %8s" , product.getId(), product.getName(), product.getPrice(),
		productOptions);
	}

}
