package applogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetingRoomService {

	public static void enterNewMeeting(String meetingName, String roomNum, String occupants, String meetingDate,
			String time, String conferenceNumber) {
		Connection con = null;
		PreparedStatement newMeetingStatement = null;

		long id = Long.parseLong(getId(meetingDate, time, roomNum));
		int roomNumber = Integer.parseInt(roomNum);
		int occupantCount = Integer.parseInt(occupants);
		int meetingTime = Integer.parseInt(time);
		
		if (isDuplicateMeeting(id) == true) {
			System.out.println("Duplicate Meeting!");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost/Starters?useSSL=false";
				con = DriverManager.getConnection(url, "root", "password");

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
				System.out.println("Meeting " + id + " successfully added!");
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();// not good!

			} catch (SQLException sqle) {
				sqle.printStackTrace();// not good!

			}
		}
	}

	

	public static String getId(String date, String time, String room) {
		String id = "";
		String parseDate = date.replace("-", "");
		id = parseDate + time + room;

		return id;
	}

	public static boolean isDuplicateMeeting(long id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			con = DriverManager.getConnection(url, "root", "password");

			stmt = con.prepareStatement("select id from meetings where id=? limit 1");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next() == true) {
				con.close();
				return true;
			}con.close();
			return false;
			

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}return false;
		
	}
	
	public static void deletMeeting(long id) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/Starters?useSSL=false";
			con = DriverManager.getConnection(url, "root", "password");

			stmt = con.prepareStatement("DELETE from meetings WHERE id=?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
			
			con.close();			
			System.out.println("Meeting " + id + " deleted!");

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();// not good!

		} catch (SQLException sqle) {
			sqle.printStackTrace();// not good!

		}
	}
}
