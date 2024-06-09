package controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SunDAO;
import service.APIService;
import util.Commons;
import vo.SunVO;

@Controller
public class SunController {
	
	SunDAO sun_dao;
	
	public SunController(SunDAO sun_dao) {
		this.sun_dao = sun_dao;
	}
	
	@RequestMapping(value= {"/", "list.do"})
	public String list(Model model) throws IOException {
		APIService serv = new APIService();
		List<SunVO> list = serv.apiTest();
		model.addAttribute("list", list);
		return Commons.SunCommon.VIEW_PATH + "sun_list.jsp";
	}
	
}
