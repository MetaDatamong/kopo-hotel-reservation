package kr.ac.kopo05.ctc.spring.board.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
import kr.ac.kopo05.ctc.spring.board.dto.NoticeForm;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepository;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;

@Controller
public class BoardController {

	@Autowired
	BoardItemRepository boardItemRepository;

	@Autowired
	UserItemRepository userItemRepository;

	@Autowired
	BoardItemRepositoryCustomImpl boardItemRepositoryCustomImpl;

	@GetMapping("/notice")
	public String showAllNotice(HttpSession session, Model model) {
		// userType ADMIN 일때만 userType에 True return
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		if (username != null) {
			UserItem userItem = userItemRepository.findByUsername(username).orElse(null);
			if (userItem.getType().equals("ADMIN")) {
				model.addAttribute("userType", "ADMIN");
			} else {
				model.addAttribute("userType", "ADMIN");
			}
		}
		List<BoardItem> boardItemlist = boardItemRepositoryCustomImpl.getAll();
		// 등록
		model.addAttribute("boardItemlist", boardItemlist);
		// view 처리
		return "notice/all_view";
	}

	@GetMapping("/notice/create")
	public String getNoticeForm(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);

		if (username == null) {
			return "redirect:/login"; // 로그인 페이지로 리다이렉트
		}

		UserItem userItem = userItemRepository.findByUsername(username).orElse(null);

		if (!"ADMIN".equals(userItem.getType())) {
			redirectAttributes.addFlashAttribute("message", "권한이 없습니다."); // 에러 메시지 설정
			return "redirect:/index"; // 인덱스 페이지로 리다이렉트
		}

		// view 처리
		return "notice/create_form";
	}

	@PostMapping("/notice/create")
	public String saveNoticeForm(HttpSession session, NoticeForm form, Model model) {

		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		if (username == null)
			return "redirect:/login"; // 로그인 페이지로 리다이렉트

		UserItem userItem = userItemRepository.findByUsername(username).orElse(null);

		BoardItem boardItem = new BoardItem("notice", form.getTitle(), dateFormat.format(today), form.getContent(),
				userItem);

		boardItemRepository.save(boardItem);

		return "redirect:/notice";
	}

	@GetMapping("/notice/{boardId}")
	public String showOneNotice(@PathVariable Long boardId, HttpSession session, Model model) {
		// userType ADMIN 일때만 userType에 True return
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		if (username != null) {
			UserItem userItem = userItemRepository.findByUsername(username).orElse(null);
			if (userItem.getType().equals("ADMIN")) {
				model.addAttribute("userType", "ADMIN");
			}
		}
		BoardItem boardItem = boardItemRepositoryCustomImpl.searchByBoardId(boardId);

		model.addAttribute("boardItem", boardItem);

		return "notice/one_view";
	}

	@GetMapping("/notice/delete/{boardId}")
	public String deleteNotice(@PathVariable Long boardId, RedirectAttributes rttr, HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		BoardItem boardItem = boardItemRepository.findById(boardId).orElse(null);

		if (boardItem != null) {
			boardItemRepository.delete(boardItem);

			rttr.addFlashAttribute("message", "Delete Success!!");
		}

		model.addAttribute("boardItem", boardItem);

		return "redirect:/notice";
	}

	@GetMapping("/notice/edit/{boardId}")
	public String editNotice(@PathVariable Long boardId, HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		BoardItem boardItem = boardItemRepositoryCustomImpl.searchByBoardId(boardId);

		model.addAttribute("boardItem", boardItem);
		
		return "/notice/edit_form";
	}
	
	@PostMapping("/notice/edit/{boardId}")
	public String updateNotice(@PathVariable Long boardId, NoticeForm form,HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		BoardItem originBoardItem = boardItemRepositoryCustomImpl.searchByBoardId(boardId);
		
		BoardItem newBoardItem = new BoardItem(boardId, "notice", form.getTitle(), originBoardItem.getDate(), form.getContent(),
				originBoardItem.getUserItem());
		
		if(originBoardItem != null) {
			boardItemRepository.save(newBoardItem);
		}
		
		return "redirect:/notice/" + boardId;
	}
}
