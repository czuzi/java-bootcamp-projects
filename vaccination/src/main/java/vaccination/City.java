package vaccination;

public class City {

	private String zipCode;
	private String cityName;

	public City(String zipCode) {
		this.zipCode = zipCode;
	}

	public City(String zipCode, String cityName) {
		this.zipCode = zipCode;
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCityName() {
		return cityName;
	}
}
