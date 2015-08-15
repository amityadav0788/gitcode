import java.io.*;
import java.util.*;

public class DEShawStringResearc {
	public static void main(String[] args)
	{
		int N,Q;
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		String [] input = new String [10000];
		for(int i=0;i<N;i++)
		{
			input[i] = in.next();
		}
		Q = in.nextInt();
		for(int j=0;j<Q;j++)
		{
			int L,R;
			String S = new String();
			String outp = new String();
			L = in.nextInt();
			R = in.nextInt();
			S = in.next();
			int max =0;
			for(int k=L-1;k<R;k++)
			{
				String pre = Prefix(input[k],S);
				if(pre!=null)
				{
					if(pre.length() > max)
					{
						max = pre.length();
						outp = pre;
					}
				}
			}
			System.out.println(outp);
		}
	}

	private static String Prefix(String s,String s2)
	{
		String small,large;
        if(s.length() > s2.length()) 
           {small = s2;large = s;}
         else
           {small = s;large = s2;}
       int index = 0;    
       for(char c: large.toCharArray())
       {
           if(index==small.length()) break;
           if(c != small.charAt(index)) break;
           index++;
       }
       if(index==0)
         return null;
       else
         return large.substring(0,index);
	}
}
