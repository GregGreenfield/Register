package register;

public class Student {
	private int StudentID;
	private String name;
	private String RFID;
	private boolean enrolled;
	private boolean attend;
	
	public Student(int StudentID, String name, String RFID, boolean enrolled, boolean attend){
		this.StudentID = StudentID;
		this.name = name;
		this.RFID = RFID;
		this.enrolled = enrolled;
		this.attend = attend;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRFID() {
		return RFID;
	}

	public void setRFID(String rFID) {
		RFID = rFID;
	}

	public boolean isEnrolled() {
		return enrolled;
	}

	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}

	public boolean isAttend() {
		return attend;
	}

	public void setAttend(boolean attend) {
		this.attend = attend;
	}
}
