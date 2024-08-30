package kr.ac.kopo05.ctc.spring.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;

@Repository
public interface Reservation extends JpaRepository<BoardItem, Long>, JpaSpecificationExecutor<BoardItem>{

}
