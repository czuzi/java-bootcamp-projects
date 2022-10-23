package catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CatalogItem {

	private String registrationNumber;
	private int pieces;
	List <LibraryItem> libraryItems;

	public CatalogItem(String registrationNumber, int pieces, LibraryItem... libraryItems) {
		validate(registrationNumber, pieces);
		this.registrationNumber = registrationNumber;
		this.pieces = pieces;
		this.libraryItems = Arrays.asList(libraryItems);
	}

	public boolean hasAudioFeature() {
		for (LibraryItem e : libraryItems) {
			if (e.isAudio()) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPrintedFeature() {
		for (LibraryItem e : libraryItems) {
			if (e.isPrinted()) {
				return true;
			}
		}
		return false;
	}

	public int getNumberOfPagesAtOneItem() {
		int res = 0;
		for (LibraryItem e : libraryItems) {
			if (e.isPrinted()) {
				res += ((Book) e).getNumberOfPages();
			}
		}
		return res;
	}

	public List<String> getContributors() {
		List<String> contributors = new ArrayList<>();
		for (LibraryItem e : libraryItems) {
			List<String> toAdd = getContributorsToAdd(contributors, e.getContributors());
			contributors.addAll(toAdd);
		}
		return contributors;
	}

	private List<String> getContributorsToAdd(List<String> contributorsAlready, List<String> contributors) {
		List<String> res = new ArrayList<>();
		for (String contributor : contributors) {
			if (!contributorsAlready.contains(contributor)) {
				res.add(contributor);
			}
		}
		return res;
	}

	public List<String> getTitles() {
		List<String> titles = new ArrayList<>();
		for (LibraryItem e : libraryItems) {
			titles.add(e.getTitle());
		}
		return titles;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public int getPieces() {
		return pieces;
	}

	public List<LibraryItem> getLibraryItems() {
		return libraryItems;
	}

	private void validate(String registrationNumber, int pieces) {
		if (Validators.isBlank(registrationNumber)) {
			throw new IllegalArgumentException("Empty registration number");
		}
		if (pieces < 0) {
			throw new IllegalArgumentException("Pieces must be at least 0");
		}
	}
}
