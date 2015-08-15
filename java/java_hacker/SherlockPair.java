import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SherlockPair {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         int N,T,ct;
        Scanner in = new Scanner(System.in);
		T = in.nextInt();
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		while(T!=0)
		{
			ct = 0;
			N = in.nextInt();
			if(N!=0)
			{
				int[] count = new int[100000];
				for(int i=0;i<N;i++)
					count[i] = 0;
		
				int[] members = new int[N];
				for(int i=0;i<N;i++)
					members[i] = in.nextInt();
				for(int j=0;j<N;j++)
				{
					count[members[j]]++;
				}
			for (int i = 0; i < 100000; i++) {
			{
				if(count[i]!=0)
				{
					ct = ct+(count[i]*(count[i]-1));
				}
			}
			
			}
			
		}
		else ct =0;
		output.add(ct);
			T--;
		}
		for(int i=0;i<output.size();i++)
				System.out.println(output.get(i));
    }
}