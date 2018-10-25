import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser
{
	private String filename;
	private Scanner scan;
	private Integer nextInstruction;
	
	public Parser(String paramString) throws FileNotFoundException {
		filename = paramString;
		scan = new Scanner(new FileReader(paramString));
		nextInstruction = Integer.valueOf(-1);
		
		if (scan.hasNextLine()) {
			String str = scan.nextLine();
			if (str.startsWith("@")) {
				nextInstruction = Integer.valueOf(Integer.parseInt(str.substring(1)));
			}
		}
	}
	
	public boolean hasNextInstruction() {
		if (nextInstruction.intValue() == -1) {
			return false;
		}
		return true;
	}
	
	public String[] getNextInstruction() {
		if (!hasNextInstruction()) {
			return null;
		}
		
		ArrayList<String> localArrayList = new ArrayList<>();
		localArrayList.add(nextInstruction.toString());
		
		while (scan.hasNextLine()) {
			String str = scan.nextLine().trim();
			
			if (!str.isEmpty()) {
				if (str.startsWith("@")) {
					nextInstruction = Integer.valueOf(Integer.parseInt(str.substring(1)));
					return localArrayList.toArray(new String[0]);
				}
				localArrayList.add(str);
			}
		}
		
		nextInstruction = Integer.valueOf(-1);
		return localArrayList.toArray(new String[0]);
	}
}