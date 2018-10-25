import junit.framework.TestCase;
import junit.framework.*;


public class ReportManagerTests extends TestCase {

    // Test invalid date for monthly report
    public void testInvalidReportMonth() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.reportMonth("-1/10");
        String expected = "===============REPORT - FOR A MONTH================\nInvalid date!!!\n";
        assertEquals("Invalid date", expected, actual);
    }

    // Another invalid date for the weekly report
    public void testInvalidReportWeek() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.reportWeek("01/3/95");
        String expected = "=============TWO WEEKS AUTO NOT APPROVED===========\n" + "Invalid date!!!\n";
        assertEquals("Invalid date", expected, actual);
    }

    // Trying to update an empty meeting
    public void testEmptyMeeting() {
        Admin ad = new Admin();
        Meeting m = new Meeting();
        ad.insertMeeting(m);
        ReportManager rm = new ReportManager(ad);
        boolean actual = rm.updateMeetings(ad.viewMeetings().get(0));
        assertEquals("Invalid meeting", false, actual);
    }

    // invalid month: positive
    public void testInvalidMonthPositive() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.getDate(1000000000);
        assertEquals("Invalid month", "NaN", actual);
    }

    // invalid month: negative
    public void testInvalidMonthNegative() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.getDate(-2);
        assertEquals("Invalid month", "NaN", actual);
    }

    // empty requests for month
    public void testEmptyReportMonthEnd() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.reportMonthEnd();
        String expected = "================MONTH END UPDATE==================\n" +
                "No requests to be cleaned from the system\n";
        assertEquals("Empty month", expected, actual);
    }

    // empty requests for year
    public void testEmptyReportYearEnd() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.reportYearEnd();
        String expected = "=================YEAR END UPDATE==================\n" +
                "No requests to be cleaned from the system\n";
        assertEquals("Empty year", expected, actual);
    }

    // check the test value of validateRequests
    public void testValidateRequests() {
        Admin ad = new Admin();
        ReportManager rm = new ReportManager(ad);
        String actual = rm.reportWeek("10/14/17");
        String expected = "=============TWO WEEKS AUTO NOT APPROVED===========\nNo Pending Requests\n";
        assertEquals("No requests to validate", expected, actual);
    }
  public static void main(String[] args) {
  junit.textui.TestRunner.run(ReportManagerTests.class);
}
}
