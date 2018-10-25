import java.util.ArrayList;

/**
 *  Meetings class creates Meetings object for the system.
 *  Meetings between the manager and client are scheduled here.
 *  @author Nabil Adam Benali
 */

public class Meeting {
    String email;
    private String petID;
    private ArrayList<String> meeting_avail;
    private String accepted_meet;

    /**
     * Inclusion of a null constructor for potential building of a Meeting object
     * outside of the class.
     */
    Meeting() {
        this(null, "", null);
    }

    /**
     * The Meeting overloaded constructor will set object fields equal to parameters.
     * Like any other constructor, really...
     * @param email of the user being met with, String
     * @param petID of the pet being adopted, String
     * @param accepted_meet is the date being accepted for, Date
     */
    Meeting(String email, String petID, String accepted_meet) {
        this.email = email;
        this.petID = petID;
        this.accepted_meet = accepted_meet;
        this.meeting_avail = new ArrayList<>();
    }

    /**
     * Getter for retriving private field email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for retriving private field petID
     * @return petID
     */
    public String getPetID() {
        return petID;
    }

    /**
     * Getter for retriving private field meeting_avail
     * @return array of available meeting dates
     */
    ArrayList<String> getMeeting_avail() {
        return meeting_avail;
    }

    /**
     * Getter for retriving private field accepted_meet
     * @return an array with accepted meeting dates
     */
    String getAccepted_meet() {
        return accepted_meet;
    }

    /**
     * Setter for email field
     * @param email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter for petID field
     * @param petID of pet being adopted
     */
    public void setPetID(String petID) {
        this.petID = petID;
    }

    /**
     * Setter for meeting_avail field
     * @param meeting_avail for available dates to meet
     */
    void setMeeting_avail(ArrayList<String> meeting_avail) {
        this.meeting_avail = meeting_avail;
    }

    /**
     * Setter for accepted_meet field
     */
    public void setAccepted_meet(String accepted_meet) {
        this.accepted_meet = accepted_meet;
    }

}