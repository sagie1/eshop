
/**
 * @author sagie tanami
 *  @date 27/07/2020
 */

import java.util.Random;

public class Shop {
	/**
	 * instance variables
	 */
	private Random rand = new Random();
	private String storeName;
	private Product[] items = new Product[1000];
	private int[] storage = new int[items.length];
	private int[] originalStorage = new int[items.length];// represent the original amount of the products.
	private Customer[] customer = new Customer[100];
	private Cart[] cart = new Cart[100];
	private int itemCounter;// logic counter of products.
	private int signedCounter = 0;// logic counter of customers.
	private int cartCounter = 0;// logic counter of carts.
	private double salesReport;// sums the money that the users paid.
	private int managerCounter;// counts the number of users with manager permissions.
	private String[] item = { "Paper Towel Holder", "Drawer Organizers", "Drying Mat", "Kitchen Towels", "Mixing Bowls",
			"Placemats", "Cups", "Plates", "Stools", "Kitchen table", "Chairs", "Couch", "Area rug", "Coffee table",
			"Side tables", "TV", "Lamps", "Rug", "Mugs", "Frying pan", "Saucepan", "Roasting pans", "Bakeware",
			"Cast iron skillet", "Chef’s knife", "Paring knife", "Serrated bread knife", "Honing Steel",
			"Cutting board", "Coffee maker", "Toaster oven", "Blender", "Food processor", "Microwave", "Slow cooker",
			"Serving platters", "Water pitchers", "Spoons", "Forks", "knives", "Trash can and bags", "Napkins",
			"Tea kettle", "Vegetable peeler", "Oven mitts", "Spatula", "Measuring cups", "Can opener",
			"Storage containers", "Hammer", "Adjustable wrench", "Screwdrivers", "Pliers", "Utility knife",
			"Allen wrenches", "Tape measure", "Chisels", "Paintbrushes", "Sandpaper", "Cordless drill", "Bed",
			"Mattress", "Dressers", "Bedside tables", "Mirror", "Vacuum cleaner", "Rubber gloves", "Sponges",
			"Baking soda", "Broom", "Dustpans", "Wipes", "Microfiber cloths", "Toilet scrubber", "Bandages", "Gauze",
			"Cotton balls", "Petroleum jelly", "Hand sanitizer", "Antibiotic ointment", "Thermometer",
			"Cold/heat packs", "Aloe Vera gel", "Anti-diarrhea medication", "Laxative", "Antacids", "Antihistamine",
			"OTC pain relievers", "Cough and cold medications", "Shampoo", "Hand soap", "Conditioner", "Plunger",
			"Shower curtain", "Shower liner", "Tissues", "Blinds", "Hangers", "Hooks", "Storage bins", "Organizers",
			"Shoe Rack", "Blankets", "Sheets", "Towels", "Pillows", "Pillow covers", "Clothing iron", "Ironing board ",
			"Lawnmower", "Trimmer", "Leaf blower", "Snowblower", "Ax", "Seed spreader", "Shovel", "Snow shovel",
			"Ice melt", "Gloves", "Pruner", "Wheelbarrow", "Leaf rake", "Leaf bags", "Garden hose", "Gardening shears",
			"Edger", "Push broom", "Ladder", "Extension cord", "Flashlight", "Bottled water", "Paper plates",
			"Litterbox", "Litter(for cats)", "Leash and collar", "New tag", "Bedding", "Waste bags", "Carrie" };

	/**
	 * Constructor: This constructor is a copy constructor.
	 * 
	 * @param other - represent another shop.
	 */
	public Shop(Shop other) {
		this(other.storeName, other.items, other.storage, other.customer, other.cart);
	}

	/**
	 * Constructor: This constructor builds an object(Shop) with only the name
	 * received from the user.
	 * 
	 * @param name - represent the name of the shop.
	 */
	public Shop(String name) {
		storeName = name;
	}

	/**
	 * Constructor: This constructor builds an object(Shop) according to the values
	 * received from the user.
	 * 
	 * @param name     - represent the name of the shop.
	 * @param items    - represent an array of objects from type Product.
	 * @param storage  - represent an array of numbers which is the current quantity
	 *                 of the items in the store.
	 * @param customer - represent an array of customers.
	 * @param cart     - represent an array of carts.
	 */
	public Shop(String name, Product[] items, int[] storage, Customer[] customer, Cart[] cart) {
		storeName = name;
		setCart(cart);
		setCustomer(customer);
		setItems(items);
		setStorage(storage);
	}

