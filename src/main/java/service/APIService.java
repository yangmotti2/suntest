package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import util.Commons;
import vo.SunVO;


public class APIService {
	
	
	public List<SunVO> apiSun() throws IOException {
		String urlStr = "https://api.open-meteo.com/v1/forecast?latitude=37.566&longitude=126.9784&daily=uv_index_max,uv_index_clear_sky_max&timezone=Asia%2FTokyo";
		
		URL url = new URL(urlStr);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line = "";
		String resultJson = "";
		
		while((line = br.readLine()) != null) {
			resultJson += line;
		}
		
		br.close();
		connection.disconnect();
		
		//"daily":{"time":["",""], "uv_index_max":["",""], "uv_index_clear_sky_max":["","']}
		JSONObject json_obj = null;
		JSONArray json_time_arr = null; 
		JSONArray json_uv_arr = null; //uv_index_max
		JSONArray json_sky_arr = null;  //uv_index_clear_sky_max
		
		try {
			JSONParser jsonParser = new JSONParser();
			json_obj = (JSONObject)jsonParser.parse(resultJson);
			json_obj = (JSONObject)json_obj.get("daily"); //json_obj 재활용
			
			//(List<String>) 형태로 캐스팅 할 경우 uncheked casting type unsafety 표시가 떠서
			//각각 정석으로 (JSONArray)캐스팅 하는 방법을 사용하였습니다
			json_time_arr = (JSONArray)json_obj.get("time");
			json_uv_arr = (JSONArray)json_obj.get("uv_index_max");
			json_sky_arr = (JSONArray)json_obj.get("uv_index_clear_sky_max");
			
		} catch (Exception e) {
			// TODO: handle exception 
		}
		
		List<SunVO> list = new ArrayList<SunVO>();
		
		for(int i = 0; i < json_time_arr.size(); i++) {
			
			SunVO vo = new SunVO();
			
			//JSONArray 형태를 각 변수 타입에 맞춰서 캐스팅해야 vo에 넣을 수 있다
			String time = (String)json_time_arr.get(i);
			Double uv = (Double)json_uv_arr.get(i);
			Double sky = (Double)json_sky_arr.get(i);
			
			vo.setTime(time);
			vo.setUv_index_max(uv); 
			vo.setUv_index_clear_sky_max(sky);
			
			list.add(vo);
		}
		
		return list;
	}
	
}













