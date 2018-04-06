package com.tblanton.meetingroom.dao;

import java.util.List;

import com.tblanton.meetingroom.model.Meeting;

public interface MeetingDao {
	
	public void addMeetingToDb(Meeting meeting);
	public Meeting getMeetingFromDb(int meetingId);
	public void deleteMeetingFromDb(Meeting meeting);
	public List<Meeting> listOfMeetingsFromDb();

}
