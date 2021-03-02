package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entities.Attendance;
import entities.DayTypes;
import entities.Hours;
import entities.Minutes;
import entities.Month;
import entities.Role;
import entities.Users;

public class AttendanceRepository {
	
	private final String URL = "jdbc:mysql://127.0.0.1:3306/inandout";
	private final String USERNAME = "root";
	private final String PASS = "";

	private Connection conn;

	
	public AttendanceRepository() {
		initConnection();
	}

	
	private void initConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(URL, USERNAME, PASS);
			System.out.println("connection " + conn);
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection faild \n" +e);
		}
	}

	
	public Users login(String userName, String password) {

		String query = "SELECT u.*,r.roleID , r.name AS role_name " + "FROM users u "
				+ "INNER JOIN user_role ur ON ur.userID = u.userID " + "INNER JOIN role r ON r.roleID = ur.roleID "
				+ "WHERE u.userName = ? AND u.password = ?";

		try {
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setString(1, userName);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				/* user.setUsername(rs.getString("password")); */
				user.setActive(rs.getBoolean("active"));

				user.addRole(new Role(rs.getInt("roleID"), rs.getString("role_name")));
				return user;
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! Login error User Name:\n " + e);
		}

		return null;
	}
	
	
	
	public List<Users> findAllUsers() {

		String query = "SELECT * FROM users";
		List<Users> users = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Users user = new Users();
				user.setUserID(rs.getInt("userID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setEmail(rs.getString("email"));
				/* user.setPassword(rs.getString("password")); */
				user.setActive(rs.getBoolean("active"));
				users.add(user);

			}

		} catch (SQLException e) {
			System.err.println("!Hiba! findAllUsers:\n" + e);
		} finally {
			closeStatment(rs, stmt);
		}
		return users;
	}
	
	
	public Users findUserById(int userId) {
		
		String query = "SELECT * FROM users WHERE userID = ?";
		try {
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, userId);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserID(rs.getInt("userID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("active"));

				return user;
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! UserById:\n " + e);
		}
		return null;
	}
	
	
	public List<Users> findUserByLeader(int userId) {
		
		String query = "SELECT u.* FROM users u "
				+ "INNER JOIN leader_employe le ON le.userID = u.userID WHERE leaderID = ?;";
		List<Users> userL = new ArrayList<>();
		
		try {
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, userId);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserID(rs.getInt("userID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("active"));
				
				userL.add(user);
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findUserByLeader:\n " + e);
		}
		return userL;
	}
	
	
	public void saveUser(Users user) {

		String query = "INSERT INTO users(firstName,lastName,userName,email,password,active) " + "VALUES(?,?,?,?,?,?)";

		try {
			PreparedStatement pStmt = conn.prepareCall(query);
			pStmt.setString(1, user.getFirstName());
			pStmt.setString(2, user.getLastName());
			pStmt.setString(3, user.getUserName());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getPassword());
			pStmt.setBoolean(6, user.isActive());
			pStmt.execute();
		} catch (SQLException e) {
			System.err.println("!Hiba! saveUser:\n} " + e);
		}
	}
	
	
	public void updateUser(Users u) {

		String query = "UPDATE `users` SET `firstName`= ?,`lastName`= ?,`userName`= ?,"
						+ "`email`= ?,`password`= ?,`active`= ? WHERE userID = ?";

		try {
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setString(1, u.getFirstName());
			pStmt.setString(2, u.getLastName());
			pStmt.setString(3, u.getUserName());
			pStmt.setString(4, u.getEmail());
			pStmt.setString(5, u.getPassword());
			pStmt.setBoolean(6, u.isActive());
			pStmt.setInt(7, u.getUserID());
			pStmt.execute();
		} catch (SQLException e) {
			System.err.println("!Hiba! updateUsers:\n" + e);
		}
	}
	
	
	public void userToRole(String userName, String name) {
		
		int userID = universalId("userID", "users", "userName", userName);
		int roleID = universalId("roleID", "role", "name", name);
		String query = "INSERT INTO user_role(userID,roleID) VALUES(?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, roleID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("!Hiba! userToRole:\n" + e);

		}

	}
	
	
	public void employeToLeader(String userName, String name) {
		
		int userID = universalId("userID", "users", "userName", userName);
		int leaderID = universalId("userID", "users", "userName", name);
		String query = "INSERT INTO `leader_employe`(`userID`,`leaderID`) VALUES (?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, leaderID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("!Hiba! employeToLeader:\n" + e);

		}

	}
		
	
	public List<Attendance> findAttendanceByUser(int userID) {
		
		String query = "SELECT * FROM attendance WHERE userID = ?";
		List<Attendance> attendance = new ArrayList<>();;
			
		try {
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, userID);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Attendance ad = new Attendance();
				ad.setAttendanceID(rs.getInt("attendanceID"));
				ad.setUserID(rs.getInt("userID"));
				ad.setYear(rs.getInt("year"));
				ad.setMonth(rs.getInt("month"));
				ad.setDay(rs.getInt("day"));
				ad.setStarted(rs.getString("started"));
				ad.setStartOfBReak(rs.getString("startOfBReak"));
				ad.setFinishOfBreak(rs.getString("finishOfBreak"));
				ad.setFinished(rs.getString("finished"));
				ad.setHoursOfDay(rs.getString("hoursOfDay"));
				ad.setTypeOfDay(rs.getString("typeOfDay"));
				attendance.add(ad);
				
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAttendanceByID:\n" + e);
		}
		return attendance;
	}
	
	
	public Attendance findAttendanceByID(int atId) {
		
		String query = "SELECT * FROM attendance WHERE attendanceID = ?";
			
		try {
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, atId);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Attendance ad = new Attendance();
				ad.setAttendanceID(rs.getInt("attendanceID"));
				ad.setUserID(rs.getInt("userID"));
				ad.setYear(rs.getInt("year"));
				ad.setMonth(rs.getInt("month"));
				ad.setDay(rs.getInt("day"));
				ad.setStarted(rs.getString("started"));
				ad.setStartOfBReak(rs.getString("startOfBReak"));
				ad.setFinishOfBreak(rs.getString("finishOfBreak"));
				ad.setFinished(rs.getString("finished"));
				ad.setHoursOfDay(rs.getString("hoursOfDay"));
				ad.setTypeOfDay(rs.getString("typeOfDay"));
				
				return ad;
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAttendanceByID:\n" + e);
		}
		return null;
	}
	
	
	public void saveAttendance(int userID) {
		
		String query = "INSERT INTO `attendance`(`userID`, `year`, `month`, `day`,"
					+ " `started`, `startOfBreak`, `finishOfBreak`, `finished`, `housrOfDay`, `typeOfDay`) " 
					+"VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		LocalDate dt = LocalDate.now();
		Integer y = dt.getYear();
		Integer m = dt.getMonthValue();	
		Integer d = dt.getDayOfMonth();
		
		Attendance at = new Attendance();
		at.setUserID(userID);
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, at.getUserID());
			pStmt.setInt(2, y);
			pStmt.setInt(3, m);
			pStmt.setInt(4, d);
			pStmt.setString(5, at.getStarted());
			pStmt.setString(6, at.getStartOfBReak());
			pStmt.setString(7, at.getFinishOfBreak());
			pStmt.setString(8, at.getFinished());
		
			String hofD = "";
			if ( !at.getStarted().isEmpty()&& !at.getStartOfBReak().isEmpty() &&
				!at.getFinishOfBreak().isEmpty() && !at.getFinished().isEmpty() ) {
				hofD = HoursOfDay(at.getStarted(), at.getStartOfBReak(), at.getFinishOfBreak(), at.getFinished());
			}
			
			
			pStmt.setString(9, hofD);
			pStmt.setString(10, at.getTypeOfDay());
			
			pStmt.execute();
			
		} catch (SQLException e) {
			System.err.println("!HIBA! saveAttendance:\n" +e);
		}
	
	}
	
	
	public void updateAttendance(Attendance at) {
		
		String query = "UPDATE `attendance` SET "
				+ "`started`= ?,`startOfBreak`=?,"
				+ "`finishOfBreak`= ?,`finished`= ?,"
				+ "`housrOfDay`= ?,`typeOfDay`= ? WHERE attendanceID = ?";
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setString(1, at.getStarted());
			pStmt.setString(2, at.getStartOfBReak());
			pStmt.setString(3, at.getFinishOfBreak());
			pStmt.setString(4, at.getFinished());
		
			String hof = "";
			if ( !at.getStarted().isEmpty()&& !at.getStartOfBReak().isEmpty() &&
				!at.getFinishOfBreak().isEmpty() && !at.getFinished().isEmpty() ) {
				hof = HoursOfDay(at.getStarted(), at.getStartOfBReak(), at.getFinishOfBreak(), at.getFinished());
			}
			
			
			pStmt.setString(5, hof);
			pStmt.setString(6, at.getTypeOfDay());
			pStmt.setInt(7, at.getAttendanceID());
			System.out.println("update" +at);
			pStmt.execute();
		} catch (Exception e) {
			System.err.println("!HIBA! updateAttendance:\n" +e);
		}
		
	}
	
	
	public List<Month> findAllMonth() {
		
		String query = "SELECT * FROM month";
		List<Month> month = new ArrayList<>();;
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Month m = new Month();
				m.setMonthID(rs.getInt("monthID"));
				m.setNameOfMonth(rs.getString("nameOfMonth"));
				m.setLengthOfMonth(rs.getInt("lengthOfMonth"));
				month.add(m);
				
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAllMonth:\n" + e);
		}
		return month;
	}
	
	
	public List<Hours> findAllHours() {
		
		String query = "SELECT * FROM hours";
		List<Hours> hours = new ArrayList<>();;
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Hours h = new Hours();
				h.setHoursID(rs.getInt("hoursID"));
				h.setHours(rs.getString("hours"));
				hours.add(h);
				
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAllHours:\n" + e);
		}
		return hours;
	}
	
	
	public List<Minutes> findAllMinutes() {
		
		String query = "SELECT * FROM minutes";
		List<Minutes> minutes = new ArrayList<>();;
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Minutes mi = new Minutes();
				mi.setMinutesID(rs.getInt("minutesID"));
				mi.setMinutes(rs.getString("minutes"));
				minutes.add(mi);
				
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAllMinutes:\n" + e);
		}
		return minutes;
	}
	
	public List<DayTypes> findAllDayTypes() {
		
		String query = "SELECT * FROM daytypes";
		List<DayTypes> dayTypes = new ArrayList<>();;
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				DayTypes dt = new DayTypes();
				dt.setDtID(rs.getInt("dtID"));
				dt.setType(rs.getString("type"));
				dayTypes.add(dt);
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAllMinutes:\n" + e);
		}
		return dayTypes;
	}
	
	
	public List<Role> findAllRole() {
		
		String query = "SELECT * FROM role";
		List<Role> role = new ArrayList<>();;
			
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Role r = new Role();
				r.setRoleID(rs.getInt("role"));
				r.setName(rs.getString("name"));
				role.add(r);
				
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! findAllRole:\n" + e);
		}
		return role;
	}
	
	public String getTime() {
		
		String time ="";
		LocalTime ltn = LocalTime.now();
		
		time = ltn.toString();		
		time = time.substring(0, 5);
		
		return time;
	}
	
	public String HoursOfDay(String started, String startOfBReak, String finishOfBreak, String finished){
		
		String stH = started.substring(0, 2);
		String stM = started.substring(3, 5);
		Integer stHI = Integer.parseInt(stH);
		Integer stMI = Integer.parseInt(stM);
		
		String sobtH = startOfBReak.substring(0, 2);
		String sobtM = startOfBReak.substring(3, 5);
		Integer sobtHI = Integer.parseInt(sobtH);
		Integer	sobtMI = Integer.parseInt(sobtM);
		
		String fobtH = finishOfBreak.substring(0, 2);
		String fobtM = finishOfBreak.substring(3, 5);
		Integer fobtHI = Integer.parseInt(fobtH);
		Integer fobtMI = Integer.parseInt(fobtM);
		
		String ftH = finished.substring(0, 2);
		String ftM = finished.substring(3, 5);
		Integer ftHI = Integer.parseInt(ftH);
		Integer ftMI = Integer.parseInt(ftM);
		
		Integer hour = null;
		Integer minute = null;
		
		if (ftHI-stHI> 12) {
			int h = 24 - ftHI;
			hour = h + stHI;
			
		}else {
			hour = ftHI - stHI;
		}
		
		if (stMI+ftMI > 60) {
			hour++;
			minute = (stMI+ftMI)-60;
			
		} else {
			minute = stMI+ftMI;
		}
		
		Integer breakH = null; // = fobtHI - sobtHI;
		Integer breakM = null; //= fobtMI - sobtMI;
		
		if (sobtHI == 0) {
			breakH = fobtHI;
			
		}else if (sobtHI == 23) {
			breakH = 0;
			
		}else {
			breakH = fobtHI - sobtHI; 
		}
		
		if (60-sobtMI == 30 && 60 - fobtMI == 30) {
			
			breakH++;
			
			breakM = 0;
			
		} else if (60-sobtMI < 30 || 60 - fobtMI < 30){
			
			int sum = sobtMI + fobtMI;
			if (sum > 60) {
				breakH++;
				breakM = (sobtMI+fobtMI)-60;
				
			}else if (sum < 60) {
				
				if (sobtMI >= 30 ) {
					int c1 = 60 - sobtMI;
					breakM = c1 + fobtMI;
					
				}else if (fobtMI > 30) {
					int c2 = 60 - sobtMI;
					breakM = fobtMI + c2 ;
					
				}else if(sobtMI < 30 && fobtMI < 30){
					breakM = sobtMI + fobtMI;
				}
			}
		}
		
		
		hour = hour - breakH;
		minute = minute - breakM;
		
		String hod = "";
		
		if (hour < 10 && minute <10) {
			hod = "0"+hour.toString()+":0"+minute.toString();
		}else if (hour >= 10 && minute <10) {
			hod = hour.toString()+":0"+minute.toString();
		}else if (hour < 10 && minute >=10) {
			hod = "0"+hour.toString()+":"+minute.toString();
		}else {
		hod = hour.toString()+":"+minute.toString();
		}
		
		return hod;
	}
	
	public String sumHoursOfMonth(Integer userId){
		
		LocalDate date = LocalDate.now();
		
		Integer year = date.getYear();
		Integer month = date.getMonthValue();
		
		List<Attendance> attendance = findAttendanceByUser(userId);
		List<Integer> hours = new ArrayList<>();
		List<Integer> minutes = new ArrayList<>();
		
		attendance.forEach(a ->{
			if (a.getYear().equals(year) &&a.getMonth().equals(month)) {
				String hour = a.getHoursOfDay().substring(0, 2);
				String minute = a.getHoursOfDay().substring(3, 5);
				Integer h = Integer.parseInt(hour);
				Integer m = Integer.parseInt(minute);
				
				hours.add(h);
				minutes.add(m);
			}
		});
		
		Integer sumH = 0;
		Integer sumM = 0;
		
		for (int i = 0; i < hours.size(); i++) {
			 sumH += hours.get(i);
		}
		
		for (int i = 0; i < minutes.size(); i++) {
			sumM += minutes.get(i);
		}
		
		Integer hfm = sumM/60;
		sumH = sumH + hfm;
		
		Integer realM = sumM - (60*hfm);
		
		String hof = sumH.toString()+":"+realM.toString();
		
		return hof;
	}
	
	
	public Integer universalId(String idname, String tableName, String where, String who) {

		String query = "SELECT " + idname + " FROM " + tableName + " WHERE " + where + " = '" + who + "'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer id = rs.getInt(idname);
				return id;
			}

		} catch (SQLException e) {
			System.err.println("!Hiba! a lekerdezes kozben [uniID] " + e);
			e.printStackTrace();
		}

		return null;
	}
	
	private void closeStatment(ResultSet rs, Statement stmt) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.err.println("!Hiba! SQL ex in finnaly:\n" + e);
		}
	}
	
	
	
}
