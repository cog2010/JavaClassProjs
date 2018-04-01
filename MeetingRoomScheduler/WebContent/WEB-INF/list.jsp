<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Meeting Room Scheduler</title>

<c:set var="date" />
<c:if test=>

</c:if>

<%
	String date = "";
	if ((String) request.getAttribute("meetingDate") == null) {
		date = LocalDate.now().toString();
	} else {
		date = (String) request.getAttribute("meetingDate");
	}
	String displayTime = "";
%>
</head>
<body>
	<h1>Meetings Scheduled for:</h1>
	<h2><%=date%></h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Meeting Name</th>
				<th>Meeting Date</th>
				<th>Meeting Time</th>
				<th>Room Number</th>
				<th>Number of Occupants</th>
				<th>Conference Call Number</th>
				<th>Delete?</th>
			</tr>
		</thead>
		<tbody>
			<%
				try {
					Class.forName("com.mysql.jdbc.Driver");

					String url = "jdbc:mysql://localhost/Starters?useSSL=false";
					Connection con = DriverManager.getConnection(url, "root", "password");

					PreparedStatement meetingScheduleByDate = null;
					ResultSet rs = null;

					meetingScheduleByDate = con.prepareStatement("SELECT * from meetings WHERE meetingDate=? ORDER BY  meetingTime, roomNumber");
					meetingScheduleByDate.setString(1, date);

					rs = meetingScheduleByDate.executeQuery();

					HashMap schedule = new HashMap();
					while (rs.next()) {
						response.setContentType("text/html");

						switch (rs.getInt("meetingTime")) {
						case 7:
							displayTime = "7:00am - 8:00am";
							break;
						case 8:
							displayTime = "8:00am - 9:00am";
							break;
						case 9:
							displayTime = "9:00am - 10:00am";
							break;
						case 10:
							displayTime = "10:00am - 11:00am";
							break;
						case 11:
							displayTime = "11:00am - 12:00pm";
							break;
						case 12:
							displayTime = "12:00pm - 1:00pm";
							break;
						case 13:
							displayTime = "1:00pm - 2:00pm";
							break;
						case 14:
							displayTime = "2:00pm - 3:00pm";
							break;
						case 15:
							displayTime = "3:00pm - 4:00pm";
							break;
						case 16:
							displayTime = "4:00pm - 5:00pm";
							break;
						case 17:
							displayTime = "5:00pm - 6:00pm";
							break;
						default:
							break;
						}
						;

						out.println("<tr><td>" + rs.getString("meetingName") + "</td><td>" + rs.getString("meetingDate")
								+ "</td><td>" + displayTime + "</td><td>" + rs.getInt("roomNumber") + "</td><td>"
								+ rs.getInt("occupantCount") + "</td><td>" + rs.getString("conferenceNumber")
								+ "</td><td><form method='POST' action='schedule-update'><button class='btn btn-danger' type='submit' name='idToDelete' value="
								+ rs.getLong("id") + ">Delete</button></form>" + "</td></tr>");

					}
					con.close();

				} catch (SQLException sqle) {
					sqle.printStackTrace();// not good!

				}
			%>
		</tbody>
	</table>
</body>
</html>