package kr.ac.kopo05.ctc.spring.board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
import kr.ac.kopo05.ctc.spring.board.dto.JoinForm;
import kr.ac.kopo05.ctc.spring.board.dto.LoginForm;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;

@Service
@Transactional
public class UserService {

	private final UserItemRepository userItemRepository;
	
	@Autowired
    public UserService(UserItemRepository userRepository) {
        this.userItemRepository = userRepository;
    }
	
	// id 중복 체크
	public boolean checkLoginIdDuplication(String username){
        return userItemRepository.existsByUsername(username);
    }
	// 닉네임 중복 체크
	public boolean checkNicknameDuplicate(String name) {
    return userItemRepository.existsByName(name);
	}
	// 회원가입
	public void join(JoinForm form) {
		 userItemRepository.save(form.toEntity());
	}
	
	// 로그인
	public UserItem login(LoginForm form) {
        Optional<UserItem> optionalUser = userItemRepository.findByUsername(form.getUsername());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        UserItem user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getPassword().equals(form.getPassword())) {
            return null;
        }

        return user;
    }
	
	
}
