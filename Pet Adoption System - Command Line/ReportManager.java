import java.util.ArrayList;

/**
 * Report Manager class will handle all the report processing of the program.
 * It will also update the classes themselves based on validation passes.
 * Report is built on a global StringBuilder object for now. May just print it later.
 * Gotta keep everything nice and updated.
 * @author Nabil Adam Benali
 */

public class ReportManager {
    private int month, day, year;
    private boolean testVal = false;
    private Admin admin;

    /**
     * Overloaded constructor to allow accessing other classes through Admin and generation of reports at date given.
     * @param admin is an instance of the Admin class to allow obtaining data from other classes, Admin
     */
    ReportManager(Admin admin) {
        this.admin = admin;
    }

    /**
     * validateRequests will handle verifying if the current requests are valid.
     */
    private void validateRequests() {
        /*
            We gon' check dat ish.
            valid Requests only.
         */
        if(admin.viewAdoptionReqs().isEmpty()) {
            this.testVal = true;
            return;
        }

        int date = (month*100)+day;
        ArrayList <AdoptionRequest> ar = admin.viewAdoptionReqs();
        ArrayList<Meeting> meets = admin.viewMeetings();
        for(int i = 0; i < ar.size(); i++) {
            int arMonth = Integer.parseInt(ar.get(i).getDateCreated().substring(0,2));
            int arDay = Integer.parseInt(ar.get(i).getDateCreated().substring(3, 5));
            int arDate = (arMonth*100)+arDay;
            long diff = date - arDate;
            if((diff >= 14) && (ar.get(i).getIsPending() || ar.get(i).getIsDenied())) {
                admin.removeRequest(ar.get(i));
                admin.removeMeeting(meets.get(i));
            }
        }
    }

    /**
     * updateMeetings will change meetings by calling the MeetingManager class and manipulating Meeting objects there.
     * @param meet is the value of the date being changed, Meeting
     * @return whether or not the values were updated successfully, boolean
     */
    boolean updateMeetings(Meeting meet) {
        try {
            MeetingManager m = new MeetingManager(admin);
            int i = admin.viewMeetings().indexOf(meet);
            if(admin.viewMeetings().get(i).getAccepted_meet().isEmpty()) return false;
            m.updateMeetings(meet.getAccepted_meet());
            return true;
        } catch(Exception e) { return false; }
    }

    /**
     * getDate converts int values for months into String representations.
     * @param month of the month to be converted to a String, int
     * @return the month associated with the int parameter, String
     */
    String getDate(int month) {
        // switch statement to change stuff. It is a lazy approach but it works.
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "NaN";
        }
    }

    /**
     * reportMonth generates a report for the month passed into the constructor.
     * @return the month report, String
     */
    String reportMonth(String date) {
        this.month = Integer.parseInt(date.substring(0, 2));
        this.year = Integer.parseInt(date.substring(3, 5));
        ArrayList<AdoptionRequest> ar = admin.viewAdoptionReqs();
        ArrayList<Meeting> m = admin.viewMeetings();
        AdoptionRequest req;
        int pendReview = 0, pendResponse = 0, totalAdopted = 0;
        for (int i = 0; i < ar.size(); i++) {
            req = ar.get(i);
            if (req.getIsApproved()) {
                totalAdopted++;
            }
            pendReview++;
        }
        for (int i = 0; i < m.size(); i++) {
            if (!(m.get(i).getAccepted_meet().isEmpty())) pendResponse++;
        }
        String month = getDate(this.month);
        if(month.equals("NaN")) return "===============REPORT - FOR A MONTH================\nInvalid date!!!\n";
        return String.format("===============REPORT - FOR A MONTH================\n" +
                        "Month : %s, 20%d\n" +
                        "Request State\t\t\t\tCount\n" +
                        "---------------------------------------------------\n" +
                        "Pending Review:\t\t\t\t%d\n" +
                        "Pending Response:\t\t\t%d\n" +
                        "Total adopted:\t\t\t\t%d\n",
                month, this.year, pendReview, pendResponse, totalAdopted);
    }

    /**
     * reportWeek generates a message that two week old pending requests have been deleted.
     * @return the week report, String
     */
    public String reportWeek(String date) {
        try {
            this.month = Integer.parseInt(date.substring(0, 2));
            this.day = Integer.parseInt(date.substring(3, 5));
            this.year = Integer.parseInt(date.substring(6));
            if (((month < 1) || (month > 12)) || ((day < 1) || (day > 31)) || ((year > 17) || (year < 16))) {
                return "=============TWO WEEKS AUTO NOT APPROVED===========\n" + "Invalid date!!!\n";
            }
            validateRequests();
            if(this.testVal) return "=============TWO WEEKS AUTO NOT APPROVED===========\nNo Pending Requests\n";

            return "=============TWO WEEKS AUTO NOT APPROVED===========\n" +
                    "All requests marked as 'pending response' for last two weeks\n" +
                    "since given date will be marked as 'not approved'\n";
        } catch(Exception e) {
            return "=============TWO WEEKS AUTO NOT APPROVED===========\n" + "Invalid date!!!\n";
        }
    }

    /**
     * reportMonthEnd generates the end-of-month report message by removing invalid requests.
     * @return the month end report, String
     */
    public String reportMonthEnd() {
        ArrayList<AdoptionRequest> ar = admin.viewAdoptionReqs();
        if(ar.isEmpty()) {
            return "================MONTH END UPDATE==================\n" +
                    "No requests to be cleaned from the system\n";
        }
        for(int i = 0; i <ar.size();i++) {
            if(ar.get(i).getIsCancelled())
                admin.removeRequest(ar.get(i));
        }
        return "================MONTH END UPDATE==================\n" +
                "All canceled requests will be cleaned from the system\n";
    }

    /**
     * reportYearEnd generates a year end report that all requests that are not approved are removed.
     * @return the year end report, String
     */
    public String reportYearEnd() {
        ArrayList<AdoptionRequest> ar = admin.viewAdoptionReqs();
        if(ar.isEmpty()) {
            return "=================YEAR END UPDATE==================\n" +
                    "No requests to be cleaned from the system\n";
        }
        for(int i = 0; i <ar.size();i++) {
            if(ar.get(i).getIsDenied() || ar.get(i).getIsPending())
                admin.removeRequest(ar.get(i));
        }
        return "=================YEAR END UPDATE==================\n" +
                "All not approved requests will be cleaned from the system\n";
    }

}