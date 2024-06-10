package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import vo.HourlyVO;
import vo.RootVO;

public class APIService {

	public HourlyVO get_hourly_temp(Double lat, Double lon) throws IOException {
		
		String urlStr = String.format( //1. $s -> %s /2. %s -> %f
				"https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m&hourly=temperature_2m",
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
		System.out.println(resultJson);		 
		br.close(); connection.disconnect();
		
		ObjectMapper om = new ObjectMapper();
		
		// 3. null -> new HourlyVO();
		HourlyVO hourly = new HourlyVO(); // 이거 문제 nullpointerexception? >> 왜??????
		
		try {
			
			JsonNode rootNode = om.readTree(resultJson);
			JsonNode hourlyNode = rootNode.path("hourly");
			JsonNode timeNode = hourlyNode.path("time");
			
			// 이거 이전까지는 된다. 이거 에러 나고 있어요 아래 .asText()로 대신했어요
//			hourly = om.readValue(hourlyNode.toString(), HourlyVO.class);
			
			// .asText-----------------------------------------------
			// 이 메서드는 JsonNode에서 텍스트 값을 반환. 
			// 만약 JsonNode가 문자열인 경우에는 그대로 해당 문자열을 반환하고, 
			// 다른 유형의 값인 경우에는 해당 값을 문자열로 변환하여 반환
			
			// .toString---------------------------------------------
			// 이 메서드는 객체를 문자열로 변환하는 일반적인 자바 메서드입니다. 
			// JSON 데이터에서는 toString()을 호출하면 
			// 해당 JsonNode 객체의 JSON 형태의 문자열 표현을 반환

			// 4. 여기부터 setTime까지가 대신한 부분
			List<String> time = new ArrayList<String>();
			for(int i = 0; i < timeNode.size(); i++) {
				time.add(timeNode.get(i).asText());
 			}
			hourly.setTime(time);
			
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













