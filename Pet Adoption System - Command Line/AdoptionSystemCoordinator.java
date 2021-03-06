/**
 * This class is the controller that selects what controller handles what input from the user.
 * @author Sean Perry
 *
 */
public class AdoptionSystemCoordinator {

	private String[] input;
	private Admin arrays;
	private UserAccountManager userMngr;
	private MeetingManager meetMngr;
	private PetManager petMngr;
	private AdoptionRequestManager adoptMngr;
	private ReportManager repMngr;
	private String currentDate = "01/01/17";
	private int Month = 1;
	private int Day = 1;
	private int Year = 2017;
	
	/**
	 * This Constructor creates the System Coordinator by creating each of the child coordinators, and by creating an input array.
	 */
	public AdoptionSystemCoordinator()
	{
		//create admin entity
		this.arrays = new Admin();
		//Created with all business logic controllers with admin as input
		this.userMngr = new UserAccountManager(arrays);
		this.petMngr = new PetManager(arrays);
		this.meetMngr = new MeetingManager(arrays);
		this.adoptMngr = new AdoptionRequestManager(arrays);
		this.repMngr = new ReportManager(arrays);
	}
	
	/**
	 * This method selects a controller to call using the 0 element of the array as selection criteria
	 * @param command the array passed to the controller by the PetSystem class
	 * @return The string that contains the output of the correct child controller
	 */
	public String executeCommand(String[] command)
	{
		//update the input array
		this.input = command;
		int inputInt = Integer.parseInt(command[0]);
		
		//declare of all possible inputs
		String userName;
		String email;
		String petID;
		String petName;
		String petPic;
		String petDesc;
		String phoneNum;
		String address;
		String leaseRules;
		String creditCard;
		String creditExp;
		String datesAvail;
		String dateSelect;
		String newDate;
		String approve;
		
		String outputString;
		//use input[0] to select the correct controller's method call
        switch (inputInt) {
        case 1: //creating a new user
        	userName = command[1];
        	email = command[2];
        	outputString = userMngr.newUserAccount(userName, email);
            break;
        case 2: //creating new pet
        	petID = command[1];
        	petName = command[2];
        	petPic = command[3];
        	petDesc = command[4];
        	outputString = petMngr.addPet(petID, petName, petPic, petDesc);
            break;
        case 3:  //view all available pets
        	outputString = petMngr.viewAnimals();
            break;
        case 4:  //create new adoption request
        	email = command[1];
        	petID = command[2];
        	phoneNum = command[3];
        	address = command[4];
        	leaseRules = command[5];
        	creditCard = command[6];
        	creditExp = command[7];
        	outputString = adoptMngr.makeRequest(email, petID, phoneNum, address, leaseRules, creditCard, creditExp, currentDate);
            break;
        case 5:  //request status of all requests for given email
        	email = command[1];
        	outputString = adoptMngr.viewRequestStatus(email);
            break;
        case 6:  //request status of all requests for given pet ID
        	petID = command[1];
        	outputString = adoptMngr.reviewRequestStatus(petID);
            break;
        case 7:  //update status of pet and adoption request
        	email = command[1];
        	petID = command[2];
        	approve = command[3];
        	String approveString = "Approved";
        	String notapprveString = "Not Approved";
        	
        	if (approve.equals(approveString))
        	{
        		outputString = adoptMngr.approveRequest(adoptMngr.getRequest(email, petID));
        	}
        	else if (approve.equals(notapprveString))
        	{
        		outputString = adoptMngr.denyRequest(adoptMngr.getRequest(email, petID));
        	}
        	else
        	outputString = "Invalid Input for approve/deny";
            break;
        case 8:  //create new meeting
        	email = command[1];
        	petID = command[2];
        	datesAvail = command[3];
        	outputString = meetMngr.createMeeting(email, petID, datesAvail);
            break;
        case 9:  //select a time for a meeting
        	email = command[1];
        	petID = command[2];
        	dateSelect = command[3];
        	outputString = meetMngr.updateMeetingTime(email, petID, dateSelect);
            break;
        case 10: //generate a report for a given month and advance the date to given month
        	newDate = command[1];
        	outputString = repMngr.reportMonth(newDate);
        	//calculate new Date
        	String MonthString = newDate.substring(0, 2);
        	Day = 1;
        	//update date to new month
        	Month = Integer.parseInt(MonthString);
        	currentDate = (Month + "/" + Day + "/" + Year);
            break;
        case 11: //view details of a given pet ID
        	petID = command[1];
        	outputString = petMngr.viewPet(petID);
            break;
        case 12: //generate a report for the end of the month and advance date to next month
        	outputString = repMngr.reportMonthEnd();
           	//move date to 1st of next month
        	Day = 1;
        	Month += 1;
        	//move to new date
        	currentDate = (Month + "/" + Day + "/" + Year);
            break;
        case 13: //generate a report for the end of the year and advance date to next year
        	outputString = repMngr.reportYearEnd();
        	//move date to 1st of Jan next year
        	Day = 1;
        	Month = 1;
        	Year += 1;
        	//move to new date
        	currentDate = (Month + "/" + Day + "/" + Year);
        	break;
        case 14: //cancel an adoption request by the user
        	email = command[1];
        	petID = command[2];
        	outputString = adoptMngr.cancelRequest(adoptMngr.getRequest(email, petID));
        	break;
        case 15: //advance to a given day within the current month
        	newDate = command[1];
        	//outPutString = repMngr.moveToDate(newDate);
        	outputString = repMngr.reportWeek(newDate);
        	//move to new date
        	String DayString = newDate.substring(3, 5);
        	Day = Integer.parseInt(DayString);
        	currentDate = (Month + "/" + Day + "/" + Year);
        	break;
        default: 
        	outputString = "invalid input";
            break;
    }
		
		
		return outputString;
	}

}
