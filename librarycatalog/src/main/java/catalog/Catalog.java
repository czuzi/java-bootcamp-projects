package catalog;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalog {

	private List<CatalogItem> catalogItems = new ArrayList<>();

	public List<CatalogItem> getCatalogItems() {
		return new ArrayList<>(catalogItems);
	}

	public void addItem(CatalogItem item) {
		if (item == null) {
			throw new IllegalArgumentException("Catalog item is empty");
		}
		catalogItems.add(item);
	}

	public void deleteItemByRegistrationNumber(String registrationNumber) {
		if (Validators.isBlank(registrationNumber)) {
			throw new IllegalArgumentException("Empty registration number");
		}
		CatalogItem itemToDelete = null;
		for (CatalogItem item : catalogItems) {
			if (item.getRegistrationNumber().equals(registrationNumber)) {
				itemToDelete = item;
			}
		}
		if (itemToDelete != null) {
			catalogItems.remove(itemToDelete);
		}
	}

	public List<CatalogItem> getAudioLibraryItems() {
		List<CatalogItem> audioItems = new ArrayList<>();
		for (CatalogItem item : catalogItems) {
			if (item.hasAudioFeature()) {
				audioItems.add(item);
			}
		}
		return audioItems;
	}

	public List<CatalogItem> getPrintedLibraryItems() {
		List<CatalogItem> printedItems = new ArrayList<>();
		for (CatalogItem item : catalogItems) {
			if (item.hasPrintedFeature()) {
				printedItems.add(item);
			}
		}
		return printedItems;
	}

	public int getAllPageNumber() {
		int numberOfPages = 0;
		for (CatalogItem item : catalogItems) {
			numberOfPages += item.getNumberOfPagesAtOneItem();
		}
		return numberOfPages;
	}

	public double getAveragePageNumberMoreThan(int pageNumber) {
		if (pageNumber < 1) {
			throw new IllegalArgumentException("Page number must be positive");
		}
		double sum = 0;
		int count = 0;
		for (CatalogItem item : catalogItems) {
			if (item.getNumberOfPagesAtOneItem() > pageNumber) {
				sum += item.getNumberOfPagesAtOneItem();
				count++;
			}
		}
		if (count == 0) {
			throw new IllegalArgumentException("No such book");
		} else {
			return sum / count;
		}
	}

	public List<CatalogItem> findByCriteria(SearchCriteria searchCriteria) {
		if (searchCriteria == null) {
			throw new IllegalArgumentException("Empty criteria");
		}
		List<CatalogItem> res = new ArrayList<>();
		for (CatalogItem item : catalogItems) {
			boolean found = false;
			if (searchCriteria.hasTitle() && item.getTitles().contains(searchCriteria.getTitle())) {
				found = true;
			}
			if (searchCriteria.hasContributor() && item.getContributors().contains(searchCriteria.getContributor())) {
				found = true;
			}
			if (found) {
				res.add(item);
			}
		}
		return res;
	}

	public void readBooksFromFile(Path path) {
		try (Scanner sc = new Scanner(path)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				processLine(line);
			}
		} catch (IOException ioe) {
			throw new IllegalStateException("Can not read from file", ioe);
		} catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
			throw new WrongFormatException("Line format in file is wrong", ex);
		}
	}

	private void processLine(String line) {
		String[] parts = line.split(";");
		String registrationNumber = "R-" + (catalogItems.size() + 1);
		int pieces = Integer.parseInt(parts[0]);
		List<String> authors = new ArrayList<>();
		for (int i = 3; i < parts.length; i++) {
			authors.add(parts[i]);
		}
		LibraryItem book = new Book(parts[1], Integer.parseInt(parts[2]), authors);
		CatalogItem item = new CatalogItem(registrationNumber, pieces, book);
		catalogItems.add(item);
	}
}
