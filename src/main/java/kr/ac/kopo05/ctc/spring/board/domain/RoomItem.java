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
public class RoomItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long roomId;
	@Column
	private int pricePerNight;
	@Column
	private String grade;
	@Column
	private int roomNum;
	@Column
	private boolean roomAvailable;
	
	
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	@JoinColumn(name="reservation_id")
//	@JsonBackReference
//	private Reservation reservation;
//	
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public int getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(int pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public boolean isRoomAvailable() {
		return roomAvailable;
	}
	public void setRoomAvailable(boolean roomAvailable) {
		this.roomAvailable = roomAvailable;
	}
	
	@Override
	public String toString() {
		return "RoomItem{" +
				"roomId=" + roomId +
				", pricePerNight=" + pricePerNight +
				", grade='" + grade + '\'' +
				", roomNum=" + roomNum +
				", roomAvailable=" + roomAvailable +
				'}';
	}
}
