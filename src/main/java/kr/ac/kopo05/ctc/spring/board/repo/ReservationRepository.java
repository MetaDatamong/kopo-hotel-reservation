package kr.ac.kopo05.ctc.spring.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation>{

	
}
