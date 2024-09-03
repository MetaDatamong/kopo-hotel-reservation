package kr.ac.kopo05.ctc.spring.board.repo;

import java.util.List;
import static kr.ac.kopo05.ctc.spring.board.domain.QUserItem.userItem;
import static kr.ac.kopo05.ctc.spring.board.domain.QBoardItem.boardItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.domain.Reservation;


import com.querydsl.jpa.impl.JPAQueryFactory;
import static kr.ac.kopo05.ctc.spring.board.domain.QReservation.reservation;
import static kr.ac.kopo05.ctc.spring.board.domain.QRoomItem.roomItem;
import static kr.ac.kopo05.ctc.spring.board.domain.QUserItem.userItem;

@Repository
public class BoardItemRepositoryCustomImpl implements BoardItemRepositoryCustom{

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardItem> getAll() {
		return jpaQueryFactory
				.selectFrom(boardItem)
		        .join(boardItem.userItem, userItem).fetchJoin()
		        .fetch();
	}
	
	@Override
	public BoardItem searchByBoardId(Long boardId) {
		 return jpaQueryFactory
		            .selectFrom(boardItem)
		            .join(boardItem.userItem, userItem).fetchJoin()
		            .where(boardItem.boardId.eq(boardId))
		            .fetchFirst();
	}
}
