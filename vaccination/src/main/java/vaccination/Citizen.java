package vaccination;

import java.time.LocalDate;
import java.util.Objects;

public class Citizen {
	private Long id;
	private String name;
	private String zip;
	private Integer age;
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

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getZip() {
		return zip;
	}

	public Integer getAge() {
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

	@Override
	public String toString() {
		return name + ";" + zip + ";" + age + ";" + email + ";" + ssn;
	}
}
