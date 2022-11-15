package webshop;

import java.util.*;

public class Store {

	private Set<Product> products = new HashSet<>();

	public Set<Product> getProducts() {
		return new HashSet<>(products);
	}

	public void addProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be empty!");
		}

		for (Product actual: products) {
			if (actual.getBarcode().equals(product.getBarcode())) {
				throw new IllegalArgumentException("Product with barcode: " + product.getBarcode() + " is already registered.");
			}
		}

		products.add(product);
	}

	public Map<ProductCategory, List<Product>> getProductsByCategory() {
		Map<ProductCategory, List<Product>> result = new HashMap<>();
		for (Product product: products) {
			if (!result.containsKey(product.getCategory())) {
				result.put(product.getCategory(), new ArrayList<>());
				result.get(product.getCategory()).add(product);
			} else {
				result.get(product.getCategory()).add(product);
			}
		}
		return result;
	}

	public Product getProductByBarcode(String barcode) {
		return products.stream()
				.filter(product -> product.getBarcode().equals(barcode)).
				findAny().orElseThrow(() -> new IllegalArgumentException("No product with barcode: " + barcode));
	}

	public Product getCheapestProductByCategory(ProductCategory category) {
		return products.stream()
				.filter(product -> product.getCategory() == category)
				.min(Comparator.comparing(Product::getPrice))
				.orElseThrow(IllegalArgumentException::new);
	}

	public List<Product> listProductsSortedByPrice() {
		return products.stream()
				.sorted(Comparator.comparing(Product::getPrice))
				.toList();
	}

	public List<Product> listProductsSortedByName() {
		return products.stream()
				.sorted(Comparator.comparing(Product::getName))
				.toList();
	}
}
