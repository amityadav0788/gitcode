/* IMPORTANT: class must not be public. */


 //* uncomment this if you want to read input.
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class FindingNumbers {
    public static void main(String args[] ) throws Exception {
        
         //* Read input from stdin and provide input before running

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
        while(T!=0)
		 {
			if(T<=50)
			{
				int count = 0;
				String line2 = br.readLine();
				String[] parts = line2.split(" ");
				long Z = Long.parseLong(parts[0]);
				long M = Long.parseLong(parts[1]);
				long N = Long.parseLong(parts[2]);
				long temp;
				for(long i=1;i<=(long)(Math.sqrt(Z));i++)
				{
					temp = Z/i;
					if(temp*i == Z)
					{
						if(temp <= M)
							count++;
						if(temp<=N && temp!=i)
							count++;
					}
				}
				System.out.println(count);
			}
		 T--;
		 }
}
}
