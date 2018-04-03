import static org.junit.Assert.*;
 
import org.junit.Test;

public class MeetingRoomServiceTest {
	
	@Test
    public void testShouldReturnString201801017101() {
        String id = applogic.MeetingRoomService.getId("2018-01-01","7","101");
        assertEquals("201801017101", id);
    }
	
	@Test
    public void testShouldReturnFalse() {
        boolean id = applogic.MeetingRoomService.isDuplicateMeeting(201801017101L);
        assertFalse(id);
    }
	
	@Test
    public void testShouldReturnTrue() {
		applogic.MeetingRoomService.enterNewMeeting("test","101","1","2018-01-02","7",null);
        boolean id = applogic.MeetingRoomService.isDuplicateMeeting(201801027101L);
        assertTrue(id);
    }
	
	@Test
    public void testShouldReturnString01_01_2018() {
        String id = applogic.MeetingRoomService.getDisplayDate("2018-01-01");
        assertEquals("01-01-2018", id);
    }
	
	@Test
    public void testShouldReturnStringForTable() {
		applogic.MeetingRoomService.enterNewMeeting("Test","101","1","2018-01-03","7","8675309");
		String tableTest = "<tr><td>Test</td><td>7:00am - 8:00am</td><td>101</td><td>1</td><td>8675309</td><td><form method='POST' "
				+ "action='schedule-update'><button class='btn btn-danger' type='submit' "
				+ "name='idToDelete' value=201801037101>Delete</button></form></td></tr>";
        String tableResult = applogic.MeetingRoomService.getCurrentMeetings("2018-01-03");
        assertEquals(tableTest, tableResult);
    }
}
