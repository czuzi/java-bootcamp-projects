package schoolrecords;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClassRecordsTest {

	ClassRecords classRecords;

	@BeforeEach
	void setUp() {
		School school = new School(Path.of("src/test/resources/school.csv"));
		classRecords = new ClassRecords("Fourth Grade A", new Random(5));
		classRecords.addStudent("Kovacs Rita");
		classRecords.addStudent("Nagy Bela");
		classRecords.addStudent("Varga Marton");
		Student firstStudent = classRecords.findStudentByName("Kovacs Rita");
		firstStudent.addGrading(new Mark(MarkType.A, school.findSubjectByName("foldrajz"), school.findTutorByName("Dienes Iren")));
		firstStudent.addGrading(new Mark(MarkType.C, school.findSubjectByName("matematika"), school.findTutorByName("Szabo Laszlo")));
		firstStudent.addGrading(new Mark(MarkType.D, school.findSubjectByName("foldrajz"), school.findTutorByName("Dienes Iren")));
		Student secondStudent = classRecords.findStudentByName("Nagy Bela");
		secondStudent.addGrading(new Mark(MarkType.A, school.findSubjectByName("biologia"), school.findTutorByName("Dienes Iren")));
		secondStudent.addGrading(new Mark(MarkType.C, school.findSubjectByName("matematika"), school.findTutorByName("Toth Ilona")));
		secondStudent.addGrading(new Mark(MarkType.D, school.findSubjectByName("enek-zene"), school.findTutorByName("Nemeth Lili")));
		Student thirdStudent = classRecords.findStudentByName("Varga Marton");
		thirdStudent.addGrading(new Mark(MarkType.A, school.findSubjectByName("fizika"), school.findTutorByName("Kiss Jozsef")));
		thirdStudent.addGrading(new Mark(MarkType.C, school.findSubjectByName("kemia"), school.findTutorByName("Kiss Jozsef")));
		thirdStudent.addGrading(new Mark(MarkType.D, school.findSubjectByName("foldrajz"), school.findTutorByName("Toth Ilona")));
	}

	@Test
	void testCreate() {
		assertEquals("Fourth Grade A", classRecords.getClassName());
	}

	@Test
	void testCreateWithEmptyName() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> new ClassRecords("", new Random()));
		assertEquals("Name must not be empty!", ex.getMessage());
	}

	@Test
	void testAddStudent() {
		assertTrue(classRecords.addStudent("Nagy Klára"));
	}

	@Test
	void testAddStudentAlreadyExists() {
		assertFalse(classRecords.addStudent("Nagy Bela"));
	}

	@Test
	void testAddStudentWithEmptyName() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> classRecords.addStudent(""));
		assertEquals("Name must not be empty!", ex.getMessage());
	}

	@Test
	void testRemoveStudent() {
		assertTrue(classRecords.removeStudent("Nagy Bela"));
	}

	@Test
	void testRemoveStudentDoesNotExists() {
		assertFalse(classRecords.removeStudent("Nagy Klara"));
	}

	@Test
	void testRemoveStudentWithEmptyName() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> classRecords.removeStudent(""));
		assertEquals("Name must not be empty!", ex.getMessage());
	}

	@Test
	void testCalculateClassAverageBySubject() {
		assertEquals(2.75, classRecords.calculateClassAverageBySubject("foldrajz"));
	}

	@Test
	void testCalculateClassAverageBySubjectWithEmptySubjectName() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> classRecords.calculateClassAverageBySubject(""));
		assertEquals("Name must not be empty!", ex.getMessage());
	}

	@Test
	void testFindStudentByName() {
		assertEquals("Kovacs Rita", classRecords.findStudentByName("Kovacs Rita").getName());
	}

	@Test
	void testEmptyStudentNameShouldThrowException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> classRecords.findStudentByName(""));
		assertEquals("Name must not be empty!", ex.getMessage());
	}

	@Test
	void testEmptyListShouldThrowException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> new ClassRecords("First Grade", new Random()).findStudentByName("Kovács Rita"));
		assertEquals("No students to search!", ex.getMessage());
	}

	@Test
	void testNonExistingStudentShouldThrowException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> classRecords.findStudentByName("Kiss Rita"));
		assertEquals("No student found with name: Kiss Rita", ex.getMessage());
	}

	@Test
	void testRepetition() {
		assertEquals("Varga Marton", classRecords.repetition().getName());
	}

	@Test
	void testEmptyListException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> new ClassRecords("Fourth Grade", new Random()).repetition());
		assertEquals("No students to select for repetition!", ex.getMessage());
	}

	@Test
	void testListSubjectResults() {
		//Given
		List<SubjectResult> list = classRecords.listSubjectResults("foldrajz");
		//Then
		assertEquals("Kovacs Rita", list.get(0).getStudentName());
		assertEquals(3.5, list.get(0).getSubjectAverage());
		assertEquals(2, list.size());
	}

	@Test
	void testListSubjectResultsWithEmptySubjectName() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> classRecords.listSubjectResults(""));
		assertEquals("Name must not be empty!", ex.getMessage());
	}

	@Test
	void testListStudentNames() {
		assertEquals("Kovacs Rita, Nagy Bela, Varga Marton", classRecords.listStudentNames());
	}
}