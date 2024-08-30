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
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="user_item_id")
	@JsonBackReference
	private UserItem userItem;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="room_item_id")
	@JsonBackReference
	private RoomItem roomItem;
}
