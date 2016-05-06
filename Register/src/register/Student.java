package register;

public class Student {
	private int StudentID;
	private String name;
	private boolean enrolled;
	private boolean attend;
	
	public Student(int StudentID, String name, boolean enrolled, boolean attend){
		this.StudentID = StudentID;
		this.name = name;
		this.enrolled = enrolled;
		this.attend = attend;
	}
}
