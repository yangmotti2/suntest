package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.RadiDAO;
import dao.SunDAO;
import dao.UvDAO;
import service.APIService;
import service.UvApiService;
import util.Commons;
import vo.HourlyVO;
import vo.RadiVO;
import vo.UvVO;

@Controller
public class SunController {
	
	SunDAO sun_dao;
	RadiDAO radi_dao;
	UvDAO uv_dao;
	
	boolean insert_ok = false; // 추가 작업 진행 여부 확인
	
	public SunController(SunDAO sun_dao, RadiDAO radi_dao, UvDAO uv_dao) {
		this.sun_dao = sun_dao;
		this.radi_dao = radi_dao;
		this.uv_dao = uv_dao;
	}
	
	@RequestMapping(value= {"/", "list.do"})
	public String list(Model model) throws IOException {
		
		/* 시간 함수
			Date: 가장 오래된 클래스로, UTC 기반의 날짜와 시간을 가지며, mutable(가변)한 성격을 가집니다.
			LocalDate: Java 8에서 도입된 시간대 독립적인 날짜 클래스로, 시간 구성 요소가 없습니다.
			ZonedDateTime: 시간대 정보를 포함한 날짜와 시간을 나타내는 클래스로, Java 8에서 도입되었으며, 다양한 시간대 관련 작업을 지원합니다.
		*/
		
        // 현재 시각을 얻는다
        ZonedDateTime now = ZonedDateTime.now();

        // 특정 시간대(서울 시간)에 해당하는 ZonedDateTime 생성
        ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        // 시간을 포맷팅한다. (Java 8 이상에서는 DateTimeFormatter 사용 권장)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = formatter.format(seoulTime);
		
        System.out.println(formattedNow);
        
		//해당 날짜의 데이터가 DB에 있는지 확인한다.
		List<RadiVO> list = radi_dao.radi_list_date(formattedNow);

		// 존재할 경우 전체 데이터 조회
		if(list.size() > 0) { // 0보다 클 경우는 임시, 나중가면 24로 변경
			List<RadiVO> total_list = radi_dao.selecList();
			model.addAttribute("list", total_list);
			return Commons.SunCommon.VIEW_PATH + "sun_list.jsp";
		
		}else if(!insert_ok){ // 값이 없는데 추가 작업이 진행되지 않았을 경우
			return "redirect:insert.do";
	
		}else { // 추가 작업이 진행되었는데도 값을 조회할 수 없을 경우
			model.addAttribute("no_date", "데이터 없음");
			return Commons.SunCommon.VIEW_PATH + "sun_list.jsp";
		}
		
	}
	
	// API 호출 하여 데이터 DB에 추가하기
	@RequestMapping("insert.do")
	public String insert(Model model ) throws IOException {
		APIService serv = new APIService();
		HourlyVO h = serv.get_hourly_temp(37.5519, 126.9918);
		List<String> time = h.getTime();
		List<String> direct_radiation = h.getDirect_radiation();
		Map<String, List<String>> map;
		int res = sun_dao.insert(h);
		insert_ok = true; // insert 진행 완료
		
		return "redirect:list.do";
	}
	
	//단순 이동 버튼
	@RequestMapping("/change.do")
	public String change() {
		return Commons.SunCommon.VIEW_PATH + "test.jsp";
	}

	//uv 데이터 조회
	@RequestMapping("/uv_change.do")
	public String change(Model model) {
		List<UvVO> list = uv_dao.list_select();
		model.addAttribute("list", list);
		
		return Commons.SunCommon.VIEW_PATH_UV + "uv_bar.jsp";
	}

	
/* insert 작업이 불가피하다 일단 보류 -------------------------------------------------

	
	//Uv 페이지 이동 버튼
	@RequestMapping("/uv_change.do")
	public String uv_change(Model model) {
        // 현재 시각을 얻는다
        ZonedDateTime now = ZonedDateTime.now();
        
        // 특정 시간대(서울 시간)에 해당하는 ZonedDateTime 생성
        ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        
        // 시간을 포맷팅한다. (Java 8 이상에서는 DateTimeFormatter 사용 권장)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String dt = formatter.format(seoulTime);

        System.out.println(dt);
        
		model.addAttribute("date", dt);
		model.addAttribute("lat", 37.5519);
		model.addAttribute("lng", 126.9918);
		return Commons.SunCommon.VIEW_PATH_UV + "uv_test.jsp";
	}

	//Uv api 이동 버튼
	@RequestMapping("/uv_selectList.do")
	public String uv_selectList(String result) {
	    // JSON 문자열 파싱
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        JSONArray arr_data = null;
		try {
			jsonObject = (JSONObject) parser.parse(result);
			arr_data = (JSONArray) jsonObject.get("result");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // JSON 객체 내부의 요소에 접근
        System.out.println(jsonObject);
		
        UvApiService uvservice = new UvApiService();
        
        List<UvVO> uv_list = uvservice.get_uv(arr_data);
        int res = uv_dao.list_insert(uv_list);
        System.out.println(res +"개 성공 (uv)");
        
		return Commons.SunCommon.VIEW_PATH_UV + "uv_test.jsp";
	}

*/
}
