package example;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BoardItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
    private Long id;
		
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="user_item_id")
	@JsonBackReference
	private UserItem userItem;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "boardItem", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<CommentItem> commentItem;
	
	@Column
    private String title;
	@Column
    private String content;
	@Column
    private String date;


	public BoardItem(Long id, String title, String content, String date, UserItem userItem) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.userItem = userItem;
	}
	
	public BoardItem(String title, String content, String date, UserItem userItem) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
		this.userItem = userItem;
	}
	
	public BoardItem() {}
	
	
	public UserItem getUserItem() {
		return userItem;
	}
	public void setUserItem(UserItem userItem) {
		this.userItem = userItem;
	}
	public List<CommentItem> getCommentItem() {
		return commentItem;
	}
	public void setCommentItem(List<CommentItem> commentItem) {
		this.commentItem = commentItem;
	}
	public UserItem getBoardItem() {
		return userItem;
	}
	public void setBoardItem(UserItem userItem) {
		this.userItem = userItem;
	}
	public String getTitle() {
		return title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
