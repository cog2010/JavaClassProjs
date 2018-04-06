package com.tblanton.meetingroom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.tblanton.meetingroom.model.Meeting;

public class MeetingDaoImpl implements MeetingDao {
	private static final Logger logger = Logger.getLogger(MeetingDaoImpl.class);
	
	@Override
	public void addMeetingToDb(Meeting meeting) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			Connection con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			PreparedStatement newMeetingStatement = con.prepareStatement("INSERT INTO meetings "
					+ "(id, meetingname, meetingdate, meetingtime, roomnumber, occupantcount, conferencenumber)"
					+ " VALUES (?,?,?,?,?,?,?)");

			newMeetingStatement.setLong(1, meeting.getId());
			newMeetingStatement.setString(2, meeting.getMeetingName());
			newMeetingStatement.setString(3, meeting.getMeetingDate());
			newMeetingStatement.setInt(4, meeting.getMeetingTime());
			newMeetingStatement.setInt(5, meeting.getRoomNumber());
			newMeetingStatement.setInt(6, meeting.getOccupantCount());
			newMeetingStatement.setString(7, meeting.getConferenceNumber());

			newMeetingStatement.executeUpdate();

			con.close();
			logger.trace("Connection Closed");
			logger.info("Meeting " + meeting.getId() + " successfully added!");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}

	}

	//This will be used to select a meeting for updating and such
	@Override
	public Meeting getMeetingFromDb(int meetingId) {
		Meeting meeting = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			Connection con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			PreparedStatement selectMeetingStatement = con.prepareStatement("SELECT * FROM meetings WHERE id=? LIMIT 1");

			selectMeetingStatement.setInt(1, meetingId);
			ResultSet rs = selectMeetingStatement.executeQuery();
			
			meeting = new Meeting();
			meeting.setId(rs.getInt("id"));
			meeting.setMeetingName(rs.getString("meetingName"));
			meeting.setMeetingDate(rs.getString("meetingDate"));
			meeting.setMeetingTime(rs.getInt("meetingTime"));
			meeting.setRoomNumber(rs.getInt("roomNumber"));
			meeting.setOccupantCount(rs.getInt("occupantCount"));
			meeting.setConferenceNumber(rs.getString("conferenceNumber"));

			con.close();
			logger.trace("Connection Closed");
			
			
			logger.info("Meeting " + meetingId + " selected!");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}
		
		return meeting;
	}


	@Override
	public void deleteMeetingFromDb(Meeting meeting) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			stmt = con.prepareStatement("DELETE from meetings WHERE id=?");
			stmt.setLong(1, meeting.getId());
			stmt.executeUpdate();

			con.close();
			logger.trace("Connection Closed");
			logger.info("Meeting " + meeting.getId() + " deleted!");

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}

	}

	@Override
	public List<Meeting> listOfMeetingsFromDb() {
		Connection con = null;
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			con = DriverManager.getConnection(url, "root", "password");
			logger.trace("Connection Established");

			stmt = con.prepareStatement("SELECT * from meetings");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setConferenceNumber(rs.getString("conferenceNumber"));
				meeting.setMeetingDate(rs.getString("meetingDate"));
				meeting.setMeetingName(rs.getString("meetingName"));
				meeting.setMeetingTime(rs.getInt("meetingTime"));
				meeting.setOccupantCount(rs.getInt("occupantCount"));
				meeting.setRoomNumber(rs.getInt("roomNumber"));
				
				meetings.add(meeting);
			}

			con.close();
			logger.trace("Connection Closed");
			logger.trace("Added to meetings " + meetings.size() + " meetings.");

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}
		return meetings;
	}

}
