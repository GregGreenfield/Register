package register;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class Program {
	public enum State{ Login, Register, Idle };
	
	private static State state;
	private static login frame;
	private static selectClass frame2;
	private static Teacher teacher;
	
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
			
			case Register:
				System.out.println("REGISTER");
				
				
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
	 boolean ret = false;
	 
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
	    	 String teacherID = rs.getString("teacherID");
	    	 
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
}
