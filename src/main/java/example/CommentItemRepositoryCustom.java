package example;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.ac.kopo05.ctc.spring.board.domain.CommentItem;

public interface CommentItemRepositoryCustom {

	List<CommentItem> selectById(Long id);
	List<CommentItem> selectAll(Pageable pageable);
//	List<CommentItem> selectAllComment();
}
