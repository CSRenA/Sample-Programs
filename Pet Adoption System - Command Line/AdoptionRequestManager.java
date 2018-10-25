import java.util.*;
/**The AdoptionRequestManager class creates and changes adoption requests. It also interfaces with the banking system proxy to make sure that a user has paid their $10 fee before creating an adoption request.
  * @author Lauren Anderson
  * */
public class AdoptionRequestManager{
  /** Holds the string that will be displayed to the user. The displayString will be passed to the coordinator for display.*/
  private Admin admin;
  /**The AdoptionRequestManager constructor. Does not have any fields inputted upon creation. Only one of these will be created in the coordinator.
    * @param admin Admin object containing list of all objects*/
  public AdoptionRequestManager(Admin admin){
    this.admin = admin;
  }
  /**This method creates an adoption request. Adoption requests will only be created in this class using this method.
    * @param email User's email.
    * @param petID A pet ID for a pet that must already be in the system. 
    * @param phoneNumber A user's phone number
    * @param address A user's full address
    * @param leaseRules User's lease agreement if they are a renter, community rules if they are a homeowner.
    * @param creditNumber User's credit card number
    * @param creditExpire User's credit card expiration date in mm/yy format.
    * @param date The date passed from the system
    * @return a new AdoptionRequest
    * */
  public String makeRequest(String email, String petID, String phoneNumber, String address, String leaseRules, String creditNumber, String creditExpire, String date){//DONE
    boolean hasValidPay = pay(creditNumber, creditExpire);
    ArrayList<Pet> par = admin.viewPets();
    ArrayList<User> uar = admin.viewUsers();
    int x;
    int y;
    int size = par.size();
    int size2 = uar.size();
    if(hasValidPay){
      for(x=0;x<size; x++){
        if(par.get(x).getID().equals(petID)&&(par.get(x).isAdopted()==false)){
          AdoptionRequest ar = new AdoptionRequest(email, petID, phoneNumber, address,leaseRules,creditNumber,creditExpire,date);
          for(y=0; y<size2; y++){
            if(uar.get(y).getEmail().equals(email)){
              if(uar.get(y).getNumRequests()<3){
                uar.get(y).setNumRequests(uar.get(y).getNumRequests()+1);
                break;
              }
              else{
                String s = "A User cannot exceed 3 adoption requests. User "+email+" already has 3 requests.";
                return s;
              }
            }
            if(uar.get(y).getEmail().equals(email)==false && y == size2-1){
              String s = "This user does not exist! Please create an account for email "+email+" before attempting to create an adoption request.";
              return s;
            }
          }
          if(uar.size() == 0){
            String s = "This user does not exist! Please create an account for email "+email+" before attempting to create an adoption request.";
            return s;
          }
          admin.insertAR(ar);
          String s = "===========SUBMITTING ADOPTION REQUEST============\n"+"Adoption request submitted successfully with following details:\n"+
          "Pet ID: " + petID + "\n" + "Requested by: "+email+"\n"+"Phone: "+phoneNumber+"\n"+address+"\n"+"Credit card: "+creditNumber+"\n"+"Expiration Date: "+creditExpire+"\n";
          return s;
        }
        if(par.get(x).getID().equals(petID)&&(par.get(x).isAdopted())){
          String s = "Pet with ID "+petID+" has already been adopted!\n";
          return s;
        }
      }
      String s = "invalid pet information for adoption request with petID: "+petID+"\n";
      return s;
    }
    else{
      String s = "invalid payment information for adoption request with email: " +email+" and petID: "+petID+"\n";
      return s;
    }
  }
  /**This method goes into the adoption request and determines its status. The adoption request can be approved, denied, pending review, pending response, or cancelled.
    * @param ar A pre-existing adoption request
    * @return A String representation of the adoption request's status
    * */
  public String getStatus(AdoptionRequest ar){ //DONE
    ArrayList<Pet> petlist = admin.viewPets();
    int size = petlist.size();
    int x;
    if(ar.getIsDenied() == true){
     return "not approved";
    }
    else if(ar.getIsApproved() == true){
      return "approved";
    }
    else if(ar.getIsCancelled() == true){
      if(ar.getIsPendingMeeting() == true){
        return "canceled before meeting";
      }
      else{
        for(x=0; x<size; x++){
          if(petlist.get(x).getID().equals(ar.getPetID()) && petlist.get(x).isAdopted()){
            return "cancelled due to animal adoption";
          }
         
        }
         if(ar.getIsPendingMeeting() == false){
            return "canceled before review";
          }
      }
    }
    else if(ar.getIsCancelled() == false && ar.getIsPendingMeeting() == true){
      return "pending meeting";
    }
    else{
      ArrayList <Meeting> mtg = admin.viewMeetings();
      size = mtg.size();
      for(x=0;x<size;x++){
        if((mtg.get(x).getEmail().equals(ar.getEmail())) && (mtg.get(x).getPetID().equals(ar.getPetID())) && mtg.get(x).getAccepted_meet() == null){
          return "pending review";
        }
      }
      return "pending response";
    }
    return "input error";
  }
  /**This method goes into the admin class to retrieve all existing adoption requests for a specific user
    * @param email A user's email
    * @return A list of adoption requests
    * */
  public ArrayList<AdoptionRequest> getRequestsUser(String email){//DONE
    ArrayList<AdoptionRequest> ar = admin.viewAdoptionReqs();
    ArrayList<AdoptionRequest> uar = new ArrayList<AdoptionRequest>();
    int size = ar.size();
    int x;
    for(x=0;x<size;x++){
      if(ar.get(x).getEmail().equals(email)){
        uar.add(ar.get(x));
      }
    }
    return uar;
  }
  /**This method goes into the admin class to retrieve all existing adotion requests for a specific pet
    * @param petID A pet's identification number
    * @return A list of adoption requests
    * */
  public ArrayList<AdoptionRequest> getRequestsPet(String petID){//DONE
    ArrayList<AdoptionRequest> ar = admin.viewAdoptionReqs();
    ArrayList<AdoptionRequest> par = new ArrayList<AdoptionRequest>();
    int size = ar.size();
    int x;
    for(x=0;x<size;x++){
      if(ar.get(x).getPetID().equals(petID)){
        par.add(ar.get(x));
      }
    }
    return par;
  }
    
