package vo;

public class UvVO {
	private String uv_time;
	private double uv, altitude, azimuth;

	String uv_color;
	
	public String getUv_color() {
		if(uv >= 0 && uv < 3) {
			return "#558B2F";
		}else if(uv >= 3 && uv < 6) {
			return "#F9A825";
		}else if(uv >= 6 && uv < 8) {
			return "#EF6C00";
		}else if(uv >= 8 && uv < 11) {
			return "#B71C1C";
		}else if(uv >= 11) {
			return "#6A1B9A";
		}else {
			return "수치 값 이상";
		}
		
	}

	public String getUv_time() {
		return uv_time;
	}

	public void setUv_time(String uv_time) {
		this.uv_time = uv_time;
	}

	public double getUv() {
		return uv;
	}

	public void setUv(double uv) {
		this.uv = uv;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(double azimuth) {
		this.azimuth = azimuth;
	}

}
