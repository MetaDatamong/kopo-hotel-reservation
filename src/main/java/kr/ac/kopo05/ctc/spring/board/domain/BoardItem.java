package kr.ac.kopo05.ctc.spring.board.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class BoardItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long boardId;
	@Column
	private String title;
	@Column
	private String date;
	@Column
	private String content;
	@Column
	private String boardType;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="user_item_id")
	@JsonBackReference
	private UserItem userItem;

	public BoardItem(String boardType, String title, 
			String today, String content, UserItem userItem) {
		this.boardType = boardType;
		this.title = title;
		this.date = today;
		this.content = content;
		this.userItem = userItem;
	}
	
	public BoardItem() {}

	public BoardItem(Long boardId, String boardType, String title, String today, String content, UserItem userItem) {
		this.boardId = boardId;
		this.boardType = boardType;
		this.title = title;
		this.date = today;
		this.content = content;
		this.userItem = userItem;
	}

	public Long getBoard_Id() {
		return boardId;
	}

	public void setBoard_Id(Long boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public UserItem getUserItem() {
		return userItem;
	}

	public void setUserItem(UserItem userItem) {
		this.userItem = userItem;
	}
	
}
