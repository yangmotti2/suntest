package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.RadiDAO;
import dao.SunDAO;
import service.APIService;
import util.Commons;
import vo.HourlyVO;
import vo.RadiVO;
import vo.SunVO;

@Controller
public class SunController {
	
	SunDAO sun_dao;
	RadiDAO radi_dao;
	
	boolean insert_ok = false; // 추가 작업 진행 여부 확인
	
	public SunController(SunDAO sun_dao, RadiDAO radi_dao) {
		this.sun_dao = sun_dao;
		this.radi_dao = radi_dao;
	}
	
	@RequestMapping(value= {"/", "list.do"})
	public String list(Model model) throws IOException {
		
		//현재 시각을 얻는다
		Date now = new Date();
				
		//시간을 포멧팅한다.
		SimpleDateFormat formatter = new SimpleDateFormat("YYYsY-MM-dd"); 
		String formattedNow = formatter.format(now);
		
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
}
