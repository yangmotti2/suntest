package vo;

public class RootVO {
	
    public HourlyVO hourly;

    public RootVO(HourlyVO hourly) {
    	this.hourly = hourly;
	}
    
    // Getter와 Setter
    public HourlyVO getHourly() {
        return hourly;
    }

    public void setHourly(HourlyVO hourly) {
        this.hourly = hourly;
    }
	
}
