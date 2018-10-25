import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Framework
{

	private static Parser parser;
	
	public static void init(String paramString) throws FileNotFoundException {
		parser = new Parser(paramString);
	}
	
	public static boolean hasNextInstruction() {
		return parser.hasNextInstruction();
	}
	
	public static String[] nextInstruction() {
		return parser.getNextInstruction();
	}
}