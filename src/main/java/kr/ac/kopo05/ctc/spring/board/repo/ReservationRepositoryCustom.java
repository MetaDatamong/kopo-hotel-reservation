package kr.ac.kopo05.ctc.spring.board.repo;

import java.util.List;
import kr.ac.kopo05.ctc.spring.board.domain.Reservation;

public interface ReservationRepositoryCustom {
	List<Reservation> checkRA(Long roomId,String checkInDate, String checkOutDate);

	List<Reservation> searchByUserId(Long userId);
	
	List<Reservation> getAll();
}
