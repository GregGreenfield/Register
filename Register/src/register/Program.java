package register;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



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
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://mydb.c2abvobvruo6.ap-southeast-2.rds.amazonaws.com:3306/";
	static final String USER = "greg";
	static final String PASS = "Pa55word";
	
	public static void main(String[] args) {
		
		
		state = State.Login;
		
		
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
		 Connection conn = null;
		 Statement stmt = null;
		 boolean ret = false;
		 
		 try{
			 Class.forName("com.mysql.jdbc.Driver");

		     System.out.println("Connecting to database...");
		     conn = DriverManager.getConnection(DB_URL,USER,PASS);

		     System.out.println("Creating statement...");
		     stmt = conn.createStatement();
		     String sql;
		     sql = "SELECT teacherID, name, userName, password FROM registerdb.Teacher";
		     ResultSet rs = stmt.executeQuery(sql);
    
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
		     stmt.close();
		     conn.close();
		     
		 	 }catch(SQLException se){
		    	 se.printStackTrace();
		     }catch(Exception e){
		    	 e.printStackTrace();
		     }finally{
		    	 try{
		    		 if(stmt!=null)
		    			 stmt.close();
		    	 }catch(SQLException se2){
		    	 }
		    	 try{
		    		 if(conn!=null)
		    			 conn.close();
		    	 }catch(SQLException se){
		    		 se.printStackTrace();
		    	 }
		     }
		  
		return ret;
		}
	
	public static void addclasses(){
		Connection conn = null;
		Statement stmt = null;
	 
		try{
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT classID, className, teacherID FROM registerdb.Class WHERE teacherID = " + teacher.getTID() + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				int id  = rs.getInt("classID");
				String className = rs.getString("className");
	    	 
				System.out.println("Name: " + className );
	    	 
				teacher.addClass(id, className);
			}
	     			     
			rs.close();
			stmt.close();
			conn.close();
	     
	 	 	}catch(SQLException se){
	 	 		se.printStackTrace();
	 	 	}catch(Exception e){
	 	 		e.printStackTrace();
	 	 	}finally{
	 	 		try{
	 	 			if(stmt!=null)
	 	 				stmt.close();
	 	 		}catch(SQLException se2){
	 	 		}
	 	 		try{
	 	 			if(conn!=null)
	 	 				conn.close();
	 	 		}catch(SQLException se){
	 	 			se.printStackTrace();
	 	 		}
	 	 	}
	  
		}
	
	public static void createRegister(String text){
		cl = teacher.getClass(text.substring(0, text.indexOf(" ")));
		
		System.out.println(cl +"!!!!!!!!" + cl.getClassID()+cl.getName());
		
		Connection conn = null;
		Statement stmt = null;
	 
		System.out.println(text.substring(0, text.indexOf(" ")));
		try{
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT registerID, classID, registerDate, registerTime FROM registerdb.Register WHERE classID = " + text.substring(0, text.indexOf(" ")) + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				int registerID = rs.getInt("registerID");
				int classID  = rs.getInt("classID");
				String registerDate = rs.getString("registerDate");
				String registerTime = rs.getString("registerTime");
				
				System.out.println("Date: " + registerDate + " " + registerTime );
	    	 
				cl.addRegister(registerID, registerDate, registerTime);
			}
	     			     
			rs.close();
			stmt.close();
			conn.close();
	     
	 	 	}catch(SQLException se){
	 	 		se.printStackTrace();
	 	 	}catch(Exception e){
	 	 		e.printStackTrace();
	 	 	}finally{
	 	 		try{
	 	 			if(stmt!=null)
	 	 				stmt.close();
	 	 		}catch(SQLException se2){
	 	 		}
	 	 		try{
	 	 			if(conn!=null)
	 	 				conn.close();
	 	 		}catch(SQLException se){
	 	 			se.printStackTrace();
	 	 		}
	 	 	}
	}

	public static void addStudents(String selection){
		R1 = cl.getRegister(selection);
		Connection conn = null, conn1 = null;
		Statement stmt = null,stmt1 = null;
	 
		try{
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT enrolID, studentID, attended, ellrolled FROM registerdb.Enrol WHERE registerID = " + selection.substring(0, selection.indexOf(" ")) + ";";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println(sql);
			
			
			while(rs.next()){
				int enrolID = rs.getInt("enrolID");
				int studentID  = rs.getInt("studentID");
				String attended = rs.getString("attended");
				String enrolled = rs.getString("ellrolled");
				
				System.out.println("Student: " + studentID + " " + attended + " " + enrolled );
				
				String sqlName = "SELECT studentName, RFID FROM registerdb.Student Where studentID = " + studentID + ";";
				System.out.println(sqlName);
				
				conn1 = DriverManager.getConnection(DB_URL,USER,PASS);
				stmt1 = conn.createStatement();
				ResultSet ss = stmt1.executeQuery(sqlName);
				
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
				stmt1.close();
				conn1.close();
			}
	     			     
			rs.close();
			stmt.close();
			conn.close();
			
	     
	 	 	}catch(SQLException se){
	 	 		se.printStackTrace();
	 	 	}catch(Exception e){
	 	 		e.printStackTrace();
	 	 	}finally{
	 	 		try{
	 	 			if(stmt!=null)
	 	 				stmt.close();
	 	 		}catch(SQLException se2){
	 	 		}
	 	 		try{
	 	 			if(conn!=null)
	 	 				conn.close();
	 	 		}catch(SQLException se){
	 	 			se.printStackTrace();
	 	 		}
	 	 	}
	}
}

