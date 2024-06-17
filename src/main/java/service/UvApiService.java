package service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import vo.UvVO;

public class UvApiService {
	public List<UvVO> get_uv(JSONArray arr_data) {
		List<UvVO> uv_lsit = new ArrayList<UvVO>();
		JSONObject index_obj = null;
		
		for(int i = 0; i < arr_data.size(); i++) {
			UvVO vo = new UvVO();
			
			index_obj = (JSONObject) arr_data.get(i);
			double uv = ((Number) index_obj.get("uv")).doubleValue(); //?????
			String time = (String) index_obj.get("uv_time");

			// ZonedDateTime으로 파싱
	        ZonedDateTime parseTime = ZonedDateTime.parse(time);
			
	        // 시간을 포맷팅한다. (Java 8 이상에서는 DateTimeFormatter 사용 권장)
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			String uv_time = formatter.format(parseTime);
			
			vo.setUv(uv);
			vo.setUv_time(uv_time);
			
			index_obj = (JSONObject) index_obj.get("sun_position");
			double altitude = (Double) index_obj.get("altitude");
			vo.setAltitude(altitude);
			
			double azimuth = (Double) index_obj.get("azimuth");
			vo.setAzimuth(azimuth);
			uv_lsit.add(vo);
		}
		
		return uv_lsit;
	}
}
