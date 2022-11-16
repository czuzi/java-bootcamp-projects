package webshop;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
		Customer customer = findCustomer(email);
		for (Cart cart: carts) {
			if (cart.getCustomer().getEmail().equals(email)) {
				throw new IllegalArgumentException("Customer with e-mail address: " + email + " has already began shopping!");
			}
		}
		carts.add(new Cart(customer));
	}

	public void addCartItem(String email, String barcode, int amount) {
		if (amount < 1) {
			throw new IllegalArgumentException("Quantity cannot be 0 or a negative number");
		}

		Cart cart = findCart(email);

		Product product = store.getProductByBarcode(barcode);

		cart.addCartItem(product, amount);
		carts.add(cart);
	}

	public void rejectCart(String email) {
		carts.removeIf(cart -> cart.getCustomer().getEmail().equals(email));
	}

	public long order(String email, LocalDateTime localDateTime) {
		long id = orders.size() + 1L;
		Customer customer = findCustomer(email);
		Cart cart = findCart(email);

		orders.add(new Order(id, localDateTime, cart.getCustomer(), cart.getProducts()));

		carts.remove(cart);

		checkAndSetVipStatus(email, customer);

		return id;
	}

	private Cart findCart(String email) {
		for (Cart actual: carts) {
			if (actual.getCustomer().getEmail().equals(email)) {
				return actual;
			}
		}
		throw new IllegalArgumentException("Customer with e-mail address " + email + " does not have an actual cart yet.");
	}

	private Customer findCustomer(String email) {
		for (Customer actual: customerService.getCustomers()) {
			if (actual.getEmail().equals(email)) {
				return actual;
			}
		}
		throw new IllegalArgumentException("No customer with e-mail address: " + email);
	}

	private void checkAndSetVipStatus(String email, Customer customer) {
		long ordersForCurrentCustomer = orders.stream()
				.filter(o -> o.getCustomer().getEmail().equals(email))
				.count();

		if (ordersForCurrentCustomer >= 5) {
			customer.setCustomerToVip();
		}
	}

	public Set<Customer> getCustomersByProduct(String barcode) {

		Product product = store.getProductByBarcode(barcode);

		return orders.stream()
				.filter(order -> order.getCart().containsKey(product))
				.map(Order::getCustomer)
				.collect(Collectors.toSet());
	}
}
