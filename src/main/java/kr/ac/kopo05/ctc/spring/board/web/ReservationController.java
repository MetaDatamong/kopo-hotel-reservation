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

import kr.ac.kopo05.ctc.spring.board.domain.Reservation;
import kr.ac.kopo05.ctc.spring.board.domain.RoomItem;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
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
	public String createNewForm() {
		return "reservation/booking_form";
		}
	
	@PostMapping("/checkAvailability")
    public String checkAvailability(@RequestParam Long roomId,
                                    @RequestParam String checkInDate,
                                    @RequestParam String checkOutDate,
                                    Model model) {
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
	public String booking(@RequestParam Long roomType,
            				@RequestParam String checkInDate,
            				@RequestParam String checkOutDate) {
		
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//임시
		UserItem userItem = userItemRepository.findById(1L).orElse(null);
		
		RoomItem roomItem = roomItemRepository.findById(roomType).orElse(null);
		
		//No request.ver
		Reservation r = new Reservation(dateFormat.format(today), checkInDate, checkOutDate, userItem, roomItem, "reservation_wait", "예약 대기");
		
		reservationRepository.save(r);
		
		//임시
		return "reservation/booking_form";
		}
	
	
	@PostMapping("/requestCancell") 
	public String requestCancell(@RequestParam Long reservationId) {
		
		
		Reservation originR = reservationRepository.findById(reservationId).orElse(null);
		

		Reservation updatedR = new Reservation(reservationId, originR.getReservationDate(), originR.getCheckInDate(), originR.getCheckOutDate(),
				originR.getUserItem(), originR.getRoomItem(), "cancell_wait" , "취소 대기" );
		
		System.out.println(updatedR.toString());
		
		if(originR != null) {
			reservationRepository.save(updatedR);
		}
		
		return "redirect:/bookDetail";
	}
	
	
	@GetMapping("/bookDetail")
	public String viewBookDetail(Model model) {
		
		//임시
		UserItem userItem = userItemRepository.findById(1L).orElse(null);
		
		List<Reservation> r = reservationRepositoryCustomImpl.searchByUserId(userItem.getUserId());
		
		model.addAttribute("reservation", r);

		return "reservation/book_detail";
	}
	
	
}
