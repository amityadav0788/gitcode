import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram 
{
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		 int T;
         Scanner in = new Scanner(System.in);
		 T = in.nextInt();
		 while(T!=0)
		 {
			String S;
			S = in.next();
			int S1,S2,result=-1;
			int[] count1 = new int[26];
			int[] count2 = new int[26];
			for(int i = 0;i<26;i++)
			{
				count1[i]=0;
				count2[i]=0;
			}
				
			if(S.length()%2==0)
			{
				result = 0;
				S1 = S.length()/2;
				for(int i = 0;i<S1;i++)
				{
					count1[S.charAt(i)-'a']++;
				}
				for(int j = S1;j<S.length();j++)
				{
					count2[S.charAt(j)-'a']++;
				}
				for(int k=0;k<26;k++)
				{
					//System.out.println(" Count1 : " + count1[k] + " Count 2: " + count2[k]);
					if(count1[k]-count2[k]>0)
					{
					  result = result+Math.abs(count1[k]-count2[k]);
					}
				}
				System.out.println(result);
			}
			else
			{
				System.out.println(result);
				//return;
			}
			T--;
		 }
    }
}