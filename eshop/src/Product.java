/**
 * @author sagie tanami.
 * @date 27/07/2020
 */
public class Product {
	/**
	 * instance variables
	 */
	private static int currentId = 1000;
	private String name;
	private final int productId;
	private double price;
	private double originalPrice;
	private float discount;// i used float because the options for discount are between 0.0 and 100.0;

	/**
	 * Constructor: This constructor builds an object(Product) according to the
	 * values received from the user.
	 * 
	 * @param name     - represents the name of the item.
	 * @param price    - represents the price of the item
	 * @param discount - represents the discount in the item's price.
	 */
	public Product(String name, double price, float discount) {
		productId = currentId++;
		this.name = name;
		this.discount = discount;
		originalPrice = price;
		this.price = ((100 - discount) / 100) * price;
	}

	/**
	 * Constructor: This constructor builds an object(Product) using only 2
	 * parameters, according to the values received from the user. And send it to
	 * the constructor that receive 3 parameters.
	 * 
	 * @param name  - represents the name of the item.
	 * @param price - represents the price of the item
	 */
	public Product(String name, double price) {
		this(name, price, 0);
	}

	/**
	 * Getter:
	 * 
	 * @return return the id of the item.
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Getter:
	 * 
	 * @return the name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter:
	 * 
	 * @return the price of the item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter:
	 * 
	 * @return the discount on the item.
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * This function changes the price of the item.
	 * 
	 * @param newPrice - represents the new price of the item.
	 */
	public void changePrice(double newPrice) {// equals to set function
		originalPrice = newPrice;
		price = ((100 - discount) / 100) * newPrice;
	}

	/**
	 * This function changes the discount on the item.
	 * 
	 * @param newDiscount - represents the new price of the item.
	 */
	public void changeDiscount(float newDiscount) {// equals to set function
		discount = newDiscount;
		price = ((100 - discount) / 100) * originalPrice;// calculates the new discount from the original price.
	}

	/**
	 * This function calculate the amount of money the user saved in a single
	 * purchase.
	 * 
	 * @return the amount that user saved.
	 */
	public double savings() {
		if (discount > 0) {
			return originalPrice - price;
		}
		return 0;
	}

	/**
	 * This function shows all the details of the product.
	 */
	public void show() {
		System.out.println("product catalog number: " + productId);
		System.out.println("product name: " + name);
		System.out.println("price before discount:" + originalPrice);
		System.out.println("price after discount:" + price);
		System.out.println("product discount:" + discount);
		System.out.println("----------------------------------------------------");
	}

}
