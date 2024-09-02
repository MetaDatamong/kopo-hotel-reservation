package kr.ac.kopo05.ctc.spring.board.dto;

import jakarta.persistence.Entity;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;

public class JoinForm {
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String passwordCheck;
	
	private String phoneNum;
	
	private String type;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserItem toEntity() {
		return new UserItem(this.username, this.password, this.name, 
 				this.phoneNum, this.type);
	}
}
