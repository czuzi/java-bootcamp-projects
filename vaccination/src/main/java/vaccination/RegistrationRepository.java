package vaccination;

import org.springframework.jdbc.core.JdbcTemplate;

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
		//TODO: throw error if zip code not found
		return jdbcTemplate.queryForObject("select * from cities where zip = ? limit 1",
				(rs, rowNum) -> new City(
						rs.getLong("id_city"),
						rs.getString("zip"),
						rs.getString("city"),
						rs.getString("district")), zip);
	}
}
