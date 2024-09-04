package kr.ac.kopo05.ctc.spring.board.repo;

import java.util.List;
import static kr.ac.kopo05.ctc.spring.board.domain.QReservation.reservation;
import static kr.ac.kopo05.ctc.spring.board.domain.QRoomItem.roomItem;
import static kr.ac.kopo05.ctc.spring.board.domain.QUserItem.userItem
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.Reservation;


import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom{

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	@Override
	public List<Reservation> checkRA(Long roomId, String checkInDate, String checkOutDate) {
	    return jpaQueryFactory
                .selectFrom(reservation)
                .join(reservation.roomItem, roomItem)
                .where(
                		roomItem.roomId.eq(roomId)
                    .and(reservation.checkInDate.loe(checkInDate))
                    .and(reservation.checkOutDate.goe(checkOutDate))
                )
                .fetch();
	}
// join 후에 fetchJoin 잊지말기
	@Override
	public List<Reservation> searchByUserId(Long inputId) {
		 return jpaQueryFactory
		            .selectFrom(reservation)
		            .join(reservation.roomItem, roomItem).fetchJoin()
		            .join(reservation.userItem, userItem).fetchJoin()
		            .where(userItem.userId.eq(inputId))
		            .fetch();
	}
	@Override
	public List<Reservation> getAll() {
		return jpaQueryFactory
				.selectFrom(reservation)
		        .join(reservation.roomItem, roomItem).fetchJoin()
		        .join(reservation.userItem, userItem).fetchJoin()
		        .fetch();
	}
	
	@Override
	public List<Reservation> getReservationWR() {
		return jpaQueryFactory
				.selectFrom(reservation)
		        .join(reservation.roomItem, roomItem).fetchJoin()
		        .fetch();
	}
}
