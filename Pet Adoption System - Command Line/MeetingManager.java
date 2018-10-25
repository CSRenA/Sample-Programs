import java.util.ArrayList;

/**
 * MeetingManager class will handle testing attributes of Meeting objects against request made.
 * Basically, makes sure that dates are valid for meetings and updates available dates.
 * @author Nabil Adam Benali
 */

public class MeetingManager {
    private Meeting newMeeting;
    private Admin admin;

    /**
     * Constructor for setting a specific meeting the Meeting Manager will be manipulating.
     */
    MeetingManager(Admin admin) {
        this.admin = admin;
    }

    /**
     * Overloaded constructor for manipulating a pre-existing Meeting
     * @param user is the user email associated with the meeting, String
     * @param petID is the ID of the pet being adopted, String
     * @param date is the date of the meeting, String
     * @param admin is an instance of Admin to allow viewing other classes, Admin
     */
    MeetingManager(String user, String petID, String date, Admin admin) {
        this.newMeeting = new Meeting(user, petID, date);
        this.admin = admin;
    }

    /**
     * Validation of the user setting the meeting occurs here. Need to ensure they are real.
     * @return value of whether they are a valid user or not, boolean
     */
    private boolean validUser() {
        ArrayList<User> u = admin.viewUsers();
        try{
            for(int i = 0; i < u.size(); i++) {
                if(u.get(i).getEmail().compareTo(newMeeting.getEmail()) == 0)
                    return true;
            }
            return false;
        } catch(Exception e) { return false; }
    }

    /**
     * Validation of the pet being adopted. Must be an existing pet up for adoption and available.
     * @return value of whether the pet is valid or not, boolean
     */
    private boolean validPet() {
        ArrayList<Pet> p = admin.viewPets();
        try {
            for(int i = 0; i < p.size(); i++) {
                if(p.get(i).getID().compareTo(newMeeting.getPetID()) == 0)
                    return true;
            }
            return false;
        } catch(Exception e) { return false; }
    }

    /**
     * Validation of the Date being chosen for the meeting. Must ensure the date is available.
     * @return value of whether the date is available or not, boolean
     */
    private boolean validDate() {
        ArrayList<String> dates = newMeeting.getMeeting_avail();
        for(int i = 0; i < dates.size(); i++)
            if (dates.get(i).compareTo(newMeeting.getAccepted_meet()) == 0) {
                return true;
            }
        return false;
        //return dates.stream().anyMatch(date1 -> date1.compareTo(newMeeting.getAccepted_meet()) == 0);
    }

    /**
     * updateMeetingTime takes a pre-existing Meeting and changes the time to a new date specified
     * @param user is the email of the user, String
     * @param petId is the ID of the pet, String
     * @param date is the date being changed, String
     * @return a message of the schedule meeting time, String
     */
    String updateMeetingTime(String user, String petId, String date) {
        try {
            if(!user.contains("@") || date.substring(0,4).contains("-")) {
                return "=============SCHEDULING MEETING TIME============\n" +
                        "Invalid Meeting to create\n";
            }
            ArrayList<Meeting> m = admin.viewMeetings();
            Meeting newMeet = new Meeting(user, petId, date);
            for (int i = 0; i < m.size(); i++) {
                if (m.get(i).getEmail().equals(user) && m.get(i).getPetID().equals(petId) && m.get(i).getAccepted_meet().equals(date)) {
                    admin.removeMeeting(m.get(i));
                    admin.insertMeeting(newMeet);
                    break;
                }
            }
            return "=============SCHEDULING MEETING TIME============\n" +
                    "A meeting has been scheduled with following details:\n" +
                    "User email: " + user + "\n" +
                    "Pet Id: " + petId + "\n" +
                    "Meeting time: " + date + "\n";
        } catch(Exception e) {
            return "=============SCHEDULING MEETING TIME============\n" +
                    "Meeting to change does not exist!\n";
        }
    }
    /**
     * updateMeetings will handle the actual manipulation of the meetings in the system.
     * It will delete items that are invalid.
     * @param date of the meeting being removed, String
     */
    void updateMeetings(String date) {
        try {
            Meeting m = new Meeting();
            ArrayList<String> meet = m.getMeeting_avail();
            meet.remove(date);
            m.setMeeting_avail(meet);
        } catch(Exception e) { e.printStackTrace(); }
    }

    /**
     * The Meeting object of this meeting request is built here.
     * Assuming all validations are passed, the meeting itself will be set here.
     * @return value of whether the meeting was successfully set, String
     */
    String createMeeting(String user, String petId, String date) {
        /*
            set newMeeting email = email
            set newMeeting petID = petID
            set newMeeting accepted_meet = accepted_meet
         */
        this.newMeeting = new Meeting(user, petId, date);
        if(!user.contains("@") || date.substring(0,4).contains("-")) {
            return "=============SETTING UP MEETING============\n" +
                    "Invalid Meeting to create\n";
        }
        this.validUser(); this.validDate(); this.validPet();
//        this.updateDate(user, petId, date);
        try {
            ArrayList<String> avail = this.newMeeting.getMeeting_avail();
            avail.remove(this.newMeeting.getAccepted_meet());
            this.newMeeting.setAccepted_meet(date);
            this.newMeeting.setMeeting_avail(avail);
        } catch(Exception e) {
            return "=============SETTING UP MEETING============\n" +
                "Invalid Meeting to create\n";
        }
        admin.insertMeeting(this.newMeeting);
        return "=============SETTING UP MEETING============\n" +
                "Meeting details sent to " + newMeeting.getEmail() + " for requesting " + newMeeting.getPetID() + ":\n" +
                "Possible times: " + newMeeting.getAccepted_meet() + "\n" +
                "Interview contact: sarah@rescuegroup.com\n";
    }
}