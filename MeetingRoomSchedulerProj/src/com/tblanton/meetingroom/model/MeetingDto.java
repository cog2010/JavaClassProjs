package com.tblanton.meetingroom.model;

import org.apache.log4j.Logger;

import com.tblanton.meetingroom.servlet.MeetingRoomServlet;

public class MeetingDto {
	private static Logger logger = Logger.getLogger(MeetingDto.class);
	private String meetingName = "";
	private String meetingDate = "";
	private String meetingTime = "";
	private String roomNumber = "";
	private String occupantCount = "";
	private String conferenceNumber = "";
	private String id = "";
	
	
	public MeetingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MeetingDto(Meeting meeting) {
		super();
		this.meetingName = meeting.getMeetingName();
		this.meetingDate = meeting.getMeetingDate();
		this.meetingTime = Integer.toString(meeting.getMeetingTime());
		this.roomNumber = Integer.toString(meeting.getRoomNumber());
		this.occupantCount = Integer.toString(meeting.getOccupantCount());
		this.conferenceNumber = meeting.getConferenceNumber();
		this.id = Long.toString(meeting.getId());
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getOccupantCount() {
		return occupantCount;
	}
	public void setOccupantCount(String occupantCount) {
		this.occupantCount = occupantCount;
	}
	public String getConferenceNumber() {
		return conferenceNumber;
	}
	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "MeetingDto [meetingName=" + meetingName + ", meetingDate=" + meetingDate + ", meetingTime="
				+ meetingTime + ", roomNumber=" + roomNumber + ", occupantCount=" + occupantCount
				+ ", conferenceNumber=" + conferenceNumber + ", id=" + id + "]";
	}



}
