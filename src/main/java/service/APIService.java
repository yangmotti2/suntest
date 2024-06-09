package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;

import dao.SunDAO;
import vo.SunVO;


public class APIService {
	
	
	public List<SunVO> apiTest( ) throws IOException {
		// json링크와 보낼 파라미터를 지정한다.
		String urlStr = "https://api.open-meteo.com/v1/forecast?latitude=37.566&longitude=126.9784&daily=uv_index_max,uv_index_clear_sky_max&timezone=Asia%2FTokyo";
		 // url 인스턴스 생성
		URL url = new URL(urlStr);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// connection을 통해 요청한 검색 결과를 읽어오자 (filereader는 내각 가지고 있는걸 읽어오기 때문에 불가능)
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line = "";
		String resultJson = "";
		
		while((line = br.readLine()) != null) {
			resultJson += line;
		}
		
		System.out.println(resultJson);
		// 연결 해제
		br.close();
		connection.disconnect();
		/* 생략 1
		가져온 JSON 형식의 문자열을 실제 JSON으로 변경
		JSON배열 구조를 가져오는 객체 >> 순서가 있는 값의 목록
		 */
		JSONArray json_arr = null;
		
		//일반 JSON 구조를 가져오는 객체 >> 키-값 쌍의 모음
		JSONObject json_obj = null;
		
		//dail 값의 Json 구조를 가져오는 객체
		JSONObject json_daily = null;

		// json에서 각 키값이 리스트 값으로 묶여있기 때문에 다시 담아주는 변수가 필요하다.
		List<String> time_arr = null;
		List<Double> uv_max_arr = null;
		List<Double> uv_sky_max_arr = null;
		
		try {
			// JSON 문자열을 JSONArray나 JSONObject로 변환하는 데 사용
			JSONParser jsonParser = new JSONParser();
			json_obj = (JSONObject) jsonParser.parse(resultJson);
			
			//변환된 json_obj를 통해 daily라는 이름의 배열만 추출
			json_daily = (JSONObject) json_obj.get("daily");
			
			//해당 배열을 각 배열의 키값별로 구분해서 담는다. 
			time_arr = (List<String>) json_daily.get("time");
			uv_max_arr = (List<Double>) json_daily.get("uv_index_max");
			uv_sky_max_arr = (List<Double>) json_daily.get("uv_index_clear_sky_max");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// vo로 변환하는 부분, 각 할당받은 배열의 순차적인 값들을 날짜별로 모아준다.
			List<SunVO> list = new ArrayList<SunVO>();
				
			for(int i = 0; i < time_arr.size(); i++) {
				SunVO vo = new SunVO();
				
				// 긱 리스트의 동일한 index번호끼리 vo로 포장
				vo.setTime(time_arr.get(i));
				vo.setUv_index_max(uv_max_arr.get(i));
				vo.setUv_index_clear_sky_max(uv_sky_max_arr.get(i));
				list.add(vo);
			}//for

		return list; // 해당 객체들을 담은 리스트를 반환
	}
	
}













