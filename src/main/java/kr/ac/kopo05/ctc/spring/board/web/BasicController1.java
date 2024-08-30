package kr.ac.kopo05.ctc.spring.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController1 {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/roomInfo")
	public String roomInfo() {
		return "roomInfo";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}
