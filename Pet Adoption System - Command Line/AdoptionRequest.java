/**The AdoptionRequest takes information from a user and a pet 
   and creates an instance of itself, pending approval.
 * @author Alexander Duncanson
   */

public class AdoptionRequest
{
   /**Stores user email address as a String */
   private String email;
   /**Stores pet ID as String */
   private String petID;
   /**Stores user phone number as an int */
   private String phoneNumber;
   /**User address stored in string */
   private String address;
   /**User lease rules stored in string */
   private String leaseRules;
   /**User credit (card?) Number stored as a String */
   private String creditNumber;
   /**User credit card expiration date stored as a String */
   private String creditExpire;
   /**The boolean state of approval for the AdoptionRequest */
   private boolean isApproved;
   /**The boolean state of pending for AdoptionRequest*/
   private boolean isPending;
   /**The boolean state of denial for AdoptionRequest */
   private boolean isDenied;
   /**Determines if there is a pending meeting */
   private boolean isPendingMeeting;
   /**Determines if the adoption request has been cancelled */
   private boolean isCancelled;
   /**The date the adoption request was created */
   private String dateCreated;
   
   /**get method for Email
      *@return user email address as String
    */
   public String getEmail()
   {
      return email;
   }
   
   /**sets email to given String value */
   public void setEmail(String e)
   {
      email = e;
   }

   /**get method for pet ID
      @return pet ID as String
    */
   public String getPetID()
   {
      return petID;
   }
   
   /**sets petID to given String value*/
   public void setPetID(String p)
   {
      petID = p;
   }

   /**get method for user phone number
      @return user phone number as int
    */
   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   /**sets phone number to given int value*/
   public void setPhoneNumber(String num)
   {
      phoneNumber = num;
   }

   /**get method for user address
      @return user
    */
   public String getAddress()
   {
      return this.address;
   }

   /**sets user address to given String value */
   public void setAddress(String a)
   {
      address  = a;
   }

   /**get method for user lease rules
      @return user lease rules as String
    */
   public String getLeaseRules()
   {
      return leaseRules;
   }

   /**sets user lease rules to given String value */
   public void setLeaseRules(String lr)
   {
      leaseRules = lr;
   }

   /**get method for user credit number 
      @return user credit number as String
   */
   public String getCreditNumber()
   {
      return creditNumber;
   }
   
   /**sets user credit number to given String value */
   public void setCreditNumber(String cn)
   {
      creditNumber = cn;
   }

   /**get method for user credit card expiration date 
   @return user credit card expiration date as String
   */
   public String getCreditExpire()
   {
      return creditExpire;
   }

   /**sets user credit card expiration date as String value */
   public void setCreditExpire(String ce)
   {
      creditExpire = ce;
   }

   /**get method for boolean state of approval
      @return true or false for approval state
    */
   public boolean getIsApproved()
   {
      return isApproved;
   }

   /**sets approval state to given boolean value */
   public void setIsApproved(boolean a)
   {
      isApproved = a;
   }

   /**get method for boolean state of pending
   @return true or false for pending state
    */
   public boolean getIsPending()
   {
      return isPending;
   }

   /**sets pending state to given boolean value */
   public void setIsPending(boolean p)
   {
      isPending = p;
   }
   
   /**get method for boolean state of denial
   @returns true or false for denial state
    */
   public boolean getIsDenied()
   {
      return isDenied;
   }
   
   /**sets denial state to given boolean value */
   public void setIsDenied(boolean d)
   {
      isDenied = d;
   }
   
   public boolean getIsPendingMeeting()
   {
      return isPendingMeeting;
   }
   
   public void setIsPendingMeeting(boolean p)
   {
      isPendingMeeting = p;
   }
   
   public boolean getIsCancelled()
   {
      return isCancelled;
   }
   
   public void setIsCancelled(boolean c)
   {
      isCancelled = c;
   }
   
   public String getDateCreated()
   {
      return dateCreated;
   }
   
   public void setDateCreated(String d)
   {
      dateCreated = d;
   }
   
   
   /**
      Takes above fields and creates an AdoptionRequest. isApproved and isPending begin as false.
      @param user email, String
      @param pet ID, String
      @param user phone number, String
      @param user address, String
      @param user lease rules, String
      @param user credit number, String
      @param user credit expiration date, String
      @param date created, String
      */
   public AdoptionRequest(String email, String petID, String phoneNumber, String address, String leaseRules, String creditNumber, String creditExpire, String dateCreated)
   {
      setEmail(email);
      setPetID(petID);
      setPhoneNumber(phoneNumber);
      setAddress(address);
      setLeaseRules(leaseRules);
      setCreditNumber(creditNumber);
      setCreditExpire(creditExpire);
      setIsApproved(false);
      setIsPending(false);
      setIsDenied(false);
      setIsPendingMeeting(false);
      setIsCancelled(false);
      setDateCreated(dateCreated);
   }

   /** 
      Checks if the email present in the AdoptionRequest matches that of the provided user.
      @return boolean value
      */
   public boolean emailMatch(User u)
   {
      if(this.email == u.getEmail())
         return true;
      else 
         return false;
   }
   
   /**    
      Checks if the petID present in the AdoptionRequest matches that of the provided pet.
      @return boolean value
      */
   public boolean pet_ID_Match(Pet p)
   {
      if(this.petID == p.getID())
         return true;
      else 
         return false;
   }
}