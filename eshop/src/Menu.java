
/**
 * @author sagie tanami
 *  @date 27/07/2020
 */
import java.util.Scanner;

public class Menu {
	/**
	 * instance variables
	 */
	private Shop homeSuplies;
	private Scanner s = new Scanner(System.in);

	/**
	 * This function is manager function only. This function is the first thing that
	 * starts when the program is activated.
	 */
	public void buildStore() {

		String name;
		boolean pass;

		// check that the stores name isn't null in any form
		do {
			name = "";
			pass = true;
			System.out.println("welcome to your new store how would like to name it?");
			name = s.nextLine();

			if (name.toLowerCase().equals("null")) {
				System.out.println("The store can't be named Null.");
				System.out.println("Please choose another name.");
				System.out.println();
				pass = false;
			}
		} while (!pass);

		homeSuplies = new Shop(name);
		homeSuplies.fillItems();
		signUp((short) 1);
	}

	// This function is the first menu for users.
	private void firstWindow() {
		char ansr = 1;
		System.out.println("welcome, please sign up or log into your account to start shopping");
		do {
			System.out.println("for signing up press \"s\" for log in press \"l\"");
			ansr = s.next().charAt(0);
			System.out.println();// add space

		} while (ansr != 's' && ansr != 'S' && ansr != 'l' && ansr != 'L');
		if (ansr == 's' || ansr == 'S') {
			signUp((short) 2);
		} else {
			logIn();
		}
	}

	// This function allows registered users to log in to there account.
	private void logIn() {
		String userName = "", password = "";
		char ansr = 0;
		do {
			System.out.println("please enter your user name: ");
			userName = s.next();
			System.out.println();// add space

			System.out.println("please enter your password: ");
			password = s.next();
			System.out.println();// add space

			if (!homeSuplies.userLogIn(userName, password)) {
				System.out.println("wrong user name or password");
				System.out.println("would you like to try again?");
				System.out.println("for YES press any key for NO press 9");
				ansr = s.next().charAt(0);
				System.out.println();// add space
			} else {
				ansr = '9';// if the user name and password are correct then don't try again.
			}
		} while (ansr != '9');
		System.out.println();// space between this line and the menu.
		userTypemMenu(homeSuplies.getCustomerByNameAndPassword(userName, password));
	}

	// This function registers a new user to the system.
	// the parameter controls the messages that the user will see during the
	// process.
	private void signUp(short userType) {
		String name = "", password = "", address = "", phone = "";
		short type = -1;
		if (userType == (short) 1) { // 1=manager, 2=regular, 3=manager that register a regular user.
			type = 1;
		} else {
			type = 2;
		}
		boolean isValid = false;
		if (userType == (short) 2) {
			System.out.println("were happy that you chose to sign up to our website.");
			System.out.println("we will need a few details so we could register you: ");
		}
		do {
			System.out.println("whats the user name you want to use? ");
			name = s.next();
			System.out.println();// add space
			isValid = homeSuplies.isUniqueUserName(name);
			if (!isValid) {
				System.out.println("the name you chose can't be used, please try again.\n");
			}
		} while (!isValid);

		System.out.println("whats the password you want to use? ");
		password = s.next();
		s.nextLine();
		System.out.println();// add space

		if (type == (short) 3) {// if manager register a regular user
			System.out.println("whats the new user address? ");
		} else {// if manager or regular user are signing up
			System.out.println("whats your address? ");
		}
		System.out.println("NOTE: if you dont want to answer press: 0");
		address = s.nextLine();
		System.out.println();// add space

		if (address.equals("0")) {
			address = null;
		}

		if (type == (short) 3) {// if manager register a regular user
			System.out.println("whats the new user phone number? ");
		} else {// if manager or regular user are signing up
			System.out.println("whats your phone number? ");
		}
		System.out.println("NOTE: if you dont want to answer press: 0");
		phone = s.nextLine();
		System.out.println();// add space

		if (phone.equals("0")) {
			phone = null;
		}

		System.out.println();// space between the written above and the menu
		homeSuplies.addCustomer(name, password, phone, address, type);
		if (userType == (short) 2 || userType == (short) 1) {
			userTypemMenu(homeSuplies.getCustomerByNameAndPassword(name, password));
		}
	}

	// This function controls the type of menu that the user will be transfered to.
	private void userTypemMenu(Customer customer) {
		if (customer != null) {
			if (customer.getUserType() == (short) 1) {
				managerMenuOption(customer);
			} else {
				customerMenu(customer);
			}
		}
		firstWindow();
	}

