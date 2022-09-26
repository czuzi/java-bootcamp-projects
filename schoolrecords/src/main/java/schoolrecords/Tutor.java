package schoolrecords;

import java.util.List;

public class Tutor {

	private String name;
	private List<Subject> taughtSubjects;

	public Tutor(String name, List<Subject> taughtSubjects) {
		this.name = name;
		this.taughtSubjects = taughtSubjects;
	}

	public String getName() {
		return name;
	}

	public void addSubject(Subject subject) {
		taughtSubjects.add(subject);
	}

	public boolean isTutorTeachingSubject(String subjectName) {
		for (Subject taughtSubject: taughtSubjects) {
			if (subjectName.equals(taughtSubject.getName())) {
				return true;
			}
		}
		return false;
	}
}
