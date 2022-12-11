package vaccination;

import java.time.LocalDate;

public class VaccinationData {

	private Long id;
	private Long citizenId;
	private LocalDate vaccinationDate;
	private VaccinationType type;
	private VaccinationStatus status;
	private String note;
}