	// this function is the menu of the manager.
	private void managerMenuOption(Customer customer) {
		short ansr = 0;
		do {
			System.out.println("Hi " + customer.getUserName() + " what do you wish to do?");
			System.out.println("1 - regular user menu");
			System.out.println("2 - manager menu");
			System.out.println("9 - EXIT");
			ansr = s.nextShort();
			switch (ansr) {
			case 1:
				customerMenu(customer);
				break;

			case 2:
				managerMenu(customer);
				break;
			}
		} while (ansr != 9);
		firstWindow();// returns to the first sign up or log in menu.
	}

	// This function is the customer menu.
	private void customerMenu(Customer customer) {
		short ansr = 0;
		do {
			System.out.println("Hi " + customer.getUserName() + " what do you wish to do?");
			System.out.println("1 - change details in your account");
			System.out.println("2 - start shopping");
			System.out.println("3 - show curren balance of the check");
			System.out.println("4 - pay");
			System.out.println("5 - logout");

			if (customer.getUserType() == (short) 1) {// if the manager chose to enter the customer menu.
				System.out.println("9 - BACK");
			} else {// if a regular open this menu.
				System.out.println("9 - EXIT");
			}
			ansr = s.nextShort();
			switch (ansr) {
			case 1:
				userSettingsMenu(null, customer);
				break;

			case 2:
				shoppingMenu(customer);
				break;

			case 3:
				showCheck(customer);
				break;
			case 4:
				pay(customer);
				break;
			case 5:
				logout(customer);
				break;
			}
		} while (ansr != 9);
	}

