package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://mydb.c2abvobvruo6.ap-southeast-2.rds.amazonaws.com:3306/";
	private static final String USER = "greg";
	private static final String PASS = "Pa55word";
	private Connection conn = null;
	private Statement stmt = null;
	
	
	public DBConnection(){
		try {
			connect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
	}
	
	public ResultSet getTeachers() throws SQLException{
		stmt = conn.createStatement();
	    String sql;
	    sql = "SELECT teacherID, name, userName, password FROM registerdb.Teacher";
	    ResultSet rs = stmt.executeQuery(sql);
		
	    return rs;
	}
	
	public ResultSet getClasses(int teacherID) throws SQLException{
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT classID, className, teacherID FROM registerdb.Class WHERE teacherID = " + teacherID + ";";
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public ResultSet getRegisters(String text) throws SQLException{
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT registerID, classID, registerDate, registerTime FROM registerdb.Register WHERE classID = " + text.substring(0, text.indexOf(" ")) + ";";
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public ResultSet getStudents(String selection) throws SQLException{
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT enrolID, studentID, attended, ellrolled FROM registerdb.Enrol WHERE registerID = " + selection.substring(0, selection.indexOf(" ")) + ";";
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public ResultSet getStudent(int studentID) throws SQLException{
		Connection conn1 = null;
		Statement stmt1 = null;
		
		String sqlName = "SELECT studentName, RFID FROM registerdb.Student Where studentID = " + studentID + ";";
		
		conn1 = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt1 = conn.createStatement();
		ResultSet ss = stmt1.executeQuery(sqlName);
		
		return ss;
	}
	
	public void changeAttended(String studentID, String newValue) throws SQLException{
		String sqlName = "UPDATE registerdb.Enrol SET attended = ? Where studentID = ?;";
		PreparedStatement pre = conn.prepareStatement(sqlName);
		pre.setString(1, newValue);
		pre.setString(2, studentID);
		
		pre.executeUpdate();
	}
	
	public void enrolStudent(String studentID) throws SQLException{
		String sql = "UPDATE registerdb.Enrol SET ellrolled = 't' Where studentID = ?;";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, studentID);
		
		pre.executeUpdate();
	}

	public void insertRegister(int registerID, String classID, String date, String time) throws SQLException{
		String sql = "INSERT INTO `registerdb`.`Register`(`registerID`,`classID`,`registerDate`,`registerTime`) VALUES"
				+ "(?,?,?,?);";
		PreparedStatement pre = conn.prepareStatement(sql);
		
		pre.setInt(1, registerID);
		pre.setString(2, classID);
		pre.setString(3, date);
		pre.setString(4, time);
		
		pre.execute();
	}
	
	public void deleteRegister(String registerID) throws SQLException{
		String sql = "DELETE FROM `registerdb`.`Register` WHERE registerID = ?;";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, registerID);
		
		pre.execute();	
	}
}
