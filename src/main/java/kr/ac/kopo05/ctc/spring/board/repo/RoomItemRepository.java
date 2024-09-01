package kr.ac.kopo05.ctc.spring.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.RoomItem;

@Repository
public interface RoomItemRepository extends JpaRepository<RoomItem, Long>, JpaSpecificationExecutor<RoomItem>{

}
