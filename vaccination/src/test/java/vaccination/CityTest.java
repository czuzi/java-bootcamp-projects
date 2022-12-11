package vaccination;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

	@BeforeEach
	void init() {
		DataSourceInit dataSource = new DataSourceInit();
		Flyway flyway = Flyway.configure().cleanDisabled(false).dataSource(dataSource.getDataSource()).load();
//		flyway.clean();
		flyway.migrate();
	}

	@Test
	void createCity() {
		City city = new City(123L, "4400","nyh", "josa");
	}
}