package catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogItem {

	private String registrationNumber;
	private int pieces;
	List <LibraryItem> libraryItems;

	public CatalogItem(String registrationNumber, int pieces, List<LibraryItem> libraryItems) {
		this.registrationNumber = registrationNumber;
		this.pieces = pieces;
		this.libraryItems = libraryItems;
	}

	public boolean hasAudioFeature() {

		return false;
	}

	public boolean hasPrintedFeature() {

		return false;
	}

	public int getNumberOfPagesAtOneItem() {
		return 0;
	}

	public List<String> getContributors() {
		return new ArrayList<>();
	}

	public List<String> getTitles() {
		return new ArrayList<>();
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

	private void validate() {
		return;
	}
}
