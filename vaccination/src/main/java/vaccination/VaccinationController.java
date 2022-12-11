package vaccination;

import java.util.Scanner;

public class VaccinationController {

	private VaccinationService vaccinationService = new VaccinationService();
	private Scanner sc = new Scanner(System.in);
	private boolean toExit;

	public static void main(String[] args) {
		VaccinationController vaccinationController = new VaccinationController();
		vaccinationController.run();
	}

	private void createMenu() {
		System.out.println("""
				Select option:
				1. Registration
				2. Bulk registration
				3. Generate
				4. Vaccination
				5. Failed vaccination
				6. Report
				7. Exit""");
	}

	public void run() {

		int input;
		do {
			createMenu();
			input = Integer.parseInt(sc.nextLine());
			if (input >= 1 && input <= 7) {
				selectionLoop(input);
			}
		} while (!toExit);

	}

	private void selectionLoop(int input) {
		switch (input) {
			case 1 -> vaccinationService.registration(sc);
			case 2 -> vaccinationService.bulkRegistration(sc);
			case 3 -> vaccinationService.generate(sc);
			case 7 -> {
				System.out.println("Goodbye");
				toExit = true;
			}
			default -> System.out.println("No such option");
		}
	}
}
