package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import vo.HourlyVO;

public class APIService {

	public HourlyVO get_hourly_temp(Double lat, Double lon) throws IOException {
		
		String urlStr = String.format(
				"https://api.open-meteo.com/v1/forecast?latitude=$s&longitude=$s&current=temperature_2m&hourly=temperature_2m",
				lat, lon);
		
		URL url = new URL(urlStr); 
		HttpURLConnection connection = (HttpURLConnection)
				 url.openConnection(); 
		BufferedReader br = new BufferedReader(new
				 InputStreamReader(connection.getInputStream()));
				 
		String line = ""; 
		String resultJson = "";
				 
		while((line = br.readLine()) != null) { 
			resultJson += line; 
		}
				 
		br.close(); connection.disconnect();
		
		ObjectMapper om = new ObjectMapper();
		
		HourlyVO hourly = null;
		
		try {
			
			JsonNode rootNode = om.readTree(resultJson);
			JsonNode hourlyNode = rootNode.path("hourly");
			
			hourly = om.readValue(hourlyNode.toString(), HourlyVO.class);
			System.out.println(hourly.getTime().size());
			
		} catch (Exception e) {
			System.out.println("json parsing error^^");
		}
		
		return hourly;
		
		 
		/* JSONArray json_arr = null; JSONObject json_obj = null;
		 * 
		 * try { JSONParser jsonParser = new JSONParser(); json_obj = (JSONObject)
		 * jsonParser.parse(resultJson); json_obj = (JSONObject) json_obj.get("hourly");
		 * json_arr = (JSONArray) json_obj.get("temperature_2m"); } catch (Exception e)
		 * { }
		 * 
		 */
		
	}

}













