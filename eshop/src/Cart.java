/**
 * @author sagie tanami
 * @date 27/07/2020
 */


public class Cart {
	/**
	 * instance variables
	 */
	private static int currentId = 1000;
	private final int cartid = currentId++;
	private Product[] products = new Product[100];
	private int[] productCounter = new int[100];
	private int currentAmountOfItems = 0;
	private Customer shopper;

	/**
	 * Constructor: This constructor builds an object(Cart) only when a valid
	 * Customer object is sent to it.
	 * 
	 * @param shopper - represent a customer in the store.
	 */
	public Cart(Customer shopper) {
		if (shopper != null) {
			this.shopper = shopper;
		}
	}

	/**
	 * Getter:
	 * 
	 * @return an array of objects from Product type.
	 */
	public Product[] getProducts() {
		return products;
	}

	/**
	 * Getter:
	 * 
	 * @return an array of real numbers that represents the quantity of the
	 *         products.
	 */
	public int[] getProductCounter() {
		return productCounter;
	}

	/**
	 * Getter:
	 * 
	 * @return a number which represents the amount of item in the cart.
	 */
	public int getAmountOfItems() {
		return currentAmountOfItems;
	}

	/**
	 * Getter:
	 * 
	 * @return the id of the cart.
	 */
	public int getCartId() {
		return cartid;
	}

	/**
	 * Getter:
	 * 
	 * @return the customer that owns this cart.
	 */
	public Customer getCustomer() {
		return shopper;
	}

	/**
	 * Getter:
	 * 
	 * @return the index of an item.
	 */
	public int getItemIndex(Product product) {
		if (product != null) {
			if (currentAmountOfItems > 0) {// if there is at least one product
				for (int i = 0; i < currentAmountOfItems; i++) {
					if (products[i] != null && products[i].getProductId() == product.getProductId()) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Getter:
	 * 
	 * @return the quantity of an item by its index.
	 */
	public int getAmountByIndex(int index) {
		if (index >= 0) {
			return productCounter[index];
		}
		return -1;
	}

	/**
	 * This function add an object(Product) to the array of products and the
	 * quantity of that product.
	 * 
	 * @param product - represent an item in the store.
	 * @param amount  - represent the quantity the user want from this item.
	 */
	public void addToCart(Product product, int amount) {
		boolean exist = false;
		if (shopper.getConnect()) {
				// first: check if the item is already in the cart
				for (int i = 0; i < currentAmountOfItems; i++) {
					// check if the product is already in the cart
					if (products[i] != null && products[i].getProductId() == product.getProductId()) {
						// increase the quantity of the product according to the amount that received as
						// a parameter.
						productCounter[i] += amount;
						exist = true;
						break;
					}
				}
				
				// if the product is NOT in the cart
				if (!exist) {
					
					//check that there is still free space in the cart
					if (currentAmountOfItems < products.length) {
						products[currentAmountOfItems] = product;
						productCounter[currentAmountOfItems++] = amount;
				}
			}
		}
	}

	/**
	 * This function remove item from the cart.
	 * 
	 * If the amount received as parameter is smaller than the amount in the cart then
	 * decrease the quantity in the cart by the amount received as parameter.
	 * 
	 * Else remove the item completely from the cart
	 * 
	 * @param product - represent the item that the user want to remove.
	 * @param amount  - represent the quantity that the user wants to remove.
	 */
	public void removeProductFromCart(Product product, int amount) {
		if (shopper.getConnect()) {
			if (currentAmountOfItems > 0) {// check that the array has at least 1 value.
				int index = getItemIndex(product);
				if (index >= 0) {// can only remove items that are in the cart
					if (amount < productCounter[index]) {
						productCounter[index] -= amount;
					} else {
						
						// change the amount of the removed item to the last product in the array.
						
						productCounter[index] = productCounter[currentAmountOfItems - 1];
						
						// change the reference from the removed item to the last product in the array.
						
						products[index] = products[currentAmountOfItems - 1];
						currentAmountOfItems--;// decrease the amount of item currently in the cart
						products[currentAmountOfItems] = null;
						productCounter[currentAmountOfItems] = 0;
					}
				}
			}
		}
	}
	
	/**
	 * This function remove the connection to the user.
	 */
	
	public void release() {
		shopper = null;
	}


	/**
	 * This function remove all the items from the cart.
	 */
	public void clearCart() {
		if (shopper.getConnect()) {
			int length = currentAmountOfItems;
			for (int i = 0; i < length; i++) {
				products[i] = null;
				productCounter[i] = 0;
			}
			currentAmountOfItems = 0;
		}
	}

	/**
	 * This function calculate the total price of the entire items in the cart.
	 * 
	 * @return the price that the user needs to pay.
	 */
	public double calculateTotalPrice() {
		double price = 0.0;
		if (shopper.getConnect()) {
			if (currentAmountOfItems > 0) {
				for (int i = 0; i < currentAmountOfItems; i++) {
					price += (products[i].getPrice() * productCounter[i]);
				}
			}
		}
		return price;
	}

	/**
	 * This function calculate the price of an item according to the price of one
	 * unit from this product times the quantity that in the user's cart.
	 * 
	 * @param product - represent the item that user want to know its price.
	 * @return the cost of the item that has been received as a parameter.
	 */
	public double calculateItemPrice(Product product) {
		double price = 0.0;
		if (shopper.getConnect()) {
			int index = getItemIndex(product);
			if (index >= 0) {
				price += (products[index].getPrice() * productCounter[index]);
			}
		}
		return price;
	}

	/**
	 * This function calculate the amount of money that user saved due to discounts
	 * on items in his cart.
	 * 
	 * @return the amount of money that user saved.
	 */
	public double moneySaved() {
		double saved = 0.0;
		if (shopper.getConnect()) {
			if (currentAmountOfItems > 0) {
				for (int i = 0; i < currentAmountOfItems; i++) {
					saved += (products[i].savings() * productCounter[i]);
				}
			}
		}
		return saved;
	}

	/**
	 * This function print out all the items in the cart with their full
	 * detail(name,quantity and price).
	 */
	public void show() {
		if (products != null && currentAmountOfItems > 0) {
			for (int i = 0; i < currentAmountOfItems; i++) {
				System.out.print(products[i].getName() + "            ");
				System.out.print(productCounter[i] + "   ");
				System.out.println(calculateItemPrice(products[i]));
				System.out.println("-------------------------------");
			}
		}
	}
}
