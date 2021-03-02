package entities;

public class Hours {

	private Integer hoursID;
	private String hours;
	
	public Hours() {
		
	}

	public Hours(Integer hoursID, String hours) {
		this.hoursID = hoursID;
		this.hours = hours;
	}

	public Integer getHoursID() {
		return hoursID;
	}

	public void setHoursID(Integer hoursID) {
		this.hoursID = hoursID;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "Hours hoursID = " + hoursID + ", hours = " + hours;
	}
	
	
	
}
