import junit.framework.TestCase;
import junit.framework.*;
import java.util.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class BankingSystemProxyTests extends TestCase {
  
  //Test verify method w/ invalid card number
  public static void testVerify1(){
    BankingSystemProxy bsp = new BankingSystemProxy();
    bsp.verify("2231 1232 232131 1232", "12/20");
    boolean actual = bsp.checkIfValid();
    assertEquals("Payment is invalid due to card number", false, actual);
  }
  //Test verify method w/ invalid expiration date
  public static void testVerify2(){
    BankingSystemProxy bsp = new BankingSystemProxy();
    bsp.verify("2343 2342 2342 4322", "17/20");
    boolean actual = bsp.checkIfValid();
    assertEquals("Payment is invalid due to card number", false, actual);
  }
  //Test check if valid
  public static void testCheckIfValid(){
    BankingSystemProxy bsp = new BankingSystemProxy();
    bsp.verify("2343 4234 8493 8393", "12/20");
    boolean actual = bsp.checkIfValid();
    assertEquals("Payment is valid!", true, actual);
  }

//Run all of the tests
public static void main(String[] args) {
  junit.textui.TestRunner.run(AdoptionRequestManagerTests.class);
}
}
