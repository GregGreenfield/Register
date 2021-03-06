package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://mydb.c2abvobvruo6.ap-southeast-2.rds.amazonaws.com:3306/registerdb";
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
		sql = "SELECT classID, className, teacherID, available FROM registerdb.Class WHERE teacherID = " + teacherID + ";";
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public ResultSet getRegisters(String text) throws SQLException{
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT registerID, classID, registerDate, registerTime, available FROM registerdb.Register WHERE classID = " + text.substring(0, text.indexOf(" ")) + ";";
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public ResultSet getEnrolledStudents(String selection) throws SQLException{
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT enrolID, studentID, ellrolled FROM registerdb.Enrol WHERE classID = " + selection + ";";
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public ResultSet getStudents() throws SQLException{
		stmt = conn.createStatement();
		String sql = "SELECT `Student`.`studentID`, `Student`.`studentName`, `Student`.`RFID` FROM `registerdb`.`Student`";		
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;		
	}
	
	public String getAttendance(String regID, int enrolID) throws SQLException{
		stmt = conn.createStatement();
		String sql = "SELECT `RegEnrol`.`Attened` FROM `registerdb`.`RegEnrol` WHERE RegID =" 
		+regID.substring(0, regID.indexOf(" "))+" AND EnrolID = " + enrolID+";";
		ResultSet rs = stmt.executeQuery(sql);
		
		String ret = "";
		while(rs.next())
			ret = rs.getString("Attened");
		
		return ret;
	}
	
	public ResultSet getStudent(int studentID) throws SQLException{
		Connection conn1 = null;
		Statement stmt1 = null;
		
		String sqlName = "SELECT studentName, RFID FROM registerdb.Student Where studentID = " + studentID + ";";
		
		conn1 = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt1 = conn1.createStatement();
		ResultSet ss = stmt1.executeQuery(sqlName);
		
		return ss;
	}
	
	public void changeAttended(String RegID, String enrolID, String newValue) throws SQLException{
		String sqlName = "UPDATE `registerdb`.`RegEnrol` SET `Attened` = ? WHERE `RegID` = ? AND `EnrolID` = ?;";
		PreparedStatement pre = conn.prepareStatement(sqlName);
		pre.setString(1, newValue);
		pre.setString(2, RegID);
		pre.setString(3, enrolID);
		
		pre.executeUpdate();
		
	}
	
	public boolean getRegEnroll(String regID, String enrolID) throws SQLException{
		stmt = conn.createStatement();
		
		String sql = "SELECT `RegEnrol`.`RegEnrolID` FROM `registerdb`.`RegEnrol` Where RegID = "
				+ regID +" AND EnrolID = " + enrolID +";";
		
		ResultSet ss = stmt.executeQuery(sql);
		
		boolean ret = false;
		
		if(ss.isBeforeFirst())
			ret = true;
		
		return ret;
	}
	
	public void addRegEnroll(int REID, String RID, String EID, String type) throws SQLException{
		String sql = "INSERT INTO `registerdb`.`RegEnrol` (`RegEnrolID`, `RegID`, `EnrolID`, `Attened`)"
				+ "VALUES(?,?,?,?);";
		
		PreparedStatement pre = conn.prepareStatement(sql);
		
		pre.setInt(1, REID);
		pre.setString(2, RID);
		pre.setString(3, EID);
		pre.setString(4, type);
		
		pre.execute();
	}
	
	public int getSizeOfRegEnroll() throws SQLException{
		String sql = "SELECT `RegEnrol`.`RegEnrolID` FROM `registerdb`.`RegEnrol`;";
		stmt = conn.createStatement();
		ResultSet ss = stmt.executeQuery(sql);
		
		int count = 0;
		while(ss.next())
			count = count + ss.getInt("RegEnrolID");
		
		return count;
	}
	
	public void enrolStudent(String studentID, int REI, String RID, String EID) throws SQLException{
		Connection conn1 = DriverManager.getConnection(DB_URL,USER,PASS);
		
		String sql = "UPDATE registerdb.Enrol SET ellrolled = 't' Where studentID = ?;";
		String sql2 = "INSERT INTO `registerdb`.`RegEnrol` (`RegEnrolID`,`RegID`,`EnrolID`,"
				+ "`Attened`)VALUES (?,?,?,?);";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, studentID);
		pre.executeUpdate();
		
		PreparedStatement pre1 = conn1.prepareStatement(sql2);
		pre1.setInt(1, REI);
		pre1.setString(2, RID);
		pre1.setString(3, EID);
		pre1.setString(4, "t");
		
		pre1.execute();
	}
	
	public void enrol(String studentID, String classID) throws SQLException{
		String sql = "INSERT INTO `registerdb`.`Enrol` (`enrolId`,`studentID`,`classID`,`ellrolled`)VALUES(?,?,?,?);";
		PreparedStatement pre = conn.prepareStatement(sql);
		
		pre.setInt(1, getEnrolSize());
		pre.setString(2, studentID);
		pre.setString(3, classID);
		pre.setString(4, "t");
		
		pre.execute();
	}
	
	public String getEnrolID(String studentID, String classID) throws SQLException{
		String sql = "SELECT `Enrol`.`enrolId` FROM `registerdb`.`Enrol` WHERE studentID = ? AND classID = ? AND ellrolled = ?;";
		PreparedStatement pre = conn.prepareStatement(sql);
		
		pre.setString(1, studentID);
		pre.setString(2, classID);
		pre.setString(3, "t");

		ResultSet rs = pre.executeQuery();
		
		if (rs.next())
			return rs.getString("enrolId");
		
		return null;		
	}

	public int getEnrolSize() throws SQLException{
		String sql = "SELECT `Enrol`.`enrolId`  FROM `registerdb`.`Enrol`;";
		stmt = conn.createStatement();
		ResultSet ss = stmt.executeQuery(sql);
		
		int count = 0;
		while(ss.next())
			count = count + ss.getInt("enrolId");
		
		return count;
	}

	public int getSizeReg() throws SQLException{
		String sql = "SELECT `Register`.`registerID` FROM `registerdb`.`Register`;";
		stmt = conn.createStatement();
		ResultSet ss = stmt.executeQuery(sql);
		
		int count = 0;
		while(ss.next())
			count = count + ss.getInt("registerID");
		
		return count;
	}
	
	public void insertRegister(int registerID, String classID, String date, String time) throws SQLException{
		String sql = "INSERT INTO `registerdb`.`Register`(`registerID`,`classID`,`registerDate`,`registerTime`,`available`) VALUES"
				+ "(?,?,?,?,?);";
		PreparedStatement pre = conn.prepareStatement(sql);
		
		pre.setInt(1, registerID);
		pre.setString(2, classID);
		pre.setString(3, date);
		pre.setString(4, time);
		pre.setString(5, "t");
		
		pre.execute();
		
		enrolStudents(registerID, classID);
	}
	
	public void enrolStudents(int registerID, String classID){
		try {
			ResultSet students = getStudents();
			
			while(students.next()){
				String studentID = students.getString("studentID");
				
				String enrolID = getEnrolID(studentID, classID);
				
				if(enrolID != null){
					addRegEnroll(getSizeOfRegEnroll(), String.valueOf(registerID), enrolID, "f");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRegister(String registerID) throws SQLException{
		String sql = "UPDATE `registerdb`.`Register` SET `available` = ? WHERE `registerID` = ?;";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, "f");
		pre.setString(2, registerID);
		
		pre.execute();	
	}
	
	public void insertClass(String classNum, String className, int teacherID) throws SQLException{
		String sql = "INSERT INTO `registerdb`.`Class`(`classID`,`className`,`teacherID`,`available`) VALUES"
				+ "(?,?,?,?);";
		PreparedStatement pre = conn.prepareStatement(sql);
		
		pre.setString(1, classNum);
		pre.setString(2, className);
		pre.setInt(3, teacherID);
		pre.setString(4, "t");
		
		pre.execute();
	}
	
	public void deleteClass(String classID) throws SQLException{
		String sql = "UPDATE `registerdb`.`Class` SET `available` = ? WHERE `classID` = ?;";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, "f");
		pre.setString(2, classID);
		
		pre.execute();	
	}
	
}
