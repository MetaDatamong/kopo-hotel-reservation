package kr.ac.kopo05.ctc.spring.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
import kr.ac.kopo05.ctc.spring.board.dto.JoinForm;
import kr.ac.kopo05.ctc.spring.board.dto.LoginForm;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;
import kr.ac.kopo05.ctc.spring.board.service.UserService;

@Controller
public class LoginSessionController {

	@Autowired
	UserItemRepository userItemRepository;
	
	@Autowired
	UserService userService;
	
	// 로그인 구현하기
	
	@GetMapping("/login")
	public String getLoginForm () {
	    
		return "login/login_form";
	}
	
	@PostMapping("/login")
	public String getLogin(@ModelAttribute LoginForm form, BindingResult bindingResult,
			HttpServletRequest httpServletRequest, Model model) {

		// 로그인 수행
		UserItem user = userService.login(form);
		
		if(user == null) {
            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
        }

        if(bindingResult.hasErrors()) {
            return "redirect:/login";
        }
		
        // 세션 생성 전 기존 세션 파기
        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true); // Session이 없으면 생성한다.
        
        // 세션에 username 넣기
        session.setAttribute("username", user.getUsername());
        session.setMaxInactiveInterval(1800); // 30분간 세션 유지
        
		return "redirect:/index";
	}
	
	// 회원 가입 구현하기
	
	@GetMapping("/join")
    public String joinPage() {

        return "login/join_form";
    }
	
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinForm form, BindingResult bindingResult, Model model) {
     
        // loginId 중복 체크
        if(userService.checkLoginIdDuplication(form.getUsername())) {
            bindingResult.addError(new FieldError("joinRequest", "username", "로그인 아이디가 중복됩니다."));
        }
        // 닉네임 중복 체크
        if(userService.checkNicknameDuplicate(form.getName())) {
            bindingResult.addError(new FieldError("joinRequest", "name", "닉네임이 중복됩니다."));
        }
        // password와 passwordCheck가 같은지 체크
        if(!form.getPassword().equals(form.getPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "바밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "login/join_form";
        }
        
        form.setType("USER");

        userService.join(form);
        return "redirect:/index";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/index";
    }
}
