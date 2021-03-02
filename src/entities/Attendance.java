package entities;

public class Attendance {

	private Integer attendanceID;
	private Integer userID;
	private Integer year;
	private Integer month;
	private Integer day;
	private String started;
	private String startOfBReak;
	private String finishOfBreak;
	private String finished;
	private String hoursOfDay;
	private String typeOfDay;
	
	
	public Attendance() {
		super();
	}


	public Attendance(Integer attendanceID, Integer userID, Integer year, Integer month, Integer day, String started,
			String startOfBReak, String finishOfBreak, String finished, String hoursOfDay, String typeOfDay) {
		this.attendanceID = attendanceID;
		this.userID = userID;
		this.year = year;
		this.month = month;
		this.day = day;
		this.started = started;
		this.startOfBReak = startOfBReak;
		this.finishOfBreak = finishOfBreak;
		this.finished = finished;
		this.hoursOfDay = hoursOfDay;
		this.typeOfDay = typeOfDay;
	}


	public Integer getAttendanceID() {
		return attendanceID;
	}


	public void setAttendanceID(Integer attendanceID) {
		this.attendanceID = attendanceID;
	}


	public Integer getUserID() {
		return userID;
	}


	public void setUserID(Integer userID) {
		this.userID = userID;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Integer getMonth() {
		return month;
	}


	public void setMonth(Integer month) {
		this.month = month;
	}


	public Integer getDay() {
		return day;
	}


	public void setDay(Integer day) {
		this.day = day;
	}


	public String getStarted() {
		return started;
	}


	public void setStarted(String started) {
		this.started = started;
	}


	public String getStartOfBReak() {
		return startOfBReak;
	}


	public void setStartOfBReak(String startOfBReak) {
		this.startOfBReak = startOfBReak;
	}


	public String getFinishOfBreak() {
		return finishOfBreak;
	}


	public void setFinishOfBreak(String finishOfBreak) {
		this.finishOfBreak = finishOfBreak;
	}


	public String getFinished() {
		return finished;
	}


	public void setFinished(String finished) {
		this.finished = finished;
	}


	public String getHoursOfDay() {
		return hoursOfDay;
	}


	public void setHoursOfDay(String hoursOfDay) {
		this.hoursOfDay = hoursOfDay;
	}


	public String getTypeOfDay() {
		return typeOfDay;
	}


	public void setTypeOfDay(String typeOfDay) {
		this.typeOfDay = typeOfDay;
	}


	@Override
	public String toString() {
		return "Attandance:\n attendanceID = " + attendanceID + ", userID = " + userID + ", year = " + year + ", month = " + month +"\n"
				+ "day = " + day + ", started = " + started + ", startOfBReak = " + startOfBReak + ", finishOfBreak = "
				+ finishOfBreak + ", finished = " + finished + ", hoursOfDay = " + hoursOfDay + ", typeOfDay = " + typeOfDay;
	}
	
	
	
	
	
}
