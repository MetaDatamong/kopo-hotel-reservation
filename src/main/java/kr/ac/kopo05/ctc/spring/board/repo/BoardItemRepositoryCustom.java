package kr.ac.kopo05.ctc.spring.board.repo;

import java.util.List;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.domain.Reservation;

public interface BoardItemRepositoryCustom {
	List<BoardItem> getAll();

	BoardItem searchByBoardId(Long boardId);
}

