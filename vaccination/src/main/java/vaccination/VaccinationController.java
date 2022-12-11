package vaccination;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Scanner;

public class VaccinationController {

	private VaccinationService vaccinationService = new VaccinationService();
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		VaccinationController vaccinationController = new VaccinationController();
		vaccinationController.run();
	}

	private void run() {
		createMenu();
	}

	private void createMenu() {
		System.out.println("""
				Select option:
				1. Registration
				2. Bulk registration
				3. Generate
				4. Vaccination
				5. Failed vaccination
				6. Report""");
		

		int input = Integer.parseInt(sc.nextLine());
		if (input >= 1 && input <= 6) {
			selectionLoop(input);
		} else {
			throw new IllegalArgumentException("Input is not in the menu list");
		}
	}

	private void selectionLoop(int input) {
		switch (input) {
			case 1 -> vaccinationService.registration(sc);
			case 2 -> vaccinationService.bulkRegistration(sc);
		}
	}
}
