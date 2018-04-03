package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applogic.MeetingRoomService;

public class MeetingRoomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String meetingList = "";
		String meetingDate = LocalDate.now().toString();
		
		meetingList = MeetingRoomService.getCurrentMeetings(meetingDate);
		
		request.setAttribute("meetingList", meetingList);
				
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String meetingName = (String) request.getParameter("meeting-name");
		String roomNumber = (String) request.getParameter("room-number");
		String occupantCount = (String) request.getParameter("occupant-count");
		String meetingDate = (String) request.getParameter("meeting-date");
		String meetingTime = (String) request.getParameter("meeting-time");
		String conferenceNumber = (String) request.getParameter("conference-call-number");
		String idToDelete = (String) request.getParameter("idToDelete");
		String meetingList = "test";
		

		if (idToDelete == null) {

			Long id = Long.parseLong(MeetingRoomService.getId(meetingDate, meetingTime, roomNumber));
			
			String resultUrl = "/WEB-INF/success.jsp";
			if (MeetingRoomService.isDuplicateMeeting(id)) {
				resultUrl = "/WEB-INF/duplicate.jsp";
			}
			
			MeetingRoomService.enterNewMeeting(meetingName, roomNumber, occupantCount, meetingDate, meetingTime,
					conferenceNumber);

			meetingList = MeetingRoomService.getCurrentMeetings(meetingDate);
			meetingDate = MeetingRoomService.getDisplayDate(meetingDate);
			

			request.setAttribute("id", id);
			request.setAttribute("meetingDate", meetingDate);
			request.setAttribute("meetingList", meetingList);

			request.getRequestDispatcher(resultUrl).forward(request, response);
			
		}else {
			Long id = Long.parseLong((String) request.getParameter("idToDelete"));

			MeetingRoomService.deletMeeting(id);
			
			meetingDate = LocalDate.now().toString();
			meetingList = MeetingRoomService.getCurrentMeetings(meetingDate);
			
			request.setAttribute("meetingList", meetingList);

			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} 

	}

}