	// This function prints the current balance of the user.
	private void showCheck(Customer customer) {
		if (customer != null) {
			if (customer.getCart() != null) {
				if (customer.getCart().getAmountOfItems() > 0) {
					int customerIndex = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
					homeSuplies.getCustomer()[customerIndex].showCheck();
					System.out.println();// space between the line above and the menu.
				} else {
					System.out.println("it seems that your cart is empty");
					System.out.println();// space between the line above and the menu.
				}
			} else {
				System.out.println("it seems that you dont have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

	// This function removes the user from system.
	private void logout(Customer customer) {
		char ansr = 0;
		int index = -1;
		if (customer != null && customer.getConnect() == true) {
			index = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
			if (index >= 0) {// if customer exist in the shop's users array.
				// show the message only if there is a cart and it contains at least 1 item
				if (customer.getCart() != null || customer.getCart().getAmountOfItems() > 0) {
					System.out.println("do you want to save the cart for the next time or delete it?");
					System.out.println("to SAVE press any key to DELETE press 9 ");
					ansr = s.next().charAt(0);
					if (ansr == 9) {
						homeSuplies.getCustomer()[index].releaseCart();
					}
				} else {
					homeSuplies.getCustomer()[index].releaseCart();
				}
				homeSuplies.getCustomer()[index].setConnect(false);// false means the user is offline
				System.out.println();// space between the line above and the menu.
				firstWindow();// returns to the first sign up or log in menu.
			}
		}
	}

	// This function mimic the pay function in web sites.
	private void pay(Customer customer) {
		char ansr = 0;
		int index = -1;
		String input = "";
		if (customer != null) {
			if (customer.getCart() != null) {
				if (customer.getCart().getAmountOfItems() > 0) {
					index = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
					if (index >= 0) {// if customer exist in the shop's users array.
						System.out.println("temporary check:");
						homeSuplies.getCustomer()[index].getCart().show();
						System.out.println();// space between the lines.
						System.out.println(
								"total to pay: " + homeSuplies.getCustomer()[index].getCart().calculateTotalPrice());
						System.out.println();// space between the lines.
						System.out.println("would you preffer DELIVERY or PICKUP the items from the store?");
						System.out.println("for DELIVERY press any key for PICKUP press 9");
						ansr = s.next().charAt(0);

						boolean hasPhone = homeSuplies.getCustomer()[index].getPhone() != null;
						if (!hasPhone) {// if the system doesn't have the user phone
							System.out.println("it seems that we don't have your phone, lets fix it.");
							changePhone(customer);
							System.out.println();// space between the line above and the menu.
						}

						if (ansr != '9') {// in case of DELIVERY:
							boolean hasAddress = homeSuplies.getCustomer()[index].getShippingAddress() != null;

							if (!hasAddress) {// if the system doesn't have the user address
								System.out.println("it seems that we don't have your address, lets fix it.");
								changeAddress(customer);
								System.out.println();// space between the line above and the menu.
							}
						}

						if (ansr != '9') {// in case of DELIVERY the user have to pay with credit card on the site.
							System.out.println("please enter your credit card number: ");
							input = s.next();
							System.out.println("please enter EXP date: ");
							input = s.next();
							System.out.println("please enter 3 digt on the back of the card");
							input = s.next();
						}

						if (homeSuplies.pay(customer)) {
							// after the customer finish his order then he releases his cart.
							homeSuplies.getCustomer()[index].releaseCart();
							if (ansr != '9') {// in case of DELIVERY
								System.out.println("Payment passed successfully");
							}
							System.out.println("your order was approved and sent to the store.");
							System.out.println("we will be intouch with you once the order is ready");
							System.out.println();// space between the line above and the menu.
						} else {
							System.out.println("somthing went wrong during the payment process");
							System.out.println();// space between the line above and the menu.
						}
					}
				} else {
					System.out.println("it seems that your cart is empty");
					System.out.println();// space between the line above and the menu.
				}
			} else {
				System.out.println("it seems that you dont have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

	// This function is the shopping menu for the customer.
	private void shoppingMenu(Customer customer) {
		short ansr = 0;
		do {
			System.out.println("Hi " + customer.getUserName() + " what do you wish to do?");
			System.out.println("1 - Show products on the store");
			System.out.println("2 - Take a cart");
			System.out.println("3 - Return the cart");
			System.out.println("4 - Add product to the cart");
			System.out.println("5 - Remove product from the cart");
			System.out.println("6 - Remove ALL the products from the cart");
			System.out.println("9 - BACK");
			ansr = s.nextShort();

			switch (ansr) {
			case 1:
				homeSuplies.showItems();
				break;

			case 2:
				takeCart(customer);
				break;

			case 3:
				removeCart(customer);
				break;

			case 4:
				addItemToCart(customer);
				break;

			case 5:
				removeItemFromCart(customer);
				break;
			case 6:
				clearAllItems(customer);
				break;
			}
		} while (ansr != 9);
	}

//This function binds between user and a cart.
	private void takeCart(Customer customer) {
		if (customer != null) {
			if (customer.getCart() == null) {

				if (homeSuplies.addCart(customer)) {
					System.out.println("cart has been added successfuly");
					System.out.println();// space between the line above and the menu.
				} else {
					System.out.println("somthing went wrong, please try again later...");
					System.out.println();// space between the line above and the menu.
				}

			} else {
				System.out.println("it seems that you already have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

	//�� �������� ������� ����� �� ���� ����� �� ���
	// This function break the bond between customer and his cart.
	/**private void returnCart(Customer customer) {
		char ansr = '0';
		if (customer != null) {
			if (customer.getCart() != null) {
				s.nextLine();// clear buffer.
				System.out.println("are you sure you want to remove your cart?");
				System.out.println("NOTE: the cart will be deleted with everything you placed in it.");
				System.out.println("for YES press any key for NO press 9");
				ansr = s.next().charAt(0);
				if (ansr != '9') {
					if (homeSuplies.removeCart(customer)) {
						System.out.println("Cart removed successfuly");
						System.out.println();// space between the line above and the menu.
					} else {
						System.out.println("somthing went wrong, we coulden't find your cart");
						System.out.println();// space between the line above and the menu.
					}
				}
			} else {
				System.out.println("it seems that you dont have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}**/

	// This function allows the user to add item to his cart.
	public void addItemToCart(Customer customer) {
		char ansr = 0;
		int amount;
		if (customer != null) {
			if (customer.getCart() != null) {
				do {
					s.nextLine();// clear buffer.
					System.out.println("what is the name of the item you want to add to your cart?");
					String item = s.nextLine();
					do {
						System.out.println("what is the quantity of that item that you want?");
						amount = s.nextInt();
					} while (amount <= 0);
					int index = homeSuplies.getItemIndexByName(item);// search the item in the shop
					if (index >= 0) {
						Product product = homeSuplies.getItems()[index];
						if (homeSuplies.addItem(customer, product, amount)) {
							System.out.println("the item has been added successfuly");
							System.out.println();// space between the line above and the menu.
							ansr = '9';
						} else {
							System.out.println("it seems that this item is out of stock");
						}
					} else {
						System.out.println("sorry we could'nt find the item you want.");
						System.out.println("would you like to try again?");
						System.out.println("for YES press any key for NO press 9");
						ansr = s.next().charAt(0);
						System.out.println();// space between the line above and the menu.
					}
				} while (ansr != '9');
			} else {
				System.out.println("it seems that you don't have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

	// This function allows the user to remove product from the cart.
	private void removeItemFromCart(Customer customer) {
		char ansr = 0;
		int amount;
		if (customer != null) {
			if (customer.getCart() != null) {
				if (customer.getCart().getAmountOfItems() > 0) {
					do {
						s.nextLine();// clear buffer.
						System.out.println("what is the name of the item you want to remove from your cart?");
						String item = s.nextLine();
						do {
							System.out.println("what is the quantity of that item that you want to remove?");
							amount = s.nextInt();
						} while (amount <= 0);
						int index = homeSuplies.getItemIndexByName(item);
						if (index >= 0) {
							Product product = homeSuplies.getItems()[index];
							if (homeSuplies.removeItem(customer, product, amount)) {
								System.out.println("the item has been removed successfuly");
								System.out.println();// space between the line above and the menu.
								ansr = '9';
							}
						} else {
							System.out.println("sorry, we could'nt find the item you want");
							System.out.println("would you like to try again?");
							System.out.println("for YES press any key for NO press 9");
							ansr = s.next().charAt(0);
							System.out.println();// space between the line above and the menu.
						}
					} while (ansr != '9');
				} else {
					System.out.println("it seems that your cart is empty");
					System.out.println();// space between the line above and the menu.
				}
			} else {
				System.out.println("it seems that you dont have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

	// This function allows the user to clear all items from the cart.
	private void clearAllItems(Customer customer) {
		char ansr = 0;
		if (customer != null) {
			if (customer.getCart() != null) {
				if (customer.getCart().getAmountOfItems() > 0) {
					System.out.println("are you sure you want to clear all the item in your shopping cart?");
					System.out.println("for YES press any key, for NO press 9");
					ansr = s.next().charAt(0);
					if (ansr != '9') {
						Product[] products = customer.getCart().getProducts();
						int productsLength = customer.getCart().getAmountOfItems();
						for (int i = 0; i <= productsLength; i++) {
							String itemName = products[i].getName();
							int index = homeSuplies.getItemIndexByName(itemName);
							if (index >= 0) {
								Product product = homeSuplies.getItems()[index];
								int amount = customer.getCart().getProductCounter()[i];
								homeSuplies.removeItem(customer, product, amount);
							}
						}
						if (homeSuplies.clearCart(customer)) {
							System.out.println("all items were cleared from your cart");
							System.out.println();// space between the line above and the menu.
						} else {
							System.out.println("sorry, we coulden't clear your cart ");
							System.out.println();// space between the line above and the menu.
						}
					}
				} else {
					System.out.println("it seems that your cart is empty");
					System.out.println();// space between the line above and the menu.
				}
			} else {
				System.out.println("it seems that you dont have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

	// This function is a setting menu for the user.
	// This function is also used by the manager if they wish to edit another
	// account.
	private void userSettingsMenu(Customer manager, Customer customer) {
		short ansr = 0;
		do {
			if (manager != null) {
				System.out.println("Hi " + manager.getUserName() + " what do you wish to change in "
						+ customer.getUserName() + " account?");
			} else {
				System.out.println("Hi " + customer.getUserName() + " what do you wish to do?");
			}
			System.out.println("1 - change password");
			System.out.println("2 - change address");
			System.out.println("3 - change phone number");
			System.out.println("4 - delete account");
			System.out.println("9 - BACK");
			ansr = s.nextShort();

			switch (ansr) {
			case 1:
				changePassword(customer);
				break;
			case 2:
				changeAddress(customer);
				break;

			case 3:
				changePhone(customer);
				break;
			case 4:
				deleteAccount();
				break;
			}
		} while (ansr != 9);
	}

	// This function allows the user to delete their account.
	private void deleteAccount() {
		String userName = "", password = "";
		int index = -1;
		System.out.println("enter user name please:");
		userName = s.next();
		System.out.println("enter user password please:");
		password = s.next();
		index = homeSuplies.getCustomerIndex(userName, password);
		if (index >= 0) {
			homeSuplies.removeCustomer(homeSuplies.getCustomer()[index]);
			System.out.println("sorry to see you leave, your account removed successfully.");
			System.out.println();// space between the line and the menu.
			firstWindow();
		}
	}

	// This function allows the user to change their password.
	private void changePassword(Customer customer) {
		String userName = "", password = "", newPassword = "", userPassword = "";
		int index = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
		if (index >= 0) {
			s.nextLine();// clear buffer
			do {
				System.out.println("enter previous password please:");
				password = s.nextLine();
			} while (!password.equals(homeSuplies.getCustomer()[index].getPassword()));
			userName = homeSuplies.getCustomer()[index].getUserName();
			do {
				System.out.println("enter new password please:");
				newPassword = s.nextLine();
			} while (newPassword == null || newPassword.equalsIgnoreCase("null"));
			boolean hasChanged = homeSuplies
					.cahngePassword(homeSuplies.getCustomerByNameAndPassword(userName, password), newPassword);
			if (hasChanged) {
				System.out.println("password changed successfully");
				System.out.println();// space between the line and the menu.
			} else {
				System.out.println("faild to change the password");
				System.out.println();// space between the line and the menu.
			}
		}
	}

	// This function allows the user to change their address.
	private void changeAddress(Customer customer) {
		String userName = "", password = "", address = "";
		int index = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
		if (index >= 0) {
			do {
				System.out.println("enter password please:");
				password = s.next();
			} while (!password.equals(homeSuplies.getCustomer()[index].getPassword()));
			userName = homeSuplies.getCustomer()[index].getUserName();
			do {
				System.out.println("enter address please:");
				address = s.next();
			} while (address == null || address.equalsIgnoreCase("null"));
			boolean hasChanged = homeSuplies.cahngeAddress(homeSuplies.getCustomerByNameAndPassword(userName, password),
					address);
			if (hasChanged) {
				System.out.println("address updaeted successfully");
				System.out.println();// space between the line and the menu.
			} else {
				System.out.println("faild to updaete the address");
				System.out.println();// space between the line and the menu.
			}
		}
	}

	// This function allows the user to change their phone number.
	private void changePhone(Customer customer) {
		String userName = "", password = "", phone = "";
		int index = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
		if (index >= 0) {
			do {
				System.out.println("enter password please:");
				password = s.next();
			} while (!password.equals(homeSuplies.getCustomer()[index].getPassword()));
			userName = homeSuplies.getCustomer()[index].getUserName();
			do {
				System.out.println("enter your phone number please:");
				phone = s.next();
			} while (phone == null || phone.equalsIgnoreCase("null"));
			boolean hasChanged = homeSuplies.changePhone(homeSuplies.getCustomerByNameAndPassword(userName, password),
					phone);
			if (hasChanged) {
				System.out.println("phone number updaeted successfully");
				System.out.println();// space between the line and the menu.
			} else {
				System.out.println("faild to updaete the phone number");
				System.out.println();// space between the line and the menu.
			}
		}
	}

	// This function is the Main manager menu.
	private void managerMenu(Customer customer) {
		short ansr = 0;
		do {
			System.out.println("Hi " + customer.getUserName() + " what do you wish to do?");
			System.out.println("1 - system panel");
			System.out.println("2 - store panel");
			System.out.println("9 - EXIT");
			ansr = s.nextShort();

			switch (ansr) {
			case 1:
				managerSystemPanel(customer);
				break;
			case 2:
				managerStorePanel(customer);
				break;
			}
			if (ansr == 9) {
				managerLogOut(customer);// if the manager get out of the system then his user needs to be offline.
			}
		} while (ansr != 9);
	}

	// This function is the store menu for the manager.
	private void managerStorePanel(Customer customer) {
		short ansr = 0;
		do {
			System.out.println("Hi " + customer.getUserName() + " what do you wish to do?");
			System.out.println("1 - add new item to the store");
			System.out.println("2 - remove item from the store");
			System.out.println("3 - increase/decrease quntity of product");
			System.out.println("4 - see how many customers are online now");
			System.out.println("5 - store's income");
			System.out.println("6 - store daily report");
			System.out.println("9 - BACK");
			ansr = s.nextShort();

			switch (ansr) {
			case 1:
				addNewProduct();
				break;
			case 2:
				removePtoduct();
				break;
			case 3:
				updateQuantitiyOfProduct();
				break;
			case 4:
				onlineCustomer();
				break;

			case 5:
				storeIncome();
				break;

			case 6:
				homeSuplies.itemsSold();
				break;
			}
		} while (ansr != 9);
	}

	// This function allows the manager to see how many customers are online.
	public void onlineCustomer() {
		int customers = homeSuplies.onlineCustomers();
		switch (customers) {
		case 1:
			// the 1 who seems as an online customer is actually the manager.
			System.out.println("there are no customers online right now");
			break;
		case 2:
			// the first is the manager and the second is an actual customer.
			System.out.println("there is one customer online right now");
			break;
		default:
			System.out.println("there are " + (customers - 1) + "customer online right now");
			break;
		}
		System.out.println();// space between this line and the menu.
	}

	public void storeIncome() {
		System.out.println("the stores income for now is at " + homeSuplies.getSalesReport() + " �");
		System.out.println();// space between this line and the menu.
	}

	// This function allows the manager to edit the quantity of an item in the
	// store.
	private void updateQuantitiyOfProduct() {
		String name = "";
		int index = -1, quntity = 0;
		s.nextLine();// clear buffer.
		do {
			System.out.println("please enter the product name: ");
			name = s.nextLine();
			index = homeSuplies.getItemIndexByName(name);

			// if the index is below zero then it means that this product is not in
			// the store, and you can't remove product that isn't in the store.
		} while (index < 0);

		double currentQuantity = homeSuplies.getStorage()[index];
		do {
			System.out.println("if you want to INCREASE insert the number as usual.(exmp: 350)");
			System.out.println("if you want to DECREAS insert the number with the minus sign.(exmp: -125)");
			quntity = s.nextInt();
		} while (currentQuantity + quntity < 0);
		if (homeSuplies.addOrUpdateItem(homeSuplies.getItems()[index], quntity)) {
			System.out.println("item quantity updated");
			System.out.println();// space between this line and the menu.
		} else {
			System.out.println("it seems that something went wrong");
			System.out.println();// space between this line and the menu.
		}

	}

	// This function allows the manager to remove product from the store.
	private void removePtoduct() {
		String name = "";
		int index = -1;
		s.nextLine();// clear buffer.
		do {
			System.out.println("please enter the product name: ");
			name = s.nextLine();
			index = homeSuplies.getItemIndexByName(name);
		} while (index < 0);
		if (homeSuplies.removeItem(homeSuplies.getItems()[index])) {
			System.out.println("product removed successfully");
			System.out.println();// space between this line and the menu.
		} else {
			System.out.println("it seems that something went wrong");
			System.out.println();// space between this line and the menu.
		}
	}

	// This function allows the manager to add a new product to the store.
	private void addNewProduct() {
		String name = "";
		double price = 0.0;
		float discount = 0.0f;
		int index = -1, quntity = 0;
		Product product = null;
		s.nextLine();// clear buffer.
		do {
			System.out.println("please enter the product name: ");
			name = s.nextLine();
			index = homeSuplies.getItemIndexByName(name);
		} while (index >= 0);
		do {
			System.out.println("please enter the quantity of that product:");
			quntity = s.nextInt();
		} while (quntity < 0);
		do {
			System.out.println("please enter price for the new product:");
			price = s.nextDouble();
		} while (price < 0);
		do {
			System.out.println("please enter discount for the new product.");
			System.out.println("if there is no discount press 0");
			discount = s.nextFloat();
		} while (discount < 0);
		product = new Product(name, price, discount);
		if (homeSuplies.addOrUpdateItem(product, quntity)) {
			System.out.println("the store has been updated.");
			System.out.println();// space between this line and the menu.
		} else {
			System.out.println("it seems that somthing went wrong.");
			System.out.println();// space between this line and the menu.
		}
	}

	// This function is the system menu for the manager.
	private void managerSystemPanel(Customer manager) {
		short ansr = 0;
		do {
			System.out.println("Hi " + manager.getUserName() + " what do you wish to do?");
			System.out.println("1 - add new manager account");
			System.out.println("2 - add new regular account");
			System.out.println("3 - delete regular account");
			System.out.println("4 - delete manager account");
			System.out.println("5 - edit manager account");
			System.out.println("6 - edit regular account");
			System.out.println("7 - log out");
			System.out.println("9 - BACK");
			ansr = s.nextShort();

			switch (ansr) {
			case 1:
				addNewManager();
				break;
			case 2:
				signUp((short) 3);// manager register a regular user
				break;
			case 3:
				removeAccount((short) 2);// remove regular user
				break;
			case 4:
				removeAccount((short) 1);// remove manager
				break;
			case 5:
				editInformation(manager, (short) 1);
				break;
			case 6:
				editInformation(manager, (short) 2);
				break;

			case 7:
				managerLogOut(manager);
				break;

			}
		} while (ansr != 9);
	}

	// This function allows the manager to log out of the system.
	private void managerLogOut(Customer customer) {
		int index = -1;
		if (customer != null && customer.getConnect() == true) {
			index = homeSuplies.getCustomerIndex(customer.getUserName(), customer.getPassword());
			if (index >= 0) {
				homeSuplies.getCustomer()[index].setConnect(false);
				System.out.println();// space between the line and the menu.
				firstWindow();
			}
		}
	}

	// This function allows the manager to add a new manager account.
	private void addNewManager() {
		String name = "", password = "", address = "", phone = "";
		short type = 1;// represents a manager user.
		boolean isValid = false;
		s.nextLine();// clear buffer.
		do {
			System.out.println("whats the user name you want to set for the new account? ");
			name = s.next();
			isValid = homeSuplies.isUniqueUserName(name);
			if (!isValid) {
				System.out.println("sorry this user name is already taken, please try again.");
			}
		} while (!isValid);

		System.out.println("whats the password you want to set for the new account? ");
		password = s.next();
		s.nextLine();
		do {
			System.out.println("whats the new user address address? ");
			address = s.nextLine();
		} while (address == null || address.equalsIgnoreCase("null"));
		do {
			System.out.println("whats the new user phone number? ");
			phone = s.nextLine();
		} while (phone == null || phone.equalsIgnoreCase("null"));
		homeSuplies.addCustomer(name, password, phone, address, type);
		System.out.println();// space between this line and the menu.
	}

	// This function allows the manager to delete another account.
	private void removeAccount(short type) {
		String name = "";
		s.nextLine();// clear buffer.
		do {
			System.out.println("please enter the name of the user you want to remove");
			name = s.nextLine();
		} while (name == null || name.equalsIgnoreCase("null"));
		Customer customer = homeSuplies.getCustomerByName(name, type);
		if (customer != null) {
			if (homeSuplies.removeCustomer(customer)) {
				System.out.println("customer removed successfuly");
				System.out.println();// space between this line and the menu.
			} else {
				System.out.println("sorry, there seems to be a problem.");
				System.out.println();// space between the line and the menu.
			}

			if (type == (short) 1) {
				Customer shopper = homeSuplies.getManagerAccount();
				managerMenu(shopper);
			}
		} else {
			System.out.println("sorry, there seems to be a problem.");
			System.out.println();// space between this line and the menu.
		}
	}

	// This function allows the manager to change information in another account.
	private void editInformation(Customer manager, short type) {
		String name = "";
		s.nextLine();// clear buffer.
		do {
			System.out.println("please enter the name of the user you want to edit");
			name = s.nextLine();
		} while (name == null || name.equalsIgnoreCase("null"));
		Customer customer = homeSuplies.getCustomerByName(name, type);
		if (customer != null) {
			userSettingsMenu(manager, customer);
		} else {
			System.out.println("sorry, the name you have enterd is not in the system");
			System.out.println();// space between this line and the menu.
		}
	}

	// ������� ������ ����
	private void removeCart(Customer customer) {
		char ansr = 0;
		//int[] amount;
		if (customer != null) {
			if (customer.getCart() != null) {
				if (customer.getCart().getAmountOfItems() > 0) {

					Cart cart = customer.getCart();

					// represents the real amount of unique items that are in the cart
					// for example:3 carrots, 2 water bottles and 1 table will return 3
					int cartLen = cart.getAmountOfItems();
					Product[] products = cart.getProducts();
					//amount = cart.getProductCounter();

					//in this loop all the products are returned from the shopping cart to the store/ 
					while (products[0] != null) {
						String item = products[0].getName();
						int index = homeSuplies.getItemIndexByName(item);
						Product product = homeSuplies.getItems()[index];
						homeSuplies.removeItem(customer, product, cart.getProductCounter()[0]);
					}
					customer.releaseCart();
					System.out.println("Cart returned\n");
				} else {
					customer.releaseCart();
				}
			} else {
				System.out.println("it seems that you dont have a cart");
				System.out.println();// space between the line above and the menu.
			}
		}
	}

}
