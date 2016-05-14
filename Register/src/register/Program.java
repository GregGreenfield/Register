package register;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Program {
	public enum State{ Login, Class, Register, Student, Enroll, Logout, Idle };
	
	private static State state;
	private static login frame;
	private static selectClass frame2;
	private static selectRegister frame3;
	private static StudentDisplay frame4;
	private static enrolStudents frame5;
	private static Teacher teacher;
	private static Classes cl;
	private static Register R1;
	private static DBConnection dbconnection;
	private static String RID;
	private static int curFrame;
	
	public static void main(String[] args) {
		
		state = State.Login;
		dbconnection = new DBConnection();
		curFrame = 0;
		
		for(;;){
			switch(state){
			case Login:
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame = new login();
							frame.setVisible(true);
							curFrame = 1;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				state = State.Idle;
				break;
			
			case Class:
							
				EventQueue.invokeLater(new Runnable() {
					public void run() {
							try {
								if(curFrame == 1)
									frame.setVisible(false);
								if(curFrame == 5)
									frame5.setVisible(false);
								
								frame2 = new selectClass();
								frame2.changeText(teacher.getName() + "'s Classes");
								frame2.addToList(teacher.getClasses());
								frame2.setVisible(true);
								curFrame = 2;	
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				
				state = State.Idle;
				break;
			
			case Register:	

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame2.setVisible(false);
							frame3 = new selectRegister();
							frame3.addToList(cl.getRegisters());
							frame3.setVisible(true);
							curFrame = 3;
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
							curFrame = 4;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				state = State.Idle;
				break;
			
			case Enroll:
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame2.setVisible(false);
							frame5 = new enrolStudents();
							frame5.listAllStudents(displayStudents());
							frame5.setVisible(true);
							curFrame = 5;
							} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				state = State.Idle;
				break;

			case Logout:
				if(curFrame == 2)
					frame2.setVisible(false);
				if(curFrame == 3)
					frame3.setVisible(false);
				if(curFrame == 4)
					frame4.setVisible(false);
				if(curFrame == 5)
					frame5.setVisible(false);
				
				teacher = new Teacher();
				state = State.Login;
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
 
		    	if(userName.equals(username))
		    	{
		    		 
		    		if(Apassword.equals(password))
		    		{
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
				String available = rs.getString("available");
 	 
				if(available.equals("t"))
					teacher.addClass(id, className, available);
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
				String available = rs.getString("available");
				
				if(available.equals("t"))
				{
					cl.addRegister(registerID, registerDate, registerTime);
				}
			}
	     			   
			rs.close();
			}catch(SQLException se){
	 	 		se.printStackTrace();
	 	 	}
	 	 }

	public static void addStudents(String selection){
		R1 = cl.getRegister(selection);
		RID = selection.substring(0, selection.indexOf(" "));
		
		try{
			ResultSet rs = dbconnection.getEnrolledStudents(cl.getClassID());
			
			while(rs.next()){
				int enrolID = rs.getInt("enrolID");
				int studentID  = rs.getInt("studentID");
				String as = dbconnection.getAttendance(selection,enrolID);
				String attended = as;
				String enrolled = rs.getString("ellrolled");
				
				ResultSet ss = dbconnection.getStudent(studentID);
				
				if(ss.next()){
					String name = ss.getString("studentName");
					String RFID = ss.getString("RFID");
				
					boolean attend = false, enroll = false;
					if(attended.equals("t"))
						attend = true;
					
					if (enrolled.equals("t"))
						enroll = true;
								
					R1.addStudent(enrolID, studentID, name, RFID, enroll, attend);
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
				dbconnection.changeAttended(RID,S1.getEnrolID(), "f");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				if(dbconnection.getRegEnroll(RID, S1.getEnrolID())){
					try {
						dbconnection.changeAttended(RID,S1.getEnrolID(), "t");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					dbconnection.addRegEnroll(dbconnection.getSizeOfRegEnroll()+1, RID, S1.getEnrolID());		
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		R1.changeStudentAttend(id);
	}

	public static void enrolStudent(String selected){
		String id = selected.substring(0, selected.indexOf(" "));
		
		Student S1 = R1.getStudent(id);
		
		try {
			dbconnection.enrolStudent(id,R1.getStudentsSize()+1,RID,S1.getEnrolID() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void addRegister(String date, String time){
		
		try{
			Integer id = dbconnection.getSizeReg();
			
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
			dbconnection.deleteRegister(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addClass(String classNum, String name){
		
		try{
			dbconnection.insertClass(classNum, name, teacher.getTID());
			teacher.addClass(Integer.parseInt(classNum), name, "t");
			frame2.addClass(classNum + name);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeClass(String selected){
		String id = selected.substring(0, selected.indexOf(" "));

		try{
			dbconnection.deleteClass(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List displayStudents(){
		List<String> disStud = new ArrayList<String>();
		
		try{
			ResultSet rs = dbconnection.getStudents();
			
			while(rs.next()){
				String studentID  = rs.getString("studentID");
				String studentName = rs.getString("studentName");
			
				disStud.add(studentID + " " + studentName);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return disStud;
	}

	public static void enrolStudent(List studs){
		
		try{
			for(int i = 0 ; i < studs.size(); i++){
				String SID = studs.get(i).toString().substring(0, studs.get(i).toString().indexOf(" "));
				dbconnection.enrol(SID , teacher.getLastClass().getClassID());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
}