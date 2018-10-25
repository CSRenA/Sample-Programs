import junit.framework.TestCase;
import junit.framework.*;
import java.util.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class AdoptionRequestManagerTests extends TestCase {
  
  //Make Request Tests
  
  //Test too many requests
  
  public void testMakeRequest1(){//WORKS
    Admin admin = new Admin();
    User u = new User("JimBob Blue", "jimbob@canada.com");
    u.setNumRequests(3);
    admin.insertUser(u);
    Pet p = new Pet("P1234","Pete","Pete.png","Pete was previously named Courage and lived with Eustace and Muriel.");
    admin.insertPet(p);
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    String actual = ar.makeRequest(u.getEmail(),p.getID(),"8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 1234", "12/20","11/25/17");
    String expected = "A User cannot exceed 3 adoption requests. User jimbob@canada.com already has 3 requests.";
    assertEquals("User Should Have Too Many Requests", expected, actual);
  }
  
  //Test user does not exist.
  
  public void testMakeRequest2(){//WORKS
    Admin admin2 = new Admin();
    Pet p = new Pet("P1234","Pete","Pete.png","Pete was previously named Courage and lived with Eustace and Muriel.");
    admin2.insertPet(p);
    AdoptionRequestManager ar = new AdoptionRequestManager(admin2);
    String actual = ar.makeRequest("billy@canada.com",p.getID(),"8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 1234", "12/20","11/25/17");
    String expected = "This user does not exist! Please create an account for email billy@canada.com before attempting to create an adoption request.";
    assertEquals("User Doesn't Exist", expected, actual);
  }
  
  //Test pet does not exist.
  
  public void testMakeRequest3(){
    Admin admin = new Admin();
    User u = new User("JimBob Blue", "jimbob@canada.com");
    u.setNumRequests(3);
    admin.insertUser(u);
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    String actual = ar.makeRequest(u.getEmail(),"P1234","8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 1234", "12/20","11/25/17");
    String expected = "invalid pet information for adoption request with petID: P1234\n";
    assertEquals("Pet Doesn't Exist", expected, actual);
  }
  
  //Test invalid payments.
  
  public void testMakeRequest4(){
    Admin admin = new Admin();
    User u = new User("JimBob Blue", "jimbob@canada.com");
    admin.insertUser(u);
    Pet p = new Pet("P1234","Pete","Pete.png","Pete was previously named Courage and lived with Eustace and Muriel.");
    admin.insertPet(p);
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    String actual = ar.makeRequest(u.getEmail(),p.getID(),"8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 12345", "12/20","11/25/17");
    String expected = "invalid payment information for adoption request with email: jimbob@canada.com and petID: P1234\n";
    assertEquals("Payments are Invalid", expected, actual);
  }
  
  //Test already adopted pet
  
  public void testMakeRequest5(){
    Admin admin = new Admin();
    User u = new User("JimBob Blue", "jimbob@canada.com");
    admin.insertUser(u);
    Pet p = new Pet("P1234","Pete","Pete.png","Pete was previously named Courage and lived with Eustace and Muriel.");
    p.markAsAdopted();
    admin.insertPet(p);
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    String actual = ar.makeRequest(u.getEmail(),p.getID(),"8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 1234", "12/20","11/25/17");
    String expected = "Pet with ID P1234 has already been adopted!\n";
    assertEquals("Pet was already adopted", expected, actual);
  }
    
  
  //Test getStatus, will only fail if null.
  
  public void testGetStatus(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    try{
    String actual = ar.getStatus(null);
    String expected = "input error";
    assertEquals("Adoption Request doesn't exist.", expected, actual);
    }
    catch(NullPointerException e){
    }
  }
  //Get Requests User -- Never fails, will just return an empty arraylist if the user has no requests or doesn't exist.
  
  public void testGetRequestsUser(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    ArrayList<AdoptionRequest> arl= ar.getRequestsUser("email@doesntexist.com");
    int actual = arl.size();
    assertEquals("ArrayList is empty.", 0, actual);
  }
  
  //Get Requests Pet -- Never fails, will just return an empty arraylist if the animal has no requests or doesn't exist.
  
  public void testGetRequestsPet(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    ArrayList<AdoptionRequest> arl = ar.getRequestsPet("P9876");
    int actual = arl.size();
    assertEquals("ArrayList is empty.", 0, actual);
  }
  //Get Request Test
  
  public void testGetRequest(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    AdoptionRequest actual = ar.getRequest("email@doesntexist.com","P9876");
    assertEquals("Request Does Not Exist", null, actual);
  }
  
  //Pay Test
  
  public void testPay(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    boolean actual = ar.pay("1233 3452 5345 2342","17/17");
    assertEquals("Invalid Pay", false, actual);
  }
                         
  
  //Approve Request Test
  
  public void testApproveRequest(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    AdoptionRequest request = new AdoptionRequest("email@example.com","P1234","8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 12345", "12/20","11/25/17");
    String actual = ar.approveRequest(request);
    String expected = "This adoption request does not exist!\n";
    assertEquals("Can't approve nonexistent request", expected, actual);
  }
  
  //Deny Request Test
  
  public void testDenyRequest(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    AdoptionRequest request = new AdoptionRequest("email@example.com","P1234","8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 12345", "12/20","11/25/17");
    String actual = ar.denyRequest(request);
    String expected = "This adoption request does not exist!\n";
    assertEquals("Can't deny nonexistent request", expected, actual);
  }
  //Cancel Request Test
    
    public void testCancelRequest(){
    Admin admin = new Admin();
    AdoptionRequestManager ar = new AdoptionRequestManager(admin);
    AdoptionRequest request = new AdoptionRequest("email@example.com","P1234","8665666125","1234 Blah Street, Canada, USA 12345", "lease rules", "1234 1234 1234 12345", "12/20","11/25/17");
    String actual = ar.cancelRequest(request);
    String expected = "This adoption request does not exist!\n";
    assertEquals("Can't cancel nonexistent request", expected, actual);
  }
  //View Request Status Test
    
    public void testViewRequest(){
      Admin admin = new Admin();
      AdoptionRequestManager ar = new AdoptionRequestManager(admin);
      String actual = ar.viewRequestStatus("email@doesntexist.com");
      String expected = "==========VIEW ADOPTION REQUEST STATUS============\n"+"User email: email@doesntexist.com\n"+"Pet ID      STATUS\n"+"--------------------------\n";
      assertEquals("User has no requests.", expected, actual);
    }
  
  //Review Request Status Test
    
      public void testReviewRequest(){
      Admin admin = new Admin();
      AdoptionRequestManager ar = new AdoptionRequestManager(admin);
      String actual = ar.reviewRequestStatus("P1234");
      String expected = "=========REVIEW ADOPTION REQUEST STATUS===========\n"+"Following requests are pending with pet id P1234:\n"+
      "User email              Request details\n"+"---------------------------------------------------\n";
      assertEquals("Pet has no requests.", expected, actual);
      }
//Run all of the tests
public static void main(String[] args) {
  junit.textui.TestRunner.run(AdoptionRequestManagerTests.class);
}
}
