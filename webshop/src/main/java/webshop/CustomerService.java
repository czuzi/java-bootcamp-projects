package webshop;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService {

	private Set<Customer> customers = new HashSet<>();

	public Set<Customer> getCustomers() {
		return new HashSet<>(customers);
	}

	public void addCustomer(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be empty.");
		}
		for (Customer actual: customers) {
			if (actual.getEmail().equalsIgnoreCase(customer.getEmail())) {
				throw new IllegalArgumentException("Customer with e-mail address: " + customer.getEmail() + " is already registered.");
			}
		}
		customers.add(customer);
	}

	public List<Customer> listCustomersByCategoryGiven(CustomerCategory category) {
		return customers.stream()
				.filter(customer -> customer.getCustomerCategory() == category)
				.toList();
	}

	public Customer getCustomerByEmail(String email) {
		return customers.stream()
				.filter(customer -> customer.getEmail().equals(email))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No customer with e-mail address: " + email));
	}

	public List<Customer> listCustomersSortedByEmail() {
		return customers.stream()
				.sorted(Comparator.comparing(Customer::getEmail))
				.toList();
	}

	public List<String> listCustomerNamesSorted() {
		return customers.stream()
				.map(Customer::getName)
				.sorted()
				.toList();
	}
}
