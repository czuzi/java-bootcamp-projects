package vaccination;

public class VaccinationService {

	RegistrationRepository registrationRepository;


	public void registration(String name, String zip, int age, String email, String emailAgain, String ssn) {
		validateIsNotEmpty(name, zip, age, email, emailAgain, ssn);
		validateEmail(email, emailAgain);
		cdvCheck(ssn);
		registrationRepository.saveRegistration(name, zip, age, email, emailAgain, ssn);
	}

	private void cdvCheck(String ssn) {
		int sum = 0;
		for (int i = 0; i < ssn.length() - 1; i++) {
			int current = Character.getNumericValue(ssn.charAt(i));
			if (i % 2 == 0) {
				sum += current * 7;
			} else {
				sum += current * 3;
			}
		}
		if ((Character.getNumericValue(ssn.charAt(ssn.length() - 1)) != sum % 10)) {
			throw new IllegalArgumentException("Invalid ssn");
		}
	}

	private void validateEmail(String email, String emailAgain) {
		if (!(email.equalsIgnoreCase(emailAgain) && email.length() > 3 && email.contains("@"))) {
			throw new IllegalArgumentException("Invalid Email");
		}
	}

	private void validateIsNotEmpty(String name, String zip, int age, String email, String emailAgain, String ssn) {
		if (name.isEmpty() || zip.isEmpty() || age < 10 || age > 150 || email.isEmpty() || emailAgain.isEmpty() || ssn.isEmpty()) {
			throw new IllegalArgumentException("Fields cannot be empty");
	}
}
}
