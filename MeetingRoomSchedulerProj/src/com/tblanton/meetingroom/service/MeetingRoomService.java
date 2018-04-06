package com.tblanton.meetingroom.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tblanton.meetingroom.model.MeetingDto;

public class MeetingRoomService {
	private static Logger logger = Logger.getLogger(MeetingRoomService.class);
	public void enterNewMeeting(MeetingDto meeting) {
		enterNewMeeting(meeting.getMeetingName(), meeting.getRoomNumber(),meeting.getOccupantCount(), 
				meeting.getMeetingDate(), meeting.getMeetingTime(), meeting.getConferenceNumber());
		logger.trace("MeetingDto on " + meeting.getMeetingDate());
	}
	public void enterNewMeeting(String meetingName, String roomNum, String occupants, String meetingDate,
		String time, String conferenceNumber) {
		Connection con = null;
		PreparedStatement newMeetingStatement = null;

		long id = Long.parseLong(getId(meetingDate, time, roomNum));
		int roomNumber = Integer.parseInt(roomNum);
		int occupantCount = Integer.parseInt(occupants);
		int meetingTime = Integer.parseInt(time);

		if (isDuplicateMeeting(id) == true) {
			logger.info("Duplicate Meeting!");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost/Starters?useSSL=false";
				con = DriverManager.getConnection(url, "root", "password");
				logger.trace("Connection Established");

				newMeetingStatement = con.prepareStatement("INSERT INTO meetings "
						+ "(id, meetingname, meetingdate, meetingtime, roomnumber, occupantcount, conferencenumber)"
						+ " VALUES (?,?,?,?,?,?,?)");

				newMeetingStatement.setLong(1, id);
				newMeetingStatement.setString(2, meetingName);
				newMeetingStatement.setString(3, meetingDate);
				newMeetingStatement.setInt(4, meetingTime);
				newMeetingStatement.setInt(5, roomNumber);
				newMeetingStatement.setInt(6, occupantCount);
				newMeetingStatement.setString(7, conferenceNumber);

				newMeetingStatement.executeUpdate();

				con.close();
				logger.trace("Connection Closed");
				logger.info("Meeting " + id + " successfully added!");
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();// not good!

			} catch (SQLException sqle) {
				sqle.printStackTrace();// not good!

			}
		}
	}

	public String getId(String date, String time, String room) {
		String id = "";
		String parseDate = date.replace("-", "");
		id = parseDate + time + room;

		logger.trace("Id " + id + "created");
		return id;
	}

	public boolean isDuplicateMeeting(long id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			stmt = con.prepareStatement("select id from meetings where id=? limit 1");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next() == true) {
				con.close();
				return true;
			}
			con.close();
			return false;

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}
		return false;

	}

	public void deletMeeting(long id) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			stmt = con.prepareStatement("DELETE from meetings WHERE id=?");
			stmt.setLong(1, id);
			stmt.executeUpdate();

			con.close();
			logger.trace("Connection Closed");
			logger.info("Meeting " + id + " deleted!");

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}
	}

	public String getDisplayDate(String inputDate) {
		String displayDate = "";
		String year;
		String month;
		String day;

		year = inputDate.substring(0, 4);
		month = inputDate.substring(5, 7);
		day = inputDate.substring(8);

		displayDate = month + "-" + day + "-" + year;

		return displayDate;
	}

	public String getCurrentMeetings(String date) {

		String currentMeetings = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			Connection con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			PreparedStatement meetingScheduleByDate = null;
			ResultSet rs = null;

			meetingScheduleByDate = con
					.prepareStatement("SELECT * from meetings WHERE meetingDate=? ORDER BY  meetingTime, roomNumber");
			meetingScheduleByDate.setString(1, date);

			rs = meetingScheduleByDate.executeQuery();
			String displayTime = "";

			while (rs.next()) {

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
				//Do Something With This Block
				currentMeetings += "<tr><td>" + rs.getString("meetingName") + "</td><td>" + displayTime
						+ "</td><td>" + rs.getInt("roomNumber") + "</td><td>" + rs.getInt("occupantCount") 
						+ "</td><td>" + rs.getString("conferenceNumber") + "</td><td><form method='POST' "
						+ "action='schedule-update'><button class='btn btn-danger' type='submit' "
						+ "name='idToDelete' value=" + rs.getLong("id") + ">Delete</button></form>" + "</td></tr>";

			}
			con.close();
			logger.trace("Connection Closed");

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}
		return currentMeetings;
	}
}
