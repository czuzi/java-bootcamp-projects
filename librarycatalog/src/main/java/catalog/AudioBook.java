package catalog;

import java.util.List;

public class AudioBook implements LibraryItem {

	private String title;
	private List<String> authors;
	private List<String> performers;
	private int length;

	public AudioBook(String title, List<String> authors, List<String> performers, int length) {
		this.title = title;
		this.authors = authors;
		this.performers = performers;
		this.length = length;
	}

	public int getLength() {
		return length;
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
