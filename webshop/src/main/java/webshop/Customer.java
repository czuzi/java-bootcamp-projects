package webshop;

import java.util.Objects;

public class Customer implements Comparable<Customer> {

	private String name;
	private String email;
	private CustomerCategory customerCategory;

	public Customer(String name, String email) {
		validate(name, email);
		this.name = name;
		this.email = email;
		this.customerCategory = CustomerCategory.SINGLE;
	}

	private void validate(String name, String email) {
		if ((name == null || name.trim().isEmpty()) || email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Name or e-mail address cannot be empty!");
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public CustomerCategory getCustomerCategory() {
		return customerCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return Objects.equals(email, customer.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	public void setCustomerToVip() {
		this.customerCategory = CustomerCategory.VIP;
	}

	@Override
	public int compareTo(Customer o) {
		return this.email.compareTo(o.email);
	}
}
