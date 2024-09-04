package kr.ac.kopo05.ctc.spring.board.web;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.ac.kopo05.ctc.spring.board.domain.Reservation;
import kr.ac.kopo05.ctc.spring.board.domain.RoomItem;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.ReservationRepository;
import kr.ac.kopo05.ctc.spring.board.repo.ReservationRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.RoomItemRepository;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ReservationRepositoryCustomImpl reservationRepositoryCustomImpl;
	
	@Autowired
	UserItemRepository userItemRepository;
	
	@Autowired
	RoomItemRepository roomItemRepository;
	
	
	@GetMapping("/bookRoom")
	public String createNewForm(HttpSession session, Model model) {
		// 세션에서 사용자 이름 가져오기
	    String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
	    // 만약 세션에 사용자 이름이 없으면(즉, 로그인되지 않은 상태)
	    if (username == null) {
	        return "redirect:/login"; // 로그인 페이지로 리다이렉트
	    }
		
		return "reservation/booking_form";
	}
	
	@PostMapping("/checkAvailability")
    public String checkAvailability(@RequestParam Long roomId,
                                    @RequestParam String checkInDate,
                                    @RequestParam String checkOutDate,
                                    Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		List<Reservation> resultRA =
		reservationRepositoryCustomImpl.checkRA(roomId, checkInDate, checkOutDate);
		
//		boolean roomAvailable = false;
//		
//		if(resultRA == null) {
//			roomAvailable = true;
//		}
		
		boolean roomAvailable = (resultRA == null || resultRA.isEmpty());
		
		model.addAttribute("roomAvailable", roomAvailable);
		model.addAttribute("roomId", roomId);
	    model.addAttribute("checkInDate", checkInDate);
	    model.addAttribute("checkOutDate", checkOutDate);
	    
		return "reservation/booking_form_checked";
	}
	
	@PostMapping("/bookRoom")
	public String booking(HttpSession session, 
							@RequestParam Long roomId,
            				@RequestParam String checkInDate,
            				@RequestParam String checkOutDate, Model model) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 세션에서 username 가져와서 조회.
		UserItem userItem = userItemRepository.findByUsername(username).orElse(null);
		
		RoomItem roomItem = roomItemRepository.findById(roomId).orElse(null);
		
		//No request.ver
		Reservation r = new Reservation(dateFormat.format(today), checkInDate, checkOutDate, userItem, roomItem, "reservation_wait", "예약 대기");
		
		reservationRepository.save(r);
		
		//임시
		return "reservation/booking_form";
		}
	
	
	@PostMapping("/requestCancell") 
	public String requestCancell(@RequestParam Long reservationId, Model model, HttpSession session) {
		
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		
		Reservation originR = reservationRepository.findById(reservationId).orElse(null);
		

		Reservation updatedR = new Reservation(reservationId, originR.getReservationDate(), originR.getCheckInDate(), originR.getCheckOutDate(),
				originR.getUserItem(), originR.getRoomItem(), "cancell_wait" , "취소 대기" );
		
		
		if(originR != null) {
			reservationRepository.save(updatedR);
		}
		
		return "redirect:/bookDetail";
	}
	
	
	@GetMapping("/bookDetail")
	public String viewBookDetail(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
	    
		// 세션에서 사용자 이름 가져오기
	    
	    // 만약 세션에 사용자 이름이 없으면(즉, 로그인되지 않은 상태)
	    if (username == null) {
	        return "redirect:/login"; // 로그인 페이지로 리다이렉트
	    }
	    UserItem userItem = userItemRepository.findByUsername(username).orElse(null);
	    
		List<Reservation> r = reservationRepositoryCustomImpl.searchByUserId(userItem.getUserId());
		
		model.addAttribute("reservation", r);

		return "reservation/book_detail";
	}
	
	@GetMapping("/admin/management")
	public String viewBookManagement(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
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

	    List<Reservation> r = reservationRepositoryCustomImpl.getAll();
	    
	    model.addAttribute("reservation", r);
	    
	    return "reservation/management";
	}
	
	@PostMapping("/confirmReservation") 
	public String confirmReservation(@RequestParam Long reservationId, Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		
		Reservation originR = reservationRepository.findById(reservationId).orElse(null);
		

		Reservation updatedR = new Reservation(reservationId, originR.getReservationDate(), originR.getCheckInDate(), originR.getCheckOutDate(),
				originR.getUserItem(), originR.getRoomItem(), "Reservation_confirmed" , "예약 확정" );
		
		
		if(originR != null) {
			reservationRepository.save(updatedR);
		}
		
		return "redirect:/admin/management";
	}

	@PostMapping("/cancellReservation") 
	public String cancellReservation(@RequestParam Long reservationId, RedirectAttributes rttr, Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		
		Reservation originR = reservationRepository.findById(reservationId).orElse(null);
		
		if(originR != null) {
			reservationRepository.delete(originR);
			rttr.addFlashAttribute("message", "Delete Success!!");
		}
		
		return "redirect:/admin/management";
	}

	@GetMapping("/checkReservation")
	public String getReservationTable(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    model.addAttribute("isLoggedIn",username);
		
	    
		return "reservation_table";
	}
	
}