  /**This method will retrieve an existing adoption request given a user email and pet id
    * @param email A user's email
    * @param petID A pet's identification number
    * @return the adoption request
    * */
  public AdoptionRequest getRequest(String email, String petID){//DONE
    ArrayList<AdoptionRequest> ar = admin.viewAdoptionReqs();
    int size = ar.size();
    int x;
    for(x=0;x<size;x++){
      if((ar.get(x).getEmail().equals(email))&&(ar.get(x).getPetID().equals(petID))){
        return ar.get(x);
      }
    }
    System.out.println("A request does not exist for Email: "+email+" and Pet ID: "+petID+"\n");//This will catch an error
    return null;
  }
  /**This method makes sure a user has paid their $10 fee before creating an adoption request.
    * @param creditNumber A user's credit card number
    * @param creditExpire A user's credit card expiration date in mm/yy format
    * @return true if payment was successful and false if payment was not successful
    * */
  public boolean pay(String creditNumber, String creditExpire){
    BankingSystemProxy bsp = new BankingSystemProxy();
    bsp.verify(creditNumber, creditExpire);
    return bsp.checkIfValid();
  }
  /**This method changes an adoption request's status to approved
    * @param ar A pre-existing adoption request
    * @return The formatted string containing the decision that the adoption request has been approved.
    * */
  public String approveRequest(AdoptionRequest ar){//DONE
    ar.setIsApproved(true);
    ArrayList<AdoptionRequest> requests = admin.viewAdoptionReqs();
    if(!requests.contains(ar)){
      return "This adoption request does not exist!\n";
    }
    ArrayList<Pet> pl = admin.viewPets();
    int size = pl.size();
    int x;
    //set adoption request as approved
    for(x=0; x<size; x++){
      if(pl.get(x).getID().equals(ar.getPetID())){
        pl.get(x).markAsAdopted();
        break;
      }
    }
    //set other adoption requests with this pet id
    ArrayList<AdoptionRequest> cancelReqs = admin.viewAdoptionReqs();
    int size2 = cancelReqs.size();
    for(x = 0; x<size2;x++){
      if(!(cancelReqs.get(x).getEmail().equals(ar.getEmail())) && cancelReqs.get(x).getPetID().equals(ar.getPetID())){
        cancelReqs.get(x).setIsCancelled(true);
      } 
    }
    String s = "================REQUEST DECISION================\n"+"Request with the following details has been approved: \n"+"User email: "+ar.getEmail()+"\n"+"Pet id: "+ar.getPetID()+"\n";
    return s;
  }
  /** This method changes an adoption request's status to denied
    * @param ar A preexisting adoption request
    * @return The formatted string containing the decision that the adoption request has been denied.
    * */
  public String denyRequest(AdoptionRequest ar){//DONE
    ar.setIsDenied(true);
    ArrayList<AdoptionRequest> requests = admin.viewAdoptionReqs();
    if(!requests.contains(ar)){
      return "This adoption request does not exist!\n";
    }
    String s = "================REQUEST DECISION================\n"+"Request with the following details has been disapproved: \n"+"User email: "+ar.getEmail()+"\n"+"Pet id: "+ar.getPetID()+"\n";
    return s;
  }
  /**This method changes an adoption request's status to cancelled.
    * @param ar A preexisting adoption request
    * @return The formatted string containing the cancellation.
    * */
  public String cancelRequest(AdoptionRequest ar){//DONE
    ar.setIsCancelled(true);
    ArrayList<AdoptionRequest> requests = admin.viewAdoptionReqs();
    if(!requests.contains(ar)){
      return "This adoption request does not exist!\n";
    }
    String s = "============CANCEL ADOPTION REQUEST=============\n"+"Following adoption request has been cancel by user:\n"+"User email: "+ar.getEmail()+"\n"+"Pet Id: "+ar.getPetID()+"\n";
    return s;
  }
  /**This method allows a user to view all of their current adoption requests
    * @param email A user's email
    * @return A string representation of all of the user's adoption requests
    * */
  public String viewRequestStatus(String email){//DONE
    ArrayList <AdoptionRequest> ar = getRequestsUser(email);
    String s = "==========VIEW ADOPTION REQUEST STATUS============\n"+"User email: "+email+"\n"+"Pet ID      STATUS\n"+"--------------------------\n";
    int x;
    int size = ar.size();
    for(x=0;x<size;x++){
      s=s.concat(ar.get(x).getPetID());
      s=s.concat("     ");
      s=s.concat(getStatus(ar.get(x)));
      s=s.concat("\n");
    }
    return s;
  }
  /**This method allows for the viewing of all adoption requests attached to a pet
    * @param petID A pet's identification number
    * @return string representation of all of the pet's adoption requests
    * */
  public String reviewRequestStatus(String petID){//DONE
    ArrayList<AdoptionRequest> ar = getRequestsPet(petID);
    String s ="=========REVIEW ADOPTION REQUEST STATUS===========\n"+"Following requests are pending with pet id "+petID+":\n"+
      "User email              Request details\n"+"---------------------------------------------------\n";
    int x;
    int size = ar.size();
    for(x=0;x<size;x++){
      s=s.concat(ar.get(x).getEmail());
      s=s.concat("\t\t\t");
      s=s.concat("Phone: ");
      s=s.concat(ar.get(x).getPhoneNumber());
      s=s.concat("\n");
      s=s.concat("\t\t\t\t");
      s=s.concat("Address: ");
      s=s.concat(ar.get(x).getAddress());
      s=s.concat("\n");
      s=s.concat("\t\t\t\t");
      s=s.concat("Lease/Community rules: ");
      s=s.concat(ar.get(x).getLeaseRules());
      s=s.concat("\n");
      s=s.concat("---------------------------------------------------\n");
    }
    return s;
  }
}