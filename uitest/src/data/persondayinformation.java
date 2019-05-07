package data;

public class persondayinformation{
	
	private String name;
	private String actualtimenoclear;
	private String calendartime;
	private String catogorys;
	private String subcatogory;
	private String explainreason;
	private String time;
	private String postilinformation;
	
	
	public String getPostilinformation() {
		return postilinformation;
	}


	public void setPostilinformation(String postilinformation) {
		this.postilinformation = postilinformation;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
	
	
	public persondayinformation() {
		name = null;
		actualtimenoclear = null;
		calendartime = null;
		catogorys= null;
		explainreason = null;
		subcatogory = null;
		time = null;
	}
	
	
	public void clear() {
		name = null;
		actualtimenoclear = null;
		calendartime = null;
		catogorys= null;
		explainreason = null;
		subcatogory = null;
		time = null;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActualtimenoclear() {
		return actualtimenoclear;
	}
	public void setActualtimenoclear(String actualtimenoclear) {
		this.actualtimenoclear = actualtimenoclear;
	}
	public String getCalendartime() {
		return calendartime;
	}
	public void setCalendartime(String calendartime) {
		this.calendartime = calendartime;
	}
	public String getCatogorys() {
		return catogorys;
	}
	public void setCatogorys(String catogorys) {
		this.catogorys = catogorys;
	}
	public String getExplainreason() {
		return explainreason;
	}
	public void setExplainreason(String explainreason) {
		this.explainreason = explainreason;
	}

	public String getSubcatogory() {
		return subcatogory;
	}

	public void setSubcatogory(String subcatogory) {
		this.subcatogory = subcatogory;
	}
	
	
		
}