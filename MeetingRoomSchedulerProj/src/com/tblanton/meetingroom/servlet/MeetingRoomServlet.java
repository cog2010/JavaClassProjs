package com.tblanton.meetingroom.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tblanton.meetingroom.model.MeetingDto;
import com.tblanton.meetingroom.service.MeetingRoomService;

public class MeetingRoomServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(MeetingRoomServlet.class);
	private MeetingRoomService meetingRoomService = new MeetingRoomService();
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String meetingList = "";
		String meetingDate = LocalDate.now().toString();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("passwd");
		logger.info("User Name is " + userName + " and password is " + password );
		meetingList = meetingRoomService.getCurrentMeetings(meetingDate);
		
		request.setAttribute("meetingList", meetingList);
				
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MeetingDto meeting = new MeetingDto();
		meeting.setMeetingName((String) request.getParameter("meeting-name"));
		String roomNumber = (String) request.getParameter("room-number");
		String occupantCount = (String) request.getParameter("occupant-count");
		String meetingDate = (String) request.getParameter("meeting-date");
		String meetingTime = (String) request.getParameter("meeting-time");
		String conferenceNumber = (String) request.getParameter("conference-call-number");
		String idToDelete = (String) request.getParameter("idToDelete");
		String meetingList = "test";
		

		if (idToDelete == null) {

			Long id = Long.parseLong(meetingRoomService.getId(meetingDate, meetingTime, roomNumber));
			
			String resultUrl = "/WEB-INF/success.jsp";
			if (meetingRoomService.isDuplicateMeeting(id)) {
				resultUrl = "/WEB-INF/duplicate.jsp";
			}
			
			meetingRoomService.enterNewMeeting(meeting);

			meetingList = meetingRoomService.getCurrentMeetings(meetingDate);
			meetingDate = meetingRoomService.getDisplayDate(meetingDate);
			

			request.setAttribute("id", id);
			request.setAttribute("meetingDate", meetingDate);
			request.setAttribute("meetingList", meetingList);

			request.getRequestDispatcher(resultUrl).forward(request, response);
			
		}else {
			Long id = Long.parseLong((String) request.getParameter("idToDelete"));

			meetingRoomService.deletMeeting(id);
			
			meetingDate = LocalDate.now().toString();
			meetingList = meetingRoomService.getCurrentMeetings(meetingDate);
			
			request.setAttribute("meetingList", meetingList);

			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} 

	}

}
