import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
	static String[] file = new String[10000];
	public static void main (String[] args) throws IOException
	{
		
		// I set up the input file
		FileReader fileReader = new FileReader ("input");
		BufferedReader buff = new BufferedReader (fileReader);
		read (buff);
		compile (null);
		
	}
	static int n;
	public static void read (BufferedReader buff)
	{
		String line;
		int depth = 0;
		try {
			while ((line = buff.readLine()) != null)
			{
				// I eliminate the spaces
				file[++n] = line.substring(depth * 3);
				
				if (file[n].length() > 4 && file[n].substring(0, 5).equals("while"))
					depth++;
				if (file[n].equals(";"))
					depth--;
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static Map<String, Integer> map = new HashMap <String, Integer>();
	static String[] splitted;
	static int currentLine;
	public static void compile (String x)
	{
		int startLine = currentLine;
		String var;
		while (currentLine < n)
		{
			splitted = file[++currentLine].split(" ");
			if (splitted[0].equals("clear"))
			{
				var = splitted[1];
				var = var.substring(0, var.length() - 1);
				map.put(var, 0);
				System.out.println (var + " = " + map.get(var));
			}
			
			if (splitted[0].equals("incr"))
			{
				var = splitted[1];
				var = var.substring(0, var.length() - 1);
				map.put(var, map.get(var) + 1);
				System.out.println (var + " = " + map.get(var));
			}
			
			if (splitted[0].equals("decr"))
			{
				var = splitted[1];
				var = var.substring(0, var.length() - 1);
				map.put(var, map.get(var) - 1);
				System.out.println (var + " = " + map.get(var));
			}
			
			if (splitted[0].equals("while"))
			{
				// I check if I should enter while
				if (map.get(splitted[1]).equals(0))
				{
					while (!file[currentLine].equals(";"))
						currentLine++;
				}
				else
					compile (splitted[1]);
			}
			else
			if (splitted[0].equals(";"))
			{
				// I check if I should leave while because the value reached 0
				if (x != null && map.get(x) == 0)
				{
					break;
				}
				currentLine = startLine;
			}
			
		}
	}
}