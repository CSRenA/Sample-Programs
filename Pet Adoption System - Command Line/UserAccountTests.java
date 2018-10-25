import static org.junit.Assert.*;

import org.junit.Test;

public class UserAccountTests {

	String test_name = "Tony Stark";
	String test_email = "IrnMan01@starktech.com";
	String expected;
	String actual;

	Admin admin = new Admin();
	UserAccountManager uam = new UserAccountManager(admin);

	@Test
	//expected to pass
	public void testNewUser1() 
	{
		//set-up for test
		expected = ("==================SIGN UP SUCCESS==================\n"
				+ 	"User successfully signed up with following information:\n"
				+ 	"Name: "+ test_name +"\n"
				+ 	"Email: "+ test_email +"\n");
		//run test
		actual = uam.newUserAccount(test_name, test_email);
		//verify test success/failure
		assertEquals(expected, actual);
	}

	@Test
	//expected to pass by showing (user name) as a failure
	public void testNewUser2() 
	{
		//set-up for test
		expected = "==================SIGN UP FAILURE==================\n"
				+ "Username already exsits\n";
		uam.newUserAccount("Tony Stark", "IrnMan02@starktech.com");
		//run test
		actual = uam.newUserAccount(test_name, test_email);
		//verify test success/failure
		assertEquals(expected, actual);
	}

	@Test
	//expected to fail to the user being in the list(email)
	public void testNewUser3() 
	{
		//set-up for test
		expected = "==================SIGN UP FAILURE==================\n"
				+ "Email address already exsits\n";
		uam.newUserAccount("James Rhodes", "IrnMan01@starktech.com");
		//run test
		actual = uam.newUserAccount(test_name, test_email);
		//verify test success/failure
		assertEquals(expected, actual);
	}

	public static void main(String[] args) 
	{
		junit.textui.TestRunner.run(UserAccountTests.class);
	}
}
