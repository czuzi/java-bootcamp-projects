package catalog;

import java.util.ArrayList;
import java.util.List;

public class MusicAlbum implements LibraryItem {

	private String title;
	private int length;
	private List<String> composers;
	private List<String> performers;

	public MusicAlbum(String title, int length, List<String> composers, List<String> performers) {
		this.title = title;
		this.length = length;
		this.composers = composers;
		this.performers = performers;
	}

	public MusicAlbum(String title, int length, List<String> performers) {
		this(title, length, new ArrayList<>(), performers);
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
