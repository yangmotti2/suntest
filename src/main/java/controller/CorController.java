package controller;

import org.springframework.stereotype.Controller;

import dao.CorDAO;

@Controller
public class CorController {
	
	CorDAO corDao;
	
	public CorController(CorDAO corDao) {
		this.corDao = corDao;
	}
	
	
}
