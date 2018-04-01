package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applogic.MeetingRoomService;

public class MeetingRoomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String meetingName = (String) request.getParameter("meeting-name");
		String roomNumber = (String) request.getParameter("room-number");
		String occupantCount = (String) request.getParameter("occupant-count");
		String meetingDate = (String) request.getParameter("meeting-date");
		String meetingTime = (String) request.getParameter("meeting-time");
		String conferenceNumber = (String) request.getParameter("conference-call-number");
		String idToDelete = (String) request.getParameter("idToDelete");

		if (idToDelete == null) {

			Long id = Long.parseLong(MeetingRoomService.getId(meetingDate, meetingTime, roomNumber));
			
			String resultUrl = "/WEB-INF/success.jsp";
			if (MeetingRoomService.isDuplicateMeeting(id)) {
				resultUrl = "/WEB-INF/duplicate.jsp";
			}
			
			MeetingRoomService.enterNewMeeting(meetingName, roomNumber, occupantCount, meetingDate, meetingTime,
					conferenceNumber);

			request.setAttribute("id", id);
			request.setAttribute("meetingDate", meetingDate);

			request.getRequestDispatcher(resultUrl).forward(request, response);
			
		}else {
			Long id = Long.parseLong((String) request.getParameter("idToDelete"));

			MeetingRoomService.deletMeeting(id);

			request.getRequestDispatcher("index.jsp").forward(request, response);
		} 

	}

}
