import junit.framework.TestCase;
import junit.framework.*;


public class MeetingManagerTests extends TestCase {

    // Test invalid meeting
    public void testUpdateEmptyMeeting() {
        Admin ad = new Admin();
        MeetingManager mm = new MeetingManager(ad);
        Meeting m = new Meeting();
        ad.insertMeeting(m);
        String actual = mm.updateMeetingTime(m.getEmail(), m.getPetID(), m.getAccepted_meet());
        String expected = "=============SCHEDULING MEETING TIME============\n" +
                "Meeting to change does not exist!\n";
        assertEquals("Invalid date", expected, actual);
    }

    // invalid meeting information to create
    public void testCreateInvalidMeeting() {
        Admin ad = new Admin();
        MeetingManager mm = new MeetingManager(ad);
        Meeting m = new Meeting("uhdg", "P3030303", "-1/03/19");
        ad.insertMeeting(m);
        String actual = mm.createMeeting(m.getEmail(), m.getPetID(), m.getAccepted_meet());
        String expected = "=============SETTING UP MEETING============\n" +
                "Invalid Meeting to create\n";
        assertEquals("Invalid meeting", expected, actual);
    }
    public static void main(String[] args) {
  junit.textui.TestRunner.run(MeetingManagerTests.class);
}
}
