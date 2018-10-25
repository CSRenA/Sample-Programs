/**This pet class creates a pet object for the Pet Adoption System.
 * Any pet in the system will have an object created for them.
 * @author Lauren Anderson
 * */

public class Pet{
   /** An ID created for a pet, no pet has the same ID*/
   private String petID;
   /** Name of the pet */
   private String name;
   /** Name of the photo of the pet, not an actual image */
   private String photo;
   /** Description of the pet*/
   private String description;
   /** Tells whether the pet in the adoption system has been adopted or not */
   private boolean isAdopted;
   
    
     
    /** Within this constructor for Pet, all fields except for isAdopted (boolean) will be filled upon creation and isAdopted will always start as false.
       * @param petID an ID created for a pet, no pet has the same ID, String
       * @param name the name of the pet, String
       * @param photo the name of the photo instead of an actual image, String
       * @param description a description of the pet, String*/
   public Pet(String petID, String name, String photo, String description){
      this.petID = petID;
      this.name = name;
      this.photo = photo;
      this.description = description;
      this.isAdopted = false;
   }
   
   /** will return the petID
   * @return the pet's ID*/
   public String getID(){
      return this.petID;
   }
   
   /** will return the name of the pet
    * @return the pet's name*/
   public String getName(){
      return this.name;
   }
   
   /** will return name of the photo
    * @return the name of the photo of the pet, not an actual image*/
   public String getPhoto(){
      return this.photo;
   }
   
   /** will return the description of the pet
    * @return the description of the pet*/
   public String getDescription(){
      return this.description;
   }
   
   /** will return true if adopted and false if not
    * @return true or false*/
   public boolean isAdopted(){
      return this.isAdopted;
   }
   
   /** will change the isAdopted field to true */
   public void markAsAdopted(){
      this.isAdopted = true;
   }
}