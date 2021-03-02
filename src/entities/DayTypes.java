package entities;

public class DayTypes {

	private Integer dtID;
	private String type;
	
	public DayTypes() {
	}

	public DayTypes(Integer dtID, String type) {
		this.dtID = dtID;
		this.type = type;
	}

	public Integer getDtID() {
		return dtID;
	}

	public void setDtID(Integer dtID) {
		this.dtID = dtID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DayTypes dtID = " + dtID + ", type = " + type;
	}
	
	
	
}
