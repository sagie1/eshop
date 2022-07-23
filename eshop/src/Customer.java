/**
 * @author sagie tanami
 * @date 27/07/2020
 */
public class Customer {
	/**
	 * instance variables
	 */
	private String userName;
	private String password;
	private String shippingAddress;
	private String phone;
	private Cart shoppingCart;
	private boolean connect;
	private short userType;// represents if the user is a manager(1) or regular user(2)

	/**
	 * Constructor: This constructor builds an object(Customer) according to the
	 * values received from the user.
	 * 
	 * @param name     - represent the userName .
	 * @param password - represent the password of the user.
	 * @param phone    - represent the phone number of the user.
	 * @param address  - represent the address of the user.
	 * @param type     - represent if the user is a manager or a regular user.
	 */
	public Customer(String name, String password, String phone, String address, short type) {
		userName = name;
		this.password = password;
		this.phone = phone;
		shippingAddress = address;
		userType = type;
		connect = true;
	}

	/**
	 * Getter:
	 * 
	 * @return the name of the user.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Getter:
	 * 
	 * @return the password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter:
	 * 
	 * @return the address of the user.
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Getter:
	 * 
	 * @return the phone number of the user.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter:
	 * 
	 * @return the type of the user.
	 */
	public short getUserType() {
		return userType;
	}

	/**
	 * Getter:
	 * 
	 * @return the connection status of the user.
	 */
	public boolean getConnect() {
		return connect;
	}

	/**
	 * Getter:
	 * 
	 * @return the cart of the user.
	 */
	public Cart getCart() {
		return shoppingCart;
	}

	/**
	 * Setter: This function changes the current connection status according to the
	 * parameter received from the user.
	 * 
	 * @param connect - represent the new status of the user.
	 */
	public void setConnect(boolean connect) {
		this.connect = connect;
	}

	/**
	 * Setter: This function changes current password to the one received as
	 * parameter.
	 * 
	 * @param password - represent the new password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Setter: This function changes current address to the one received as
	 * parameter.
	 * 
	 * @param address - represent the new address of the user.
	 */
	public void setShippingAddress(String address) {
		this.shippingAddress = address;
	}

	/**
	 * Setter: This function changes current phone number to the one received as
	 * parameter.
	 * 
	 * @param phone - represent the new phone number of the user.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This function changes the connection status of the user to: Connect.
	 * 
	 * @param name     - represent the user name.
	 * @param password - represent the user password.
	 */
	public void login(String name, String password) {
		if (name != null && password != null) {
			if (name.equals(userName) && password.equals(this.password)) {
				setConnect(true);
			}
		}
	}

	/**
	 * This function changes the connection status of the user to: Disconnect.
	 */
	public void logout() {
		setConnect(false);
	}

	/**
	 * This function changes the user's password after identify that name and
	 * password are correct.
	 * 
	 * @param customer    - represent the object - customer.
	 * @param newPassword - represent the new password.
	 * @return true if the password has changed and false if the object is empty or
	 *         if the name or the password are empty.
	 */
	public boolean cahngePassword(Customer customer, String newPassword) {
		if (customer != null) {
			if (customer.getUserName() != null && userName.equals(customer.getUserName())
					&& customer.getPassword() != null && password.equals(customer.getPassword())
					&& newPassword != null) {
				password = newPassword;
				return true;
			}
		}
		return false;
	}

	/**
	 * This function changes the user's address after identify that name and
	 * password are correct.
	 * 
	 * @param customer    - represent the object - customer.
	 * @param newAddressd - represent the new address.
	 * @return true if the address has changed and false if the object is empty or
	 *         if the name or the password are empty.
	 */
	public boolean cahngeAddress(Customer customer, String newAddress) {
		if (customer != null) {
			if (customer.getUserName() != null && userName.equals(customer.getUserName())
					&& customer.getPassword() != null && password.equals(customer.getPassword())
					&& newAddress != null) {
				shippingAddress = newAddress;
				return true;
			}
		}
		return false;
	}

	/**
	 * This function changes the user's phone number after identify that name and
	 * password are correct.
	 * 
	 * @param customer - represent the object - customer.
	 * @param newPhone - represent the new phone number.
	 * @return true if the phone number has changed and false if the object is empty
	 *         or if the name or the password are empty.
	 */
	public boolean changePhone(Customer customer, String newPhone) {
		if (customer != null) {
			if (customer.getUserName() != null && userName.equals(customer.getUserName())
					&& customer.getPassword() != null && password.equals(customer.getPassword()) && newPhone != null) {
				phone = newPhone;
				return true;
			}
		}
		return false;
	}

	/**
	 * This function binds between the customer and the cart.
	 * 
	 * @param customer - represent the object - customer.
	 */
	public void takeCart(Customer customer) {
		shoppingCart = new Cart(customer);

	}
	

	/**
	 * This function disconnect the customer from the cart.
	 */
	public void releaseCart() {
		shoppingCart.clearCart();
		shoppingCart.release();
		shoppingCart = null;
	}

	/**
	 * This function add an item to the customer cart.
	 * 
	 * @param product - represent the object - Product that the user want to add.
	 * @param amount  - represent the quantity of the item that the user want to
	 *                add.
	 */
	public void addProductToCart(Product product, int amount) {
		shoppingCart.addToCart(product, amount);
	}

	/**
	 * This function remove an item from the customer cart.
	 * 
	 * @param product - represent the object - Product that the user want to remove.
	 * @param amount  - represent the quantity of the item that the user want to
	 *                remove.
	 */
	public void removeProductFromCart(Product product, int amount) {
		shoppingCart.removeProductFromCart(product, amount);
	}

	/**
	 * This function shows all the items in the user cart and then shows the total
	 * price to pay and how much he saved during the shopping.
	 */
	// This function uses the show, calculateTotalPrice and moneySaved functions in
	// the Cart class.
	public void showCheck() {
		shoppingCart.show();
		System.out.println("yor total price is: " + shoppingCart.calculateTotalPrice() + " $.");
		System.out.println("in this purchase you saved " + shoppingCart.moneySaved() + " $.");
		System.out.println();
		System.out.println();// double space between the written above and the menu
	}

	/**
	 * This function behave like the pay option in online stores.
	 */
	public void pay() {
		shoppingCart.clearCart();
	}

}
