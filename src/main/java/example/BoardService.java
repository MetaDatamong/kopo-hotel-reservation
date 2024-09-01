package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepository;

@Service
public class BoardService {

	@Autowired
    private BoardItemRepository boardItemRepository;

    public Page<BoardItem> getPosts(int page) {
        Pageable pageable = PageRequest.of(page - 1, 2); // 페이지 번호는 0부터 시작하기 때문에 page - 1
        return boardItemRepository.findAll(pageable);
    }
}
