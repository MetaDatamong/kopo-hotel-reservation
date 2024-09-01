package example;

import static kr.ac.kopo05.ctc.spring.board.domain.QBoardItem.boardItem;
import static kr.ac.kopo05.ctc.spring.board.domain.QCommentItem.commentItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.kopo05.ctc.spring.board.domain.CommentItem;

@Repository
public class CommentItemRepositoryCustomImpl implements CommentItemRepositoryCustom{

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Override
	public List<CommentItem> selectById(Long id) {
		return jpaQueryFactory
				.selectFrom(commentItem)
				.where(boardItem.id.eq(id))
				.fetch();
	}

	@Override
	public List<CommentItem> selectAll(Pageable pageable) {
		return jpaQueryFactory
				.selectFrom(commentItem)
				.leftJoin(commentItem.boardItem, boardItem)
				.fetchJoin()
				.orderBy(commentItem.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
	}
	


}
