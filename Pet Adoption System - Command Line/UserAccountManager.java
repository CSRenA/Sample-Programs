import java.util.ArrayList;

/**
 * This class is a controller to manage user account creation.
 * @author Sean Perry
 *
 */
public class UserAccountManager {

	private Admin list;
	private ArrayList<User> userList;
	private String newName;
	private String newEmail;
	
	/**
	 * This method creates the Manager which houses a list of the users of the system, 
	 * updated from its storage in the "Admin" enitity
	 */
	public UserAccountManager(Admin users)
	{
		//will change to pulling the list from admin
		list = users;
	}
	
	/**
	 * This method is used for signal 1 of the program and creates a user object based on the input from the System Controller.
	 * @param name name passed in from the controller
	 * @param email email passed in from the controller
	 * @return A formatted string that reports the success or failure of creating a new account
	 */
	public String newUserAccount(String name, String email)
	{
		newName = name;
		newEmail = email;
		
		User newUser = new User(newName, newEmail);
		
		//pull in newest list of users from admin(overwrite the current userList)
		userList = list.viewUsers();
		
		//search user list for name and email of new object
		for (int i = 0; i < userList.size(); i++)
		{
			User testUser = userList.get(i);
			String testName = testUser.getName();
			String testEmail = testUser.getEmail();
			if (testName.equals(newName) )
				return ("==================SIGN UP FAILURE==================\n"
						+ "Username already exsits\n");
			if (testEmail.equals(newEmail) )
				return ("==================SIGN UP FAILURE==================\n"
						+ "Email address already exsits\n");
		}
		
		//if search passes(can not find name or email) insert the user in admin's user list
		list.insertUser(newUser);
		
		String output = ("==================SIGN UP SUCCESS==================\n"
				+ 	"User successfully signed up with following information:\n"
				+ 	"Name: "+ newName +"\n"
				+ 	"Email: "+ newEmail +"\n");
		
		return output;
	}

}
