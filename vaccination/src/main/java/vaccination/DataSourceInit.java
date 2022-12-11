package vaccination;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class DataSourceInit {

	private MariaDbDataSource dataSource = new MariaDbDataSource();

	public DataSourceInit() {
		createDataSource();
	}

	private void createDataSource() {
		try{
			dataSource.setUrl("jdbc:mariadb://localhost:3306/vaccinationdb?useUnicode=true");
			dataSource.setUser("vaccination");
			dataSource.setPassword("vaccination");
		}catch (SQLException se){
			throw new IllegalStateException("Cannot connect!",se);
		}
	}

	public MariaDbDataSource getDataSource() {
		return dataSource;
	}
}
