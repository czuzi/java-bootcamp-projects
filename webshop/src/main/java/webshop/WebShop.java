package webshop;

import java.awt.color.ProfileDataException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebShop {

	private Store store;
	private CustomerService customerService;
	private Set<Cart> carts = new HashSet<>();
	private List<Order> orders = new ArrayList<>();

	public WebShop(Store store, CustomerService customerService) {
		validate(store, customerService);
		this.store = store;
		this.customerService = customerService;
	}

	private void validate(Store store, CustomerService customerService) {
		if (store == null) {
			throw new IllegalArgumentException("Store cannot be empty!");
		}
		if (customerService == null) {
			throw new IllegalArgumentException("Customer service cannot be empty!");
		}
	}

	public Store getStore() {
		return store;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public Set<Cart> getCarts() {
		return new HashSet<>(carts);
	}

	public List<Order> getOrders() {
		return new ArrayList<>(orders);
	}

	public void addProduct(Product product) {
		store.addProduct(product);
	}

	public void addCustomer(Customer customer) {
		customerService.addCustomer(customer);
	}

	public void beginShopping(String email) {
		Customer customer = null;
		for (Customer actual: customerService.getCustomers()) {
			if (actual.getEmail().equals(email)) {
				customer = actual;
			}
		}
		if (customer == null) {
			throw new IllegalArgumentException("No customer with e-mail address: " + email);
		}

		for (Cart cart: carts) {
			if (cart.getCustomer().getEmail().equals(email)) {
				throw new IllegalArgumentException("Customer with e-mail address: " + email + " has already began shopping!");
			}
		}
		carts.add(new Cart(customer));
	}

	public void addCartItem(String email, String barcode, int amount) {
		Customer customer = null;
		Product product = null;
		Cart cart = null;

		for (Cart actual: carts) {
			if (actual.getCustomer().getEmail().equals(email)) {
				cart = actual;
			}
		}

		for (Customer actual: customerService.getCustomers()) {
			if (actual.getEmail().equals(email)) {
				customer = actual;
			}
		}
		for (Product actual: store.getProducts()) {
			if (actual.getBarcode().equals(barcode)) {
				product = actual;
			}
		}

		if (customer == null) {
			throw new IllegalArgumentException("Customer with e-mail address " + email + " does not have an actual cart yet.");
		}
		if (amount < 1) {
			throw new IllegalArgumentException("Quantity cannot be 0 or a negative number");
		}

		if (cart == null) {
			throw new IllegalArgumentException("Customer with e-mail address " + email + " does not have an actual cart yet.");
		}
		cart.addCartItem(product, amount);
		carts.add(cart);
	}

	public void rejectCart(String email) {
		for (Cart cart: carts) {
			if (cart.getCustomer().getEmail().equals(email)) {
				carts.remove(cart);
			}
		}
	}

//	public long order(String email, LocalDateTime localDateTime) {
//		return 0;
//	}
}
