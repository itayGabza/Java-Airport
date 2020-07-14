
	import java.io.File;
	import java.util.Scanner;
	import java.util.Vector;

	public class ReaderFile 
	{
		public static Vector<String> file2Vector(String path)
		{
			Scanner s = null;
			try
			{
				s = new Scanner(new File(path));
				Vector<String> fileLinesList = new Vector<String>();
				while (s.hasNextLine())
				{
					fileLinesList.add(s.nextLine());
				}
				return fileLinesList;
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
			finally
			{
				s.close();
			}
			return null;
		}

	}
