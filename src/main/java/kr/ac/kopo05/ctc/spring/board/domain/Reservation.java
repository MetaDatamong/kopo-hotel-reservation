package kr.ac.kopo05.ctc.spring.board.domain;

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
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long reservationId;
	
	@Column
	private String reservationDate;
	
	@Column
	private String request;
	
	@Column
	private String checkInDate;
	
	@Column
	private String checkOutDate;
	
	@Column
	private String state;
	
	@Column
	private String stateMessage;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="user_item_id")
	@JsonBackReference
	private UserItem userItem;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="room_item_id")
	@JsonBackReference
	private RoomItem roomItem;
	
	public Reservation(String curDate, String checkInDate, String checkOutDate, UserItem userItem, RoomItem roomItem,
			String state, String stateMessage) {
		this.reservationDate = curDate;
		this.request = "";
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.userItem = userItem;
		this.roomItem = roomItem;
		this.state = state;
		this.stateMessage = stateMessage;
	}
	
	public Reservation(Long reservationId, String curDate, String checkInDate, String checkOutDate, UserItem userItem, RoomItem roomItem,
			String state, String stateMessage) {
		this.reservationId = reservationId;
		this.reservationDate = curDate;
		this.request = "";
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.userItem = userItem;
		this.roomItem = roomItem;
		this.state = state;
		this.stateMessage = stateMessage;
	}
	
	
	public Reservation() {}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateMessage() {
		return stateMessage;
	}

	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", reservationDate=" + reservationDate + ", request="
				+ request + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", userItem=" + userItem
				+ ", roomItem=" + roomItem + "]";
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public UserItem getUserItem() {
		return userItem;
	}

	public void setUserItem(UserItem userItem) {
		this.userItem = userItem;
	}

	public RoomItem getRoomItem() {
		return roomItem;
	}

	public void setRoomItem(RoomItem roomItem) {
		this.roomItem = roomItem;
	}

}
