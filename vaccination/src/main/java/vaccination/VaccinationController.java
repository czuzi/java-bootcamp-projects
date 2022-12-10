package vaccination;

import java.util.Scanner;

public class VaccinationController {

	private VaccinationService vaccinationService;
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		VaccinationController vaccinationController = new VaccinationController();
		vaccinationController.run();
	}

	private void run() {
		createMenu();
	}

	private void createMenu() {
		System.out.println("Select option:");
		System.out.println("1.Registration");
		System.out.println("2. Bulk registration");
		System.out.println("3. Generate");
		System.out.println("4. Vaccination");
		System.out.println("5. Failed vaccination");
		System.out.println("6. Report");

		int input = Integer.parseInt(sc.nextLine());
		if (input >= 1 && input <= 6) {
			selectionLoop(input);
		} else {
			throw new IllegalArgumentException("Input is not in the menu list");
		}
	}

	private void selectionLoop(int input) {
		switch (input) {
			case 1 -> registration();
		}
	}

	private void registration() {
		String name = sc.nextLine();
		String zip = sc.nextLine();
		int age = Integer.parseInt(sc.nextLine());
		String email = sc.nextLine();
		String emailAgain = sc.nextLine();
		String ssn = sc.nextLine();

		vaccinationService.registration(name, zip, age, email, emailAgain, ssn);
	}
}
