package schoolrecords;

public class Mark {

	MarkType markType;
	Subject subject;
	Tutor tutor;

	public Mark(MarkType markType, Subject subject, Tutor tutor) {
		this.markType = markType;
		this.subject = subject;
		this.tutor = tutor;
	}

	public MarkType getMarkType() {
		return markType;
	}

	public Subject getSubject() {
		return subject;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public int getMarkValue() {
		return markType.getValue();
	}

	public String getSubjectName() {
		return subject.getName();
	}

	@Override
	public String toString() {
		return subject.getName()
				+ " - "
				+ markType.getDescription()
				+ "("
				+ markType.getValue()
				+ ")";
	}
}
