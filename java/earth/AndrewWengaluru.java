import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;

public class AndrewWengaluru 
{
	public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
		if(T<=10)
		{
			while(T!=0)
			{
				String line1 = br.readLine();
				int N = Integer.parseInt(line1);
				String line2 = br.readLine();
				String[] parts = line2.split(" ");
				int[] input = new int[N];
				int[] left = new int[N];
				int[] right = new int[N];
				int output = 0;
				for(int i=0;i<N;i++)
				{
					input[i] = Integer.parseInt(parts[i]);
					//System.out.println(input[i]);
				}
				left[0] = input[0];
				for(int i =1;i<N;i++)
				{
					if(input[i]>left[i-1])
					{
						left[i] = input[i];
					}
					else left[i] = left[i-1];
				}
				right[N-1] = input[N-1];
				for(int j =N-2;j>=0;j--)
				{
					if(input[j]>right[j+1])
					{
						right[j] = input[j];
						
					}
					else right[j] = right[j+1];
				}
				for(int k =1;k<=N-2;k++)
				{
					if((Math.min(left[k-1],right[k+1])-input[k])>0)
					{
						//System.out.println(Math.min(left[k-1],right[k+1])-input[k]);
						output=(output+(Math.min(left[k-1],right[k+1])-input[k]))%1000000007;
					}
				}
				System.out.println(output);
				T--;
			}
		}
    }
}