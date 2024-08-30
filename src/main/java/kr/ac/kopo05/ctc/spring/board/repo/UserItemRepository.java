package kr.ac.kopo05.ctc.spring.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.UserItem;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long>, JpaSpecificationExecutor<UserItem>{
	UserItem findByUsername(String username);
}
