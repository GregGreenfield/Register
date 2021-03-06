package register;

import java.util.ArrayList;
import java.util.List;

public class Register {
	private Integer registerID;
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
	
	public String getRegisterID(){
		return registerID.toString();
	}
	
	public void addStudent(int enrol,int id, String name,String RFID, boolean enrolled, boolean attend){
		Student st = new Student(enrol,id, name, RFID, enrolled, attend);
		
		students.add(st);
	}
	
	public List getStudents(){
		return students;
	}
	
	public void changeStudentAttend(String id){
		for(int i = 0; i < students.size(); i++)
		{
			if (students.get(i).getStudentID().equals(id))
				students.get(i).changeAttend();
		}
	}
	
	public Student getStudent(String id){
		for(int i = 0; i < students.size(); i++)
		{
			if (students.get(i).getStudentID().equals(id))
				return students.get(i);
		}
		
		return null;
	}
	
	public int getStudentsSize(){
		return students.size();
	}
}
