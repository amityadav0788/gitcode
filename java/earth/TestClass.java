/* IMPORTANT: class must not be public. */

/*
 * uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;

public class TestClass 
{


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	int T;
		 Scanner in = new Scanner(System.in);
		 T = in.nextInt();
		 while(T!=0)
		 {
			int N;
			long K;
			long maximum=-9999;
			N = in.nextInt();
			K = in.nextLong();
			long[] input = new long[N];
			for(int i=0;i<N;i++)
			{
				input[i] = in.nextLong();
			}
			int j=0;
			while(j<N)
			{
				long m=0;
				int k=0;
				if(input[j]<=K)
				{
					m=0;
					//long K_temp = input[j];
					k =j;
					while(k<N && input[k] <= K)
					{
						long K_temp = input[k];
						m = m+K_temp;
						//System.out.println(m+" "+K_temp);
						k++;
					}
					j = k;
					maximum = Math.max(maximum,m);
					//System.out.println("max temp: "+maximum);
				}
				else j++;
			}
			System.out.println(maximum);
		 T--;
		 }
    }
}