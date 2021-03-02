package entities;

public class Minutes {

	private Integer minutesID;
	private String minutes;
	
	public Minutes() {
	}

	public Minutes(Integer minutesID, String minutes) {
		this.minutesID = minutesID;
		this.minutes = minutes;
	}
	
	public Integer getMinutesID() {
		return minutesID;
	}
	
	public void setMinutesID(Integer minutesID) {
		this.minutesID = minutesID;
	}
	
	public String getMinutes() {
		return minutes;
	}
	
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		return "Minutes minutesID = " + minutesID + ", minutes = " + minutes;
	}
	
	
	
}
