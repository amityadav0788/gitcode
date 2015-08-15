import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TeamFormation {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         int N,T;
        Scanner in = new Scanner(System.in);
		T = in.nextInt();
		while(T!=0)
		{
			N = in.nextInt();
			if(N!=0)
			{
			int[] members = new int[N];
			for(int i =0;i<N;i++)
				members[i] = in.nextInt();
			Arrays.sort(members);
			/*for(int i =0;i<N;i++)
			System.out.println(members[i]+" ");*/
			int count=1;
			ArrayList<ArrayList<Integer>> map = ArrayList<ArrayList<Integer>>();
			int i=1;
			while(i<N)
			{
				if(members[i] == members[i-1]+1)
				{
					
				}
			}
			for(int i =1;i<N;i++)
			{
				if(members[i] == members[i-1]+1)
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
			}
			else
			{
			System.out.println(N);
			}
			T--;
		}
    }
}