package schoolrecords;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassRecords {

	private List<Student> students = new ArrayList<>();
	private String className;
	private Random random;

	public ClassRecords(String className, Random random) {
		this.className = className;
		this.random = random;
	}

	public String getClassName() {
		return className;
	}

	public boolean addStudent(String name) {
		validate(name);
		for (Student student : students) {
			if (student.getName().equals(name)) {
				return false;
			}
		}
		return students.add(new Student(name));
	}

	private void validate(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name must not be empty!");
		}
	}

	public boolean removeStudent(String studentName) {
		students.removeIf(student -> studentName.equals(student.getName()));
		return false;
	}

	public double calculateClassAverageBySubject(String subjectName) {
		double sum = 0;
		double count = 0;
		for (Student student: students) {
			sum += student.calculateSubjectAverage(subjectName);
			count++;
		}
		return sum / count;
	}

	public Student findStudentByName(String studentName) {
		for (Student student: students) {
			if (studentName.equals(student.getName())) {
				return student;
			}
		}
		throw new IllegalArgumentException("Invalid name");
	}

	public Student repetition() {
		return students.get(random.nextInt(students.size()));
	}

	public List<SubjectResult> listSubjectResults(String subjectName) {
		List<SubjectResult> results = new ArrayList<>();
		for (Student student: students) {
			results.add(new SubjectResult(student.getName(), student.calculateSubjectAverage(subjectName)));
		}
		return results;
	}

	public String listStudentNames() {
		StringBuilder sb = new StringBuilder();
		for (Student student: students) {
			sb.append(student.getName());
		}
		return sb.toString();
	}
}
