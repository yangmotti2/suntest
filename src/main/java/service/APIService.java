package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class APIService {
	
	
	public Double apiTest() throws IOException {
		String urlStr = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41";
		
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
		
		//JSONArray json_arr = null;
		
		JSONObject json_obj = null;
		
		try {
			JSONParser jsonParser = new JSONParser();
			json_obj = (JSONObject) jsonParser.parse(resultJson);
			
			//json_arr = (JSONArray) json_obj.get("latitude");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return (Double) json_obj.get("latitude");
	}
	
}













