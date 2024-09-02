package kr.ac.kopo05.ctc.spring.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long userId;
	
	@Column
	private String name;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String type;
	
	@Column
	private String phoneNum;
	
	public UserItem(String username, String password, String name, String phoneNum, String type) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNum = phoneNum;
		this.type = type;
	}
	
	public UserItem() {}

	public boolean isAdmin() {
		return this.type.equals("ROLE_ADMIN");
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "UserItem [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", type=" + type + ", phoneNum=" + phoneNum + "]";
	}
	
	
	
}
