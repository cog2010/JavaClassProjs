package com.tblanton.meetingroom.model;

public class Meeting {
	private String meetingName = "";
	private String meetingDate = "";
	private int meetingTime = 0;
	private int roomNumber = 0;
	private int occupantCount = 0;
	private String conferenceNumber = "";
	private int id = 0;
	

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

	public int getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(int meetingTime) {
		this.meetingTime = meetingTime;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getOccupantCount() {
		return occupantCount;
	}

	public void setOccupantCount(int occupantCount) {
		this.occupantCount = occupantCount;
	}

	public String getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conferenceNumber == null) ? 0 : conferenceNumber.hashCode());
		result = prime * result + id;
		result = prime * result + ((meetingDate == null) ? 0 : meetingDate.hashCode());
		result = prime * result + ((meetingName == null) ? 0 : meetingName.hashCode());
		result = prime * result + meetingTime;
		result = prime * result + occupantCount;
		result = prime * result + roomNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		if (conferenceNumber == null) {
			if (other.conferenceNumber != null)
				return false;
		} else if (!conferenceNumber.equals(other.conferenceNumber))
			return false;
		if (id != other.id)
			return false;
		if (meetingDate == null) {
			if (other.meetingDate != null)
				return false;
		} else if (!meetingDate.equals(other.meetingDate))
			return false;
		if (meetingName == null) {
			if (other.meetingName != null)
				return false;
		} else if (!meetingName.equals(other.meetingName))
			return false;
		if (meetingTime != other.meetingTime)
			return false;
		if (occupantCount != other.occupantCount)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meeting [meetingName=" + meetingName + ", meetingDate=" + meetingDate + ", meetingTime=" + meetingTime
				+ ", roomNumber=" + roomNumber + ", occupantCount=" + occupantCount + ", conferenceNumber="
				+ conferenceNumber + ", id=" + id + "]";
	}

}
