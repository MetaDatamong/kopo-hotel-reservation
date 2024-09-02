package kr.ac.kopo05.ctc.spring.board.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo05.ctc.spring.board.domain.UserItem;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long>, JpaSpecificationExecutor<UserItem>{

	boolean existsByUsername(String username);
    boolean existsByName(String name);
    
    Optional<UserItem> findByUsername(String username);
}
