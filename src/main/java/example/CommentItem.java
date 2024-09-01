package example;

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
public class CommentItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="board_item_id")
	@JsonBackReference
	private BoardItem boardItem;
	
	@Column
    private String content;
	
	@Column
    private String date;
	
	@Column
    private Long userId;
    
	private boolean editMode = false;

	    // Getters and Setters
	public boolean isEditMode() {
	     return editMode;
	 }

	public void setEditMode(boolean editMode) {
	     this.editMode = editMode;
	  }
	
	public CommentItem(Long id, String content, String date, Long userdId, BoardItem boardItem) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.userId = userdId;
		this.boardItem = boardItem;
	}
	
	public CommentItem(String content, String date, Long userdId, BoardItem boardItem) {
		super();
		this.content = content;
		this.date = date;
		this.userId = userdId;
		this.boardItem = boardItem;
	}
	
	public CommentItem() {
		
	}
	
	public BoardItem getBoardItem() {
		return boardItem;
	}
	public void setBoardItem(BoardItem boardItem) {
		this.boardItem = boardItem;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public BoardItem getBoardItem() {
//		return boardItem;
//	}
//	public void setBoardItem(BoardItem boardItem) {
//		this.boardItem = boardItem;
//	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
