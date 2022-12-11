package vaccination;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class VaccinationService {

	RegistrationRepository registrationRepository = new RegistrationRepository();


	public void registration(Scanner sc) {
		System.out.println("enter your name");
		String name = sc.nextLine();
		System.out.println("enter your zip code");
		String zip = sc.nextLine();
		System.out.println("enter your age");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("enter your email address");
		String email = sc.nextLine();
		System.out.println("enter your email address again");
		String emailAgain = sc.nextLine();
		System.out.println("enter your ssn");
		String ssn = sc.nextLine();

		validations(name, zip, age, email, emailAgain, ssn);
		registrationRepository.saveRegistration(name, zip, age, email, ssn);
	}

	public void bulkRegistration(Scanner sc) {
		System.out.println("Enter the filename");
		String path = sc.nextLine();
		try (Scanner fileScanner = new Scanner(Path.of(path))) {
			while (fileScanner.hasNextLine()) {
				String[] lineSplit = fileScanner.nextLine().split(";");
				String name = lineSplit[0];
				String zip = lineSplit[1];
				int age = Integer.parseInt(lineSplit[2]);
				String email = lineSplit[3];
				String ssn = lineSplit[4];
				validations(name, zip, age, email, ssn);
				registrationRepository.saveRegistration(name, zip, age, email, ssn);
			}
		} catch (IOException ioe) {
			throw new IllegalStateException("Cannot read file");
		}
	}

	private void validations(String name, String zip, int age, String email, String ssn) {
		validateIsNotEmpty(name, zip, email, ssn);
		validateAge(age);
		validateEmailFormat(email);
		cdvCheck(ssn);
	}

	private void validations(String name, String zip, int age, String email, String emailAgain, String ssn) {
		validateIsNotEmpty(name, zip, email, emailAgain, ssn);
		validateAge(age);
		validateEmailMatch(email, emailAgain);
		validateEmailFormat(email);
		cdvCheck(ssn);
	}

	private void validateAge(int age) {
		if (age < 10 || age > 150) {
			throw new IllegalArgumentException("Age must between 10 and 150");
		}
	}

	private void cdvCheck(String ssn) {
		int sum = 0;
		for (int i = 0; i < ssn.length() - 1; i++) {
			int current = Character.getNumericValue(ssn.charAt(i));
			if (i % 2 == 0) {
				sum += (current * 3);
			} else {
				sum += (current * 7);
			}
		}
		if ((Character.getNumericValue(ssn.charAt(ssn.length() - 1)) != sum % 10)) {
			throw new IllegalArgumentException("Invalid ssn");
		}
	}

	private void validateEmailFormat(String email) {
		if (email.length() < 3 && !email.contains("@")) {
			throw new IllegalArgumentException("Invalid email format");
		}
	}

	private void validateEmailMatch(String email, String emailAgain) {
		if (!(email.equals(emailAgain))) {
			throw new IllegalArgumentException("Email addresses does not match");
		}
	}

	private void validateIsNotEmpty(String ...values) {
		for (String value: values) {
			if (value.isEmpty()) {
				throw new IllegalArgumentException("Field cannot be empty");
			}
		}
	}
}

