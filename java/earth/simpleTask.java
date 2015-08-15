import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;

public class simpleTask 
{

	public static long Answer = 0;
	
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		int T;
		 Scanner in = new Scanner(System.in);
		 T = in.nextInt();
		 while(T!=0)
		 {
			Answer = 0;
			long A = in.nextLong();
			long B = in.nextLong();
			long C = in.nextLong();
			
			long max = A, secMax = B;
			
			if(max < secMax) {
				long temp = max;
				max = secMax;
				secMax = temp;
			}
			
			if(max < C) {
				secMax = max;
				max = C;
			} else {
				if(secMax < C) {
					secMax = C;
				}
			}

			if(A == 0L && B == 0L && C == 0L) {
				Answer = 0L;
			} else if((A == 0 && B == 0) || (A == 0 && C == 0) || (B == 0 && C == 0)) {
				Answer = 1L;
			}
			else if(secMax == max)
				Answer = max + secMax;
			else
				Answer = (secMax << 1) + 1;
			System.out.println(Answer);
		 T--;
		 }
    }
}