package kr.ac.kopo05.ctc.spring.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;

@Service
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	private UserItemRepository userItemrepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserItem userItem = userItemrepository.findByUsername(username);
		
		if (null == userItem || !userItem.getPassword().equals(password)) {
			return null;
		}
		
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		
		if(userItem.isAdmin()) {
			grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return new MyAuthentication(username, password, grantedAuthorityList, userItem);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

class MyAuthentication extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1L;
	
	UserItem userItem;
	
	public MyAuthentication(String id, String password, List<GrantedAuthority> grantedAuthorityList, UserItem userItem) {
		super(id, password, grantedAuthorityList);
		this.userItem = userItem;
	}
}

