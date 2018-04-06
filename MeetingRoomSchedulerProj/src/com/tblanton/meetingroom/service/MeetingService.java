package com.tblanton.meetingroom.service;

import java.util.List;

import com.tblanton.meetingroom.model.Meeting;

public interface MeetingService {
	
	public void createMeeting(Meeting meeting);
	public Meeting readMeeting(Meeting meeting);
	public List<Meeting> list();
	public void updateMeeting(Meeting meeting);	
	public void deleteMeeting(Meeting meeting);

}
