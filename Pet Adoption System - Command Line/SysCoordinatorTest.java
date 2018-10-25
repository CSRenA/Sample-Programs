import static org.junit.Assert.*;

import org.junit.Test;

public class SysCoordinatorTest {

	String[] test_input1 = {"1",
			"Erik Thompson",
	"ethompson2@gmail.com"};
	String[] test_input2 = {"359", 
			"Erik Thompson",
	"ethompson2@gmail.com"};
	String expected;
	String actual;

	AdoptionSystemCoordinator asc = new AdoptionSystemCoordinator();

	@Test
	public void testSelectTest1() 
	{
		//set-up test
		expected = ("==================SIGN UP SUCCESS==================\n"
				+ 	"User successfully signed up with following information:\n"
				+ 	"Name: "+ "Erik Thompson" +"\n"
				+ 	"Email: "+ "ethompson2@gmail.com" +"\n");
		//run test
		actual = asc.executeCommand(test_input1);
		//check results
		assertEquals(expected, actual);
	}

	@Test
	public void testSelectTest2() 
	{
		//set-up test
		expected = ("invalid input");
		//run test
		actual = asc.executeCommand(test_input2);
		//check results
		assertEquals(expected, actual);
	}

	public static void main(String[] args) 
	{
		junit.textui.TestRunner.run(SysCoordinatorTest.class);
	}

}
