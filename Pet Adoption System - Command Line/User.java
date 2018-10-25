/**
 * This class creates a user for the Pet Adoption System.
 * This class is an entity within the system.
 * @author Sean Perry
 */

public class User {

	private String name;
	private String email;
	private int numRequests;
	
	/**
	 * This constructs a user with a given name and email
	 * @param name the name of the user
	 * @param email the email of the user
	 * @param numRequests the number of active adoption requests
	 */
	public User(String iname, String iemail)
	{
		this.name = iname;
		this.email = iemail;
		this.numRequests = 0;
	}

	/**
	 * Standard getter method
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Standard setter method
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Standard getter method
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Standard setter method
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Standard getter method
	 */
	public int getNumRequests()
	{
		return numRequests;
	}

	/**
	 * Standard setter method
	 */
	public void setNumRequests(int numRequests)
	{
		this.numRequests = numRequests;
	}
	
	/**
	 * 
	 * @return true if the number of requests is less than 3. false if there are 3 requests
	 */
	public boolean checkNumLimit()
	{
		
		if(numRequests < 3) 
			return true;
		return false;
	}
}
