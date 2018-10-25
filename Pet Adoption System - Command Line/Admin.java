   /**The Admin manages most functions related to handling pets, users, adoption requests, and meetings.
@author Alexander Duncanson
*/
import java.util.ArrayList;
public class Admin
{
/**Arraylists for storing pets, users, meetings, and adoption requests.
*/
   private ArrayList<Pet> pets;
   private ArrayList<User> users;
   private ArrayList<Meeting> meetings;
   private ArrayList<AdoptionRequest> adoptionReqs;

/**Constructor for Admin. No fields.

*/
   public Admin()
   {
      pets = new ArrayList<Pet>();
      users = new ArrayList<User>();
      meetings = new ArrayList<Meeting>();
      adoptionReqs = new ArrayList<AdoptionRequest>();
   }

/**Takes an instance of class Meeting and removes it from the meetings array

*/
   public void removeMeeting(Meeting m)
   {
      int pos = meetings.indexOf(m);
      if(pos == -1)
         return;
      meetings.remove(pos);
   }

/**Takes an instance of class AdoptionRequest and removes it from the adoptionReqs array
   @return true if successful, false if not
*/
   public boolean removeRequest(AdoptionRequest ar)
   {
      int pos = adoptionReqs.indexOf(ar);
      if(pos == -1)
         return false;
      else
      {
         adoptionReqs.remove(pos);
         return true;
      }
   }

/**Inserts the specified Pet into the pets array

*/
   public void insertPet(Pet p)
   {
      if(pets.indexOf(p)==-1)
         pets.add(p);
   }

/**Inserts the specified Pet into the pets array

*/
   public void insertUser(User u)
   {
      if(users.indexOf(u)==-1)
         users.add(u);
   }

/**Inserts AdoptionRequest into adoptionReqs array

*/
   public void insertAR(AdoptionRequest ar)
   {
      if(adoptionReqs.indexOf(ar)==-1)
         adoptionReqs.add(ar);
   }
   
   
   /**Inserts meeting into meetings array
      
   */
   public void insertMeeting(Meeting m)
   {
      if(meetings.indexOf(m)==-1)
         meetings.add(m);
   }
   
   
   /**Finds intended ArrayList to view and returns it
   *@param String parameter defining which ArrayList to return
   */
   public ArrayList<Pet> viewPets()
   {
      return pets; 
   }
   
   public ArrayList<Meeting> viewMeetings()
   {
      return meetings; 
   }

   public ArrayList<User> viewUsers()
   {
      return users; 
   }

   public ArrayList<AdoptionRequest> viewAdoptionReqs()
   {
      return adoptionReqs; 
   }


}