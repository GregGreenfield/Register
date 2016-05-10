package register;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Program {
	public enum State{ Login, Class, Register, Student, Idle };
	
	private static State state;
	private static login frame;
	private static selectClass frame2;
	private static selectRegister frame3;
	private static StudentDisplay frame4;
	private static Teacher teacher;
	private static Classes cl;
	private static Register R1;
	private static DBConnection dbconnection;
	
	public static void main(String[] args) {
		
		state = State.Login;
		dbconnection = new DBConnection();
		
		for(;;){
			switch(state){
			case Login:
				System.out.println("LOGIN");
			
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame = new login();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				state = State.Idle;
				break;
			
			case Class:
				System.out.println("Class");
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
							try {
								frame.setVisible(false);
								frame2 = new selectClass();
								frame2.changeText(teacher.getName());
								System.out.println("Passed");
								frame2.addToList(teacher.getClasses());
								System.out.println("Passed2");
								frame2.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
			
				state = State.Idle;
				break;
			
			case Register:	
				System.out.println("REGISTER");
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame2.setVisible(false);
							frame3 = new selectRegister();
							frame3.addToList(cl.getRegisters());
							frame3.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				state = State.Idle;
				break;
				
			case Student:
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame3.setVisible(false);
							frame4 = new StudentDisplay();
							frame4.addToList(R1.getStudents());
							frame4.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				state = State.Idle;
				break;
			case Idle:
				break;
			}
			
		}
	}
	
	public static void changeState(State newstate){
		state = newstate;
	}
	
	public static void createTeacher(int ID, String name){
		teacher = new Teacher(ID, name);
	}
	
	public static boolean createTeacher(String username, String password){
		boolean ret = false;
		 
		try{
			ResultSet rs = dbconnection.getTeachers();
    
		    while(rs.next()){
		    	int id  = rs.getInt("teacherID");
		    	String name = rs.getString("name");
		    	String userName = rs.getString("userName").toLowerCase();
		    	String Apassword = rs.getString("password");

		    	 
		    	System.out.println("Name: " + userName + username + " Password : " + Apassword);
		    	 
		    	if(userName.equals(username))
		    	{
		    		System.out.println("YAY!!!");
		    		 
		    		if(Apassword.equals(password))
		    		{
		    			System.out.println("Double YAY!!!");
		    			createTeacher(id, name);
		    			addclasses();
		    			ret = true;
		    			break;
		    		}
		    	}
		    }
		    rs.close();
			}catch(SQLException se){
				se.printStackTrace();
		    }
		return ret;
		}
	
	public static void addclasses(){
	 
		try{
			ResultSet rs = dbconnection.getClasses(teacher.getTID());

			while(rs.next()){
				int id  = rs.getInt("classID");
				String className = rs.getString("className");
	    	 
				System.out.println("Name: " + className );
	    	 
				teacher.addClass(id, className);
			}
	     			     
			rs.close();
	 
 	 		}catch(SQLException se){
 	 			se.printStackTrace();
 	 		}
 		}
	  
	public static void createRegister(String text){
		cl = teacher.getClass(text.substring(0, text.indexOf(" ")));
		
		try{
			ResultSet rs = dbconnection.getRegisters(text);

			while(rs.next()){
				int registerID = rs.getInt("registerID");
				int classID  = rs.getInt("classID");
				String registerDate = rs.getString("registerDate");
				String registerTime = rs.getString("registerTime");
				
				System.out.println("Date: " + registerDate + " " + registerTime );
	    	 
				cl.addRegister(registerID, registerDate, registerTime);
			}
	     			     
			rs.close();
			}catch(SQLException se){
	 	 		se.printStackTrace();
	 	 	}
	 	 }

	public static void addStudents(String selection){
		R1 = cl.getRegister(selection);
	 
		try{
			ResultSet rs = dbconnection.getStudents(selection);
			
			while(rs.next()){
				int enrolID = rs.getInt("enrolID");
				int studentID  = rs.getInt("studentID");
				String attended = rs.getString("attended");
				String enrolled = rs.getString("ellrolled");
				
				
				ResultSet ss = dbconnection.getStudent(studentID);
				
				if(ss.next()){
					String name = ss.getString("studentName");
					String RFID = ss.getString("RFID");
				
					System.out.println(name + RFID);
					boolean attend = false, enroll = false;
					if(attended.equals("t"))
						attend = true;
				
					if(enrolled.equals("t"))
						enroll = true;
								
					R1.addStudent(studentID, name, RFID, enroll, attend);
				}
				
				ss.close();
			}
	     			     
			rs.close();
	     
	 	 	}catch(SQLException se){
	 	 		se.printStackTrace();
	 	 	}
		}
	
	public static void changeStudentState(String selected){
		String id = selected.substring(0, selected.indexOf(" "));
		Student S1 = R1.getStudent(id);
		
		if(S1.isAttend() == true){
			try {
				dbconnection.changeAttended(id, "f");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				dbconnection.changeAttended(id, "t");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		R1.changeStudentAttend(id);
	}

	public static void enrolStudent(String selected){
		String id = selected.substring(0, selected.indexOf(" "));
		
		try {
			dbconnection.enrolStudent(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void addRegister(String date, String time){
		
		try{
			Integer id = cl.unusedRegID();
			
			dbconnection.insertRegister(id, cl.getClassID(), date, time);
			cl.addRegister(id, date, time);
			frame3.addRegister(cl.getDetails(id.toString()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteRegister(String selected){
		String id = selected.substring(0, selected.indexOf(" "));

		try{
			System.out.println(id);
			dbconnection.deleteRegister(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}