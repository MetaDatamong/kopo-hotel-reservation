package example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.domain.CommentItem;

public interface CommentItemRepository extends JpaRepository<CommentItem, Long>, JpaSpecificationExecutor<CommentItem>{

}