	/**
	 * This function copies an array that represent storage to the array in this
	 * shop.
	 * 
	 * @param storage - represent an array of storage.
	 */
	private void setStorage(int[] storage) {
		if (storage != null) {
			int length = items.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					this.storage[i] = storage[i];
					originalStorage[i] = storage[i];
				}
			}
		}
	}

	/**
	 * This function copies an array that represent objects that are type Product to
	 * the array in this shop.
	 * 
	 * @param items - represent an array of objects that are type Product.
	 */
	public void setItems(Product[] items) {
		if (items != null) {
			int length = items.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					if (items[i] != null) {
						this.items[i] = items[i];
						itemCounter++;
					}
				}
			}
		}
	}

	/**
	 * This function copies an array that represent objects that are type Customer
	 * to the array in this shop.
	 * 
	 * @param items - represent an array of objects that are type Customer.
	 */
	public void setCustomer(Customer[] customer) {
		if (customer != null) {
			int length = customer.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					if (customer[i] != null) {
						this.customer[i] = customer[i];
						signedCounter++;
					}
				}
			}
		}
	}

	/**
	 * This function copies an array that represent objects that are type Cart to
	 * the array in this shop.
	 * 
	 * @param cart - represent an array of objects that are type Cart.
	 */
	public void setCart(Cart[] cart) {
		if (cart != null) {
			int length = cart.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					if (cart[i] != null) {
						this.cart[cartCounter++] = cart[i];
					}
				}
			}
		}
	}

	/**
	 * Getter:
	 * 
	 * @return the amount of users registered as managers.
	 */
	public int getManagerCounter() {
		return managerCounter;
	}

	/**
	 * Getter:
	 * 
	 * @return the amount of users that are registered to the store.
	 */
	public int getSignedCounter() {
		return signedCounter;
	}

	/**
	 * Getter:
	 * 
	 * @return the income of the store from the users purchases.
	 */
	public double getSalesReport() {
		return salesReport;
	}

	/**
	 * Getter:
	 * 
	 * @return the array of the products.
	 */
	public Product[] getItems() {
		return items;
	}

	/**
	 * Getter:
	 * 
	 * @return the array of the carts.
	 */
	public Cart[] getICarts() {
		return cart;
	}

	/**
	 * Getter:
	 * 
	 * @return the array of the storage.
	 */
	public int[] getStorage() {
		return storage;
	}

	/**
	 * Getter:
	 * 
	 * @return the array of the customers.
	 */
	public Customer[] getCustomer() {
		return customer;
	}

	/**
	 * This function locate a customer by its name and password.
	 * 
	 * @param name     - represent the name of the user.
	 * @param password - represent the password of the user.
	 * @return the object of the customer, if there is no match with any of the
	 *         objects in the array then return null.
	 */
	public Customer getCustomerByNameAndPassword(String name, String password) {
		if (name != null && password != null) {
			for (int i = 0; i < signedCounter; i++) {
				if (customer[i].getUserName().equals(name) && customer[i].getPassword().equals(password)) {
					return customer[i];
				}
			}
		}
		return null;
	}

	/**
	 * This function locate the index in the Customer array of a customer by its
	 * name and password.
	 * 
	 * @param name     - represent the name of the user.
	 * @param password - represent the password of the user.
	 * @return the index of the user in the customer array, if there is no match
	 *         with any of the objects in the array then return -1.
	 */
	// returns the index of customer
	public int getCustomerIndex(String name, String password) {
		if (name != null && password != null && signedCounter > 0) {
			for (int i = 0; i < signedCounter; i++) {
				if (customer[i].getUserName().equals(name) && customer[i].getPassword().equals(password)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * This function locate the first manager account in the array.
	 * 
	 * @return manager account, if there is no object in the array that meets the
	 *         demands then return null.
	 */
	public Customer getManagerAccount() {
		for (int i = 0; i < signedCounter; i++) {
			if (customer[i].getUserType() == 1) {
				return customer[i];
			}
		}
		return null;
	}

	/**
	 * This function is a manager function only. This function locates a user by
	 * only its name.
	 * 
	 * @param name - represent the name of the user.
	 * @param type - represent the type of the user:manager or regular user.
	 * @return the user account according to the type received as parameter, if
	 *         there is no match with any of the objects in the array then return
	 *         null.
	 */
	// manager option only
	public Customer getCustomerByName(String name, short type) {
		if (name != null) {
			for (int i = 0; i < signedCounter; i++) {
				if (customer[i].getUserName().equals(name) && customer[i].getUserType() == type) {
					return customer[i];
				}
			}
		}
		return null;
	}

	/**
	 * This function locate a cart in the array.
	 * 
	 * @param cart - represent a cart to be located in the array.
	 * @return the index of the cart in the carts array, if there is no match with
	 *         any of the objects in the array then return -1.
	 */
	public int getCartIndex(Cart cart) {
		if (cart != null && cartCounter > 0) {
			for (int i = 0; i < cartCounter; i++) {
				if (this.cart[i].getCartId() == cart.getCartId()) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * This function locate a product in the array.
	 * 
	 * @param product - represent a product to be located in the array.
	 * @return the index of the product in the products array, if there is no match
	 *         with any of the objects in the array then return -1.
	 */
	public int getItemIndex(Product product) {
		if (product != null && itemCounter > 0) {
			for (int i = 0; i < itemCounter; i++) {
				if (items[i].getProductId() == product.getProductId()) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * This function locate a product in the array using its name.
	 * 
	 * @param name - represent a product name to be located in the array.
	 * @return the index of the product in the products array, if there is no match
	 *         with any of the objects in the array then return -1.
	 */
	public int getItemIndexByName(String name) {
		if (name != null && itemCounter > 0) {
			for (int i = 0; i < itemCounter; i++) {
				if (items[i].getName().equalsIgnoreCase(name)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Setter: This function changes the name of the store.
	 * 
	 * @param name - represent the name of the store.
	 */
	public void setStoreName(String name) {
		storeName = name;
	}

	/**
	 * This function fill the array of the products and set the quantity of each
	 * product.
	 */
	public void fillItems() {
		int length = item.length;// this variable will take the length of the array with the names.
		for (int i = 0; i < length; i++) {
			// nextDouble randomize a number between 0.0-0.99 and then it multiplies by a
			// random int between 100 and 10100.
			double price = (rand.nextDouble()) * (rand.nextInt(10000) + 100);
			items[i] = new Product(item[i], price);
			storage[i] = rand.nextInt(200) + 50;
			originalStorage[i] = storage[i];
			itemCounter++;
		}
	}

	/**
	 * This function adds a user to the system.
	 * 
	 * @param name     - represent the name of the use.
	 * @param password - represent the password of the user.
	 * @param phone    - represent the phone number of the user.
	 * @param address  - represent the address of the user.
	 * @param type     - represent the type of the user.
	 */
	public void addCustomer(String name, String password, String phone, String address, short type) {
		// signedCounter>0 is needed because the first customer can't have identical
		// name to another customer because there are no other customers.
		if (signedCounter > 0 && signedCounter < customer.length) {
			if (isUniqueUserName(name)) {
				if (type == (short) 1) {
					managerCounter++;// if the new account is a manager account then increase the manager counter.
				}
				customer[signedCounter] = new Customer(name, password, phone, address, type);
				customer[signedCounter].takeCart(customer[signedCounter]);// when sigining up the user
																			// automatically receive cart
				cart[cartCounter++] = customer[signedCounter].getCart();
				signedCounter++;
			}
		} else {
			// the first account in the store is the manager account
			customer[signedCounter] = new Customer(name, password, phone, address, type);
			customer[signedCounter].takeCart(customer[signedCounter]);// when signing up the user automatically
																		// receive cart
			cart[cartCounter++] = customer[signedCounter].getCart();
			signedCounter++;
			managerCounter++;// the first account is a manager account.
		}
	}

	/**
	 * This function validate that the name of the user hasn't appeared already in
	 * the array.
	 * 
	 * @param name - represent the name of the user.
	 * @return true if there is no match with another object in the array, false if
	 *         there is a match.
	 */
	public boolean isUniqueUserName(String name) {
		if (name != null ) {
			if (name.toLowerCase().equals("null")) {
				return false;
			}
			for (int i = 0; i < signedCounter; i++) {
				if (customer[i].getUserName().equals(name)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This function remove a user from the system.
	 * 
	 * @param customer - represent the user.
	 * @return true if the user was removed. false if: 1 - the object received as a
	 *         parameter is empty. 2 - there are 2 or more users (the first is the
	 *         manager, the second is the first user). 3 - there is no match between
	 *         the object received as a parameter and the objects in the array.
	 */
	public boolean removeCustomer(Customer customer) {
		if (customer != null) {
			if (signedCounter >= 2) {
				for (int i = 0; i < signedCounter; i++) {
					if (this.customer[i].getUserName().equals(customer.getUserName())) {
						if (customer.getCart() != null) {
							removeCart(customer);
						}

						// if the account that received as parameter is a manager account then
						// you can only delete the account if there is at least one more manager account
						if (managerCounter > 1 && customer.getUserType() == (short) 1) {

							// set the last user cart in the array to be in the current position and delete
							// it from
							// the end.
							cart[i] = cart[cartCounter - 1];
							cart[cartCounter - 1] = null;
							cartCounter--;

							// set the last user in the array to be in the current position and delete it
							// from
							// the end.
							this.customer[i] = this.customer[signedCounter - 1];
							this.customer[--signedCounter] = null;
							managerCounter--;
							return true;

						} else {
							// if the account that received as parameter is a regular user then you can
							// delete it.
							if (customer.getUserType() == (short) 2) {

								// set the last user cart in the array to be in the current position and delete
								// it from
								// the end.
								cart[i] = cart[cartCounter - 1];
								cart[cartCounter - 1] = null;
								cartCounter--;

								// set the last user in the array to be in the current position and delete it
								// from
								// the end.
								this.customer[i] = this.customer[signedCounter - 1];
								this.customer[--signedCounter] = null;
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * This function add a cart to the array.
	 * 
	 * @param customer - represent the user which takes the cart.
	 */
														
	  public boolean addCart(Customer customer) {
		  if(customer!=null && customer.getCart()==null) {
			  //if there is space in the array to add a new cart 
			  if(cartCounter<this.cart.length) {
				  customer.takeCart(customer); 
				  cartCounter++;
				  int customerIndex = getCustomerIndex(customer.getUserName(), customer.getPassword());
				  if( cart[customerIndex] == null) {
					  cart[customerIndex] = customer.getCart();
					  return true;
				  }				  
		  		} 
		  	  } 
		  return false;
		  }
	 
	 

	/**
	 * This function delete a cart from the array.
	 * 
	 * @param customer - represent the user that holds the cart.
	 * @return true if the cart has been deleted. false if the object customer
	 *         received as parameter is empty, or if the user has no cart.
	 */

	public boolean removeCart(Customer customer) {
		if (customer != null && customer.getCart() != null) {
			if (cartCounter >= 1) {
				int cartId = customer.getCart().getCartId();
				for (int i = 0; i < cartCounter; i++) {
					if (cartId == cart[i].getCartId()) {
						customer.releaseCart();
						cart[i] = null;
						cartCounter--;
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This function check the connection status of all the users in the array.
	 * 
	 * @return the number of the user that are connected.
	 */
	public int onlineCustomers() {
		int loggedIn = 0;
		if (signedCounter > 0) {
			for (int i = 0; i < signedCounter; i++) {
				if (customer[i].getConnect()) {
					loggedIn++;
				}
			}
		}
		return loggedIn;
	}

	/**
	 * This function add a product to the user cart.
	 * 
	 * @param customer - represent the user.
	 * @param product  - represent the item the user want to add.
	 * @param amount   - represent the quantity that the user want from that item.
	 * @return true if the item has been added to the user cart. false if: 1- the
	 *         object customer or product is empty, 2- the amount is lower then 1,
	 *         3- if the item is not in the array, 4- if the user is not in the
	 *         array
	 */
	public boolean addItem(Customer customer, Product product, int amount) {// customer option ONLY
		if (customer != null && customer.getCart() != null && product != null && amount > 0) {
			int customerIndex = getCustomerIndex(customer.getUserName(), customer.getPassword());// find the
			int itemIndex = getItemIndex(product);// in order to update the quantity of the item in the shop.
			if (customerIndex >= 0) {
				if (itemIndex >= 0 && storage[itemIndex] > 0) {
					// if the amount in the storage is larger than the amount to be purchased.
					if (amount <= storage[itemIndex]) {
						this.customer[customerIndex].addProductToCart(product, amount);
						storage[itemIndex] -= amount;
						return true;
					} else {
						this.customer[customerIndex].addProductToCart(product, storage[itemIndex]);
						storage[itemIndex] = 0;
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This function is manager option only.
	 * 
	 * This function allows the manager to increase or decrease the quantity of an
	 * existing item. If the item isn't in the array then create it.
	 * 
	 * @param product - represent the item that the manager want to change.
	 * @param amount  - the quantity that the manager want to add to the item.
	 * @return true if the quantity has increased or if the item has been added to
	 *         the array. false if the Product received as parameter is empty.
	 */
	// change quantity of an exiting item or add a new item
	public boolean addOrUpdateItem(Product product, int amount) {
		if (product != null) {
			if (itemCounter < items.length) {// if there is still empty space in the array to add new product
				int index = getItemIndex(product);
				if (index >= 0) {// if the item is already exist in the array then only the items amount will be
									// added.
					if (storage[index] + amount >= 0) {
						storage[index] += amount;
						originalStorage[index] += amount;
						return true;
					}
				} else {
					// if the item isn't in the array then insert it to the array.
					items[itemCounter] = product;
					storage[itemCounter] = amount;
					originalStorage[itemCounter++] = amount;// increase the itemCounter here because these three arrays
															// uses this counter as index pointer.
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function is manager option only.
	 * 
	 * This function allows the manager to delete an existing item.
	 * 
	 * @param product - the item that the manager wants to remove.
	 * @return true if the item was removed. false if: 1- the object received as
	 *         parameter is empty, 2- if the product array is empty, 3- if the item
	 *         is not in the array.
	 */
	public boolean removeItem(Product product) {
		if (product != null) {
			if (itemCounter > 0) {
				int itemIndex = getItemIndex(product);
				if (itemIndex >= 0) {// can only delete an exiting item.
					int newIndex = itemCounter - 1;
					items[itemIndex] = items[newIndex];
					storage[itemIndex] = storage[newIndex];
					originalStorage[itemIndex] = originalStorage[newIndex];
					items[newIndex] = null;
					storage[newIndex] = 0;
					originalStorage[itemCounter--] = 0;// decrease the itemCounter here because these three arrays uses
														// this counter as index pointer.
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function remove some / all of the amount of an item from the user's
	 * cart.
	 * 
	 * @param customer - represent the user.
	 * @param product  - represent the item the user wants to remove.
	 * @param quantity - represent the quantity that the user wants to remove.
	 * @return true if the amount of the item was removed. false if:
	 * 						 1- the user does'nt have a cart.
	 * 						 2- the objects received as parameter are empty.
	 *         				 3- the product array is empty.
	 *         				 4- the user is not in the array.
	 *         				 5- the item is not in the array.
	 */
	public boolean removeItem(Customer customer, Product product, int quantity) {// customer option ONLY
		if (customer != null && customer.getCart() != null && product != null) {
			if (itemCounter > 0) {
				int itemIndex = getItemIndex(product);
				int customerIndex = getCustomerIndex(customer.getUserName(), customer.getPassword());
				if (customerIndex >= 0 && itemIndex > 0) {

					int itemInCartIndex = this.customer[customerIndex].getCart().getItemIndex(product);
					int amount = this.customer[customerIndex].getCart().getAmountByIndex(itemInCartIndex);

					// if the quantity that the customer wants to decrease is equals or smaller than
					// what is actually in his cart then decrease by the quantity that the user
					// chose.
					if (quantity <= amount) {

						// when the customer removes an item from his cart, the quantity of this item in
						// the store increases according to the amount that the user returned.
						storage[itemIndex] += quantity;
					} else {
						// increase only by what is in the cart

						storage[itemIndex] += amount;
					}
					
					this.customer[customerIndex].removeProductFromCart(product, quantity);// remove the item from the
																						// customer cart.
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function removes all of the items from the user's cart.
	 * 
	 * @param customer - represent the user.
	 * @return true if the cart has been cleared. false if:
	 * 							 1- the object received is empty.
	 * 							 2- the user don't have a cart.
	 * 							 3- if the user is not in the array.
	 */
	public boolean clearCart(Customer customer) {
		if (customer != null && customer.getCart() != null && signedCounter > 0) {
			int customerIndex = getCustomerIndex(customer.getUserName(), customer.getPassword());
			if (customerIndex >= 0) {
				this.customer[customerIndex].getCart().clearCart();
				return true;
			}
		}
		return false;
	}

	/**
	 * This function changes the status of the user from disconnect to connect.
	 * 
	 * @param name     - represent the name of the user.
	 * @param password - represent the password of the user.
	 * @return true if the connection status has changed. false if: 1- name is
	 *         empty, 2- password is empty, 3- if the user is not in the array.
	 */
	public boolean userLogIn(String name, String password) {
		if (name != null && password != null) {
			if (signedCounter > 0) {
				int index = getCustomerIndex(name, password);
				if (index >= 0) {
					customer[index].login(name, password);
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * This function changes the password of the user.
	 * 
	 * @param shopper     - represent a user.
	 * @param newPassword - represent the new password.
	 * @return true if the password has changed. false if: 1- the object is empty,
	 *         2- the new password is empty.
	 */
	public boolean cahngePassword(Customer shopper, String newPassword) {
		if (shopper != null && newPassword != null) {
			for (int i = 0; i < signedCounter; i++) {
				if (customer[i].cahngePassword(shopper, newPassword)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function changes the address of the user.
	 * 
	 * @param shopper    - represent a user.
	 * @param newAddress - represent the new address.
	 * @return true if the address has changed. false if: 1- the object is empty, 2-
	 *         the new address is empty.
	 */
	public boolean cahngeAddress(Customer shopper, String newAddress) {
		for (int i = 0; i < signedCounter; i++) {
			if (customer[i].cahngeAddress(shopper, newAddress)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function changes the phone number of the user.
	 * 
	 * @param shopper  - represent a user.
	 * @param newPhone - represent the new phone number.
	 * @return true if the phone number has changed. false if: 1- the object is
	 *         empty, 2- the new phone number is empty.
	 */
	public boolean changePhone(Customer shopper, String newPhone) {
		for (int i = 0; i < signedCounter; i++) {
			if (customer[i].changePhone(shopper, newPhone)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function mimic the pay function on web sites. if the user chose delivery
	 * the the function validate that this customer has an address and a phone
	 * number.
	 * 
	 * @param customer - represent the user.
	 * @return true if the payment process passed successfully. false if: 1- the
	 *         object received as parameter is empty, 2- if the user don't have a
	 *         cart, 4- if the user don't exist in the user's array.
	 */
	
	
	public boolean pay(Customer customer) {
		Product item = null;
		double amount = 0.0;
		if (customer != null && customer.getCart() != null) {
			int index = getCustomerIndex(customer.getUserName(), customer.getPassword());
			if (index >= 0) {// check if the user exist in the store's user array
				income(this.customer[index].getCart().calculateTotalPrice());
				int length = getCustomer()[index].getCart().getAmountOfItems();// set the length for the loop as the
																				// amount of items in the customer cart.
//				לבדוק שהעידכון של המוצרים בחנות השתנה רק פעם אחת
				// update the store storage.
//				for (int i = 0; i < length; i++) {
//					item = this.customer[index].getCart().getProducts()[i];// current product
//					amount = this.customer[index].getCart().getProductCounter()[index];// current amount
//				}
				this.customer[index].pay();
				return true;
			}
		}
		return false;
	}

	/**
	 * This function updates the store income.
	 * 
	 * @param money - represent money paid in a single buy.
	 */
	public void income(double money) {
		salesReport += money;
	}

	/**
	 * This function calculate and print to the screen all the item and for each
	 * item it print how much units were sold from that item and the total income
	 * for that item.
	 */
	public void itemsSold() {
		if (items != null && itemCounter > 0) {
			System.out.println("produc name            sold      profit");
			for (int i = 0; i < itemCounter; i++) {
				if (items[i] != null) {
					int difference = originalStorage[i] - storage[i];
					double price = difference * items[i].getPrice();
					System.out.println(items[i].getName() + "   " + difference + "   " + price);
					System.out.println();
				}
			}
		}
	}

	/**
	 * This function prints all of the item in the shop to the screen.
	 */
	public void showItems() {
		int length = itemCounter;
		for (int i = 0; i < length; i++) {
			if (i > 3 && i % 3 == 0) {// control the linebreaks from the second row
				System.out.println(items[i].getName());
			} else {
				if (i > 0 && i < 3 && i % 2 == 0) {// control the linebreak for the first row
					System.out.println(items[i].getName());
				} else {
					System.out.print(items[i].getName() + ",                  ");
				}
			}
		}
		System.out.println();// space between this function and the menu.
	}
}
