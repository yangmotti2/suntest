package timer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DayCount extends Thread{
	private String time = "";
	private String last_time = "";
	public DayCount() {
		while(true) {
			this.time = timer();
		}
	}
	
	public String timer() {
		//현재 시각을 얻는다
		Date now = new Date();
		
		//시간을 포멧팅한다.
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH"); 
		String formattedNow = formatter.format(now);
		
		System.out.println("시간 : "+formattedNow);
		return time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
