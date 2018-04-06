package com.tblanton.meetingroom.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.tblanton.meetingroom.model.Meeting;
import com.tblanton.meetingroom.model.MeetingDto;

public class MeetingServiceImpl implements MeetingService {
	private static final Logger logger = Logger.getLogger(MeetingServiceImpl.class);
	MeetingDto meetingDto = new MeetingDto();

	@Override
	public void createMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		logger.trace("I Made A Meeting!");

	}

	@Override
	public Meeting readMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMeeting(Meeting meeting) {
		// TODO Auto-generated method stub

	}

}
