import java.io.*;

public class Adobe_EndingThreeAllOne {
	public static void main(String[] args)
	{
		int i;
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		try
		{
		String temp = bfr.readLine();
        i = Integer.parseInt(temp);
		String s = Allone(i);
		System.out.println(s);
		}
		catch(Exception e)
		{
			
		}
	}

	private static String Allone(int i)
	{
		String s= new String();
		int count=1, rem=1; 
		while(rem!=0)
		{
		 rem= (rem*10+1)%i; count++;
		} 
		 while(count!=0){ s= s.concat("1");count--;}
		return s;
	}
}
