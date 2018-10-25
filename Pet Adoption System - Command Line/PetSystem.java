import java.io.*;

public class PetSystem 
{
	public static void main(String[] args) 
	{	
		String output;
		
		AdoptionSystemCoordinator asc = new AdoptionSystemCoordinator();
		try
		{
			Framework.init(args[0]);
			while( Framework.hasNextInstruction())
			{
				String[] istr = Framework.nextInstruction();
				output = asc.executeCommand(istr);
				System.out.println(output);
			}
		}
		catch ( FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

}