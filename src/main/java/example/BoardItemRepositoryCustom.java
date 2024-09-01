package example;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;

public interface BoardItemRepositoryCustom {

	List<BoardItem> selectAll();
	List<BoardItem> selectAll(Pageable pageable);
	List<BoardItem> selectComment(Long id);
	BoardItem selectById(Long id);
}
