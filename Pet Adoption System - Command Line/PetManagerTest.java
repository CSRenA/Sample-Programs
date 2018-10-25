import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//Alexander Duncanson
public class PetManagerTest {


   
   String petID = "P095765";
   String petName = "Rudy";
   String photoName = "rudy.png";
   String descriptionText = "Rudy is a loving dog in need of a good home.";
   Admin a = new Admin();
   PetManager p = new PetManager(a);
   
   
   @Test (timeout=2000)     
   public void createPetTest() 
   {
      Pet actual = p.createPet(petID, petName, photoName, descriptionText);
      Assert.assertNotNull(actual);
   }

   @Test (timeout=2000) 
   public void addPetTest() 
   {
   
      String expected = "================ADDING PET SUCCESS=================\nA new pet has been added successfully with following information:\nPet Id: "+petID+"\nPet Name: "+petName+"\nPet Photo: "+photoName+"\nPet Story: "+descriptionText+"\n";
      String actual = p.addPet(petID, petName, photoName, descriptionText);
      Assert.assertEquals(expected, actual);
   }
   @Test (timeout=2000)
   public void viewAnimalsTest() 
   {
      p.addPet(petID, petName, photoName, descriptionText);
      String expected = "=================AVAILABLE ANIMALS' LIST=================\nPet Id: "+petID+"\nPet Name: "+petName+"\n--------------------------\n"; 
      String actual = p.viewAnimals();
      Assert.assertEquals(expected, actual);
   }

   @Test (timeout=2000)
   public void viewPetTest() 
   {
      p.addPet(petID, petName, photoName, descriptionText);
      String expected = "=================DETAILS OF A SPECIFIC PET=================\nDetail information of pet with id "+petID+" is below:\nPet Name: "+petName+"\nPet Picture: "+photoName+"\nPet Story: "+descriptionText+"\n";
      String actual = p.viewPet(petID);
      Assert.assertEquals(expected, actual);
   }


   

}
