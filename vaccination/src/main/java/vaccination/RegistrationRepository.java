package vaccination;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class RegistrationRepository {

	private JdbcTemplate jdbcTemplate;

	public RegistrationRepository(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public void saveRegistration(String name, String zip, int age, String email, String emailAgain, String ssn) {
	}
}
