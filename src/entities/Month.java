package entities;

public class Month {

	private Integer monthID;
	private String nameOfMonth;
	private Integer lengthOfMonth;
	
	public Month() {
	}

	public Month(Integer monthID, String nameOfMonth, Integer lengthOfMonth) {
		this.monthID = monthID;
		this.nameOfMonth = nameOfMonth;
		this.lengthOfMonth = lengthOfMonth;
	}

	public Integer getMonthID() {
		return monthID;
	}

	public void setMonthID(Integer monthID) {
		this.monthID = monthID;
	}

	public String getNameOfMonth() {
		return nameOfMonth;
	}

	public void setNameOfMonth(String nameOfMonth) {
		this.nameOfMonth = nameOfMonth;
	}

	public Integer getLengthOfMonth() {
		return lengthOfMonth;
	}

	public void setLengthOfMonth(Integer lengthOfMonth) {
		this.lengthOfMonth = lengthOfMonth;
	}

	@Override
	public String toString() {
		return "Month monthID = " + monthID + ", nameOfMonth = " + nameOfMonth + ", lengthOfMonth = " + lengthOfMonth;
	}
	
	
	
	
	
}
