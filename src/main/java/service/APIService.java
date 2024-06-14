package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import vo.HourlyVO;
import vo.RootVO;

public class APIService {

	public HourlyVO get_hourly_temp(Double lat, Double lon) throws IOException {
		
		// 받은 위치 파라미터 별 JSON 데이터 뽑아오기
		String urlStr = String.format( 
				"https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&forecast_days=3&hourly=direct_radiation",
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
		System.out.println(resultJson);	// 호출 확인 용	 
		br.close(); connection.disconnect();
		 
		ObjectMapper om = new ObjectMapper();
		
		HourlyVO hourly = new HourlyVO(); // 추가할 list 형식의 Direct_radiationVO
		
		try {//값 받아오기
			JsonNode rootNode = om.readTree(resultJson);
			JsonNode timeNode = rootNode.path("hourly").path("time");
			JsonNode radiNode = rootNode.path("hourly").path("direct_radiation");
			
			List<String> timelist = om.readValue(timeNode.toString(), new TypeReference<List<String>>() {});
			List<String> radilist = om.readValue(radiNode.toString(), new TypeReference<List<String>>() {});
			
			hourly.setTime(timelist);
			hourly.setDirect_radiation(radilist);
			 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("예외오류");
		}
		return hourly; 
		
	} //get_hourly_temp()
}













