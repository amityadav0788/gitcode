import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AlternateChar 
{


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		int T;
         String S;
		 Scanner in = new Scanner(System.in);
		 T = in.nextInt();
		 while(T!=0)
		 {
			S = in.next();
			int count = 0;
			StringBuilder sb = new StringBuilder(100000);
			sb.append(S.charAt(0));
			for(int i =1;i < S.length();i++)
			{
				if(S.charAt(i) != S.charAt(i-1))
				{
					sb.append(S.charAt(i));
				}
				else
				count++;
			}
		 System.out.println(count);
		 T--;
		 }
    }
}