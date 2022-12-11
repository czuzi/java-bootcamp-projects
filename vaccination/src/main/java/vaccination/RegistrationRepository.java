package vaccination;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class RegistrationRepository {

	private JdbcTemplate jdbcTemplate;

	public RegistrationRepository() {
		DataSourceInit dataSourceInit = new DataSourceInit();
		jdbcTemplate = new JdbcTemplate(dataSourceInit.getDataSource());
	}

	public void saveRegistration(String name, String zip, int age, String email, String ssn) {
		City city = findCityByZipCode(zip);
		jdbcTemplate.update("insert into citizens (citizen_name, city_id, age, email, snn) values (?, ?, ?, ?, ?)",
				name, city.id(), age, email, ssn);
	}



	private City findCityByZipCode(String zip) {
		City city = jdbcTemplate.queryForObject("select * from cities where zip = ? limit 1",
				(rs, rowNum) -> new City(
						rs.getLong("id_city"),
						rs.getString("zip"),
						rs.getString("city"),
						rs.getString("district")), zip);

		if (city == null) {
			throw new IllegalArgumentException("Cannot find city with this postal code");
		}
		return city;
	}

	public List<String> findCitizensByZip(String zip) {
		City city = findCityByZipCode(zip);
		return jdbcTemplate.query("select * from citizens where city_id = ?",
				((rs, rowNum) -> new Citizen(
						rs.getString("citizen_name"),
						zip,
						rs.getInt("age"),
						rs.getString("email"),
						rs.getString("snn")
				).toString()), city.id());
	}
}
