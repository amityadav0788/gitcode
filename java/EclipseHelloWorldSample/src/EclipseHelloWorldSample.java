import java.io.*;

class EclipseHelloWorldSample
{
	public static void main(String[] args)
	{
		String name;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			name = in.readLine();
			System.out.println("HelloWorld "+name);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
