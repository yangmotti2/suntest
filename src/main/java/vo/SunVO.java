package vo;

public class SunVO {
	private String time;
	private double uv_index_max, uv_index_clear_sky_max;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getUv_index_max() {
		return uv_index_max;
	}

	public void setUv_index_max(double uv_index_max) {
		this.uv_index_max = uv_index_max;
	}

	public double getUv_index_clear_sky_max() {
		return uv_index_clear_sky_max;
	}

	public void setUv_index_clear_sky_max(double uv_index_clear_sky_max) {
		this.uv_index_clear_sky_max = uv_index_clear_sky_max;
	}
}