package example;



import static kr.ac.kopo05.ctc.spring.board.domain.QCommentItem.commentItem;
import static kr.ac.kopo05.ctc.spring.board.domain.QBoardItem.boardItem;
import static kr.ac.kopo05.ctc.spring.board.domain.QUserItem.userItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;

@Repository
public class BoardItemRepositoryCustomImpl implements BoardItemRepositoryCustom {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardItem> selectAll() {
		return jpaQueryFactory
				.selectFrom(boardItem)
				.leftJoin(boardItem.userItem, userItem)
				.fetchJoin()
				.fetch();
	}
	
	@Override
	public BoardItem selectById(Long id) {
		return jpaQueryFactory
				.selectFrom(boardItem)
				.leftJoin(boardItem.userItem, userItem)
				.fetchJoin()
				.where(boardItem.id.eq(id))
				.fetchFirst();
	}

	@Override
	public List<BoardItem> selectAll(Pageable pageable) {
		return jpaQueryFactory
				.selectFrom(boardItem)
				.leftJoin(boardItem.userItem, userItem)
				.fetchJoin()
				.orderBy(boardItem.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
	}

	@Override
	public List<BoardItem> selectComment(Long id) {
		return jpaQueryFactory
				.selectFrom(boardItem)
				//.leftJoin(boardItem.userItem, userItem).fetchJoin()
				.leftJoin(boardItem.commentItem, commentItem).fetchJoin()
				.where(boardItem.id.eq(id))
				.fetch();
	}
	
	

}
