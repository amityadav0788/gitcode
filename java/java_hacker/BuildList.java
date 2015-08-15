import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BuildList 
{
	private String input_string;
	private StringBuilder output = new StringBuilder();
    
	public BuildList(String str)
	{
		input_string = str;
	}
	
	public void combine()
	{
		combine(0);
	}
	public void combine(int start)
	{
		for(int i=start;i<input_string.length();++i)
		{
			output.append(input_string.charAt(i));
			System.out.println(output);
			if(i<input_string.length())
				combine(i+1);
			output.setLength(output.length()-1);
		}
	}


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		int T;
         int N;
		 Scanner in = new Scanner(System.in);
		 T = in.nextInt();
		 String[] inputString = new String[T];
		 Integer[] inputSize = new Integer[T];
		 for(int i=0;i<T;i++)
		 {
			inputSize[i] = in.nextInt();
			inputString[i] = in.next();
		 }
		 for(int i=0;i<T;i++)
		 {
			if(inputSize[i]==inputString[i].length())
			{
				BuildList comb = new BuildList(inputString[i]);
				comb.combine();
			}
		 }
    }
}