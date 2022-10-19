package catalog;

import java.util.List;

public class Book implements LibraryItem {

	private String title;
	private  int numberOfPages;
	private List<String> authors;

	public Book(String title, int numberOfPages, List<String> authors) {
		this.title = title;
		this.numberOfPages = numberOfPages;
		this.authors = authors;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	@Override
	public List<String> getContributors() {
		return null;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public boolean isPrinted() {
		return false;
	}

	@Override
	public boolean isAudio() {
		return false;
	}
}
