/** The Pet Manager creates new pets
   @author Alexander Duncanson
   */
public class PetManager
{

   private Pet newPet;
   private Admin admin;
          
   public PetManager(Admin a)
   {
      this.admin = a;
   }
   
   /**Adds a new pet and prints out a confirmation message */
   public String addPet(String petID, String name, String photo, String description)
   {
      
      Pet tempPet = createPet(petID, name, photo, description);
      admin.viewPets().add(tempPet);
      String ret = "================ADDING PET SUCCESS=================\nA new pet has been added successfully with following information:\nPet Id: "+tempPet.getID()+"\nPet Name: "+tempPet.getName()+"\nPet Photo: "+tempPet.getPhoto()+"\nPet Story: "+tempPet.getDescription()+"\n";
      return ret;
   }
   
   /**prints out the petID and pet name */
   public String viewAnimals()
   {
      Pet tempPet;
      String ret;
      ret = "=================AVAILABLE ANIMALS' LIST=================\n";
      for(int i=0; i<admin.viewPets().size(); i++)
      {  
         tempPet = admin.viewPets().get(i);
         if(tempPet.isAdopted() == false)
            ret += "Pet Id: "+tempPet.getID()+"\nPet Name: "+tempPet.getName()+"\n--------------------------\n"; 
      }
      return ret;
   }
   
   /**Shows all details of a pet */
   public String viewPet(String pid)
   {
      Pet tempPet;
      String ret = "";
      for(int i=0; i<admin.viewPets().size(); i++)
      {
         if(admin.viewPets().get(i).getID().equals(pid))
         {
            tempPet = admin.viewPets().get(i);
            ret = "=================DETAILS OF A SPECIFIC PET=================\nDetail information of pet with id "+tempPet.getID()+" is below:\nPet Name: "+tempPet.getName()+"\nPet Picture: "+tempPet.getPhoto()+"\nPet Story: "+tempPet.getDescription()+"\n";
         }
      }
      return ret;
   } 
  
   public Pet createPet(String petID, String name, String photo, String description)
   {
      this.newPet = new Pet(petID, name, photo, description);
      return newPet;
   }


}