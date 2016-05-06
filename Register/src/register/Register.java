package register;

import java.util.ArrayList;
import java.util.List;

public class Register {
	private int registerID;
	private String date;
	private String time;
	private List<Student> students;
	
	public Register(int registerID, String date, String time){
		this.registerID = registerID;
		this.date = date;
		this.time = time;
		students = new ArrayList<Student>();
	}
	
	public String getDate(){
		return date;
	}
	
	public String getTime(){
		return time;
	}
	
	public void addStudent(int id, String name, boolean enrolled, boolean attend){
		Student st = new Student(id, name, enrolled, attend);
		
		students.add(st);
	}
}
