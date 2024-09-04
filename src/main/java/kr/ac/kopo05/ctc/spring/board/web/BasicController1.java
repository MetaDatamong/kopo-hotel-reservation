package kr.ac.kopo05.ctc.spring.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class BasicController1 {

	@RequestMapping(value = "/index")
	public String index(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		return "index";
	}
	
	@RequestMapping(value = "/roomInfo")
	public String roomInfo(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		return "roomInfo";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		return "login";
	}
	
	@RequestMapping(value = "/wayToMk")
	public String wayToMk(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		return "direction";
}

}
