package vaccination;

import java.time.LocalDate;
import java.util.Objects;

public class Citizen {
	private long id;

	private String name;
	private City city;
	private int age;
	private String email;
	private String ssn;
	private Integer numberOfVaccination;
	private LocalDate lastVaccinationDate;

	public Citizen(String name, String zip, int age, String email, String ssn) {
		this.name = name;
		this.zip = zip;
		this.age = age;
		this.email = email;
		this.ssn = ssn;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public City getCity() {
		return city;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getSsn() {
		return ssn;
	}

	public Integer getNumberOfVaccination() {
		return numberOfVaccination;
	}

	public LocalDate getLastVaccinationDate() {
		return lastVaccinationDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Citizen citizen = (Citizen) o;
		return Objects.equals(ssn, citizen.ssn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ssn);
	}
}
