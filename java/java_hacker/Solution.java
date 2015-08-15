import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int T;
		long N;
        Scanner in = new Scanner(System.in);
		T = in.nextInt();
		while(T!=0)
		{
			N = in.nextLong();
			ArrayList<Long> members = new ArrayList<Long>();
			for(long i =0;i<N;i++)
				{
					long temp = in.nextLong();
					members.add(temp);
				}
			Collections.sort(members);
			/*for(int i =0;i<N;i++)
			System.out.println(members[i]+" ");*/
			long count=1;
			ArrayList<Long> max = new ArrayList<Long>();
			for(long i =1;i<N;i++)
			{
				if(members.get(i) == members.get(i-1)+1)
					count++;
				else 
				{
					max.add(count);
					count=1;
				}
			}
			max.add(count);
			Object obj = Collections.min(max);
			System.out.println(obj);
			T--;
		}
        }
    }