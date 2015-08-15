import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PolicyBazaarPrimeString {

static boolean isPrime(long len)
{
	int i;
	if(len == 1)
		return false;

    if (len==2)
        return true;

    if (len%2==0)
        return false;
	float j = (float)Math.sqrt(len);
    for (i=3;i<=j;i+=2)
        if (len%i==0)
            return false;

    return true;
}

static boolean isPrime(int len)
{
	int i;
	if(len == 1)
		return false;
    if (len==2)
        return true;

    if (len%2==0)
        return false;
	float j = (float)Math.sqrt(len);
    for (i=3;i<=j;i+=2)
        if (len%i==0)
            return false;

    return true;
}

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
		while(T!=0)
		{
		String input = br.readLine();
			//long count[26];
		long length = input.length();
		boolean flag=true;
		if(!isPrime(length))
		{
			flag = false;
		}
		else
		{
		long[] count = new long[26];
		for(int i=0;i<26;i++)
			count[i]=0;
			for(int i=0;i<length;i++)
				count[input.charAt(i)-'a']++;
			int distinct_number=0;
			for(int i=0;i<26;i++)
			{
				if(count[i]>0)
					distinct_number++;
			}
			if(isPrime(distinct_number))
			{
				for(int i=0;i<26;i++)
				{
					if(!isPrime(count[i])&&count[i]>0)
					{
						flag = false;
						break;
					}
				}
			}
			else flag = false;
		}
		if(flag)
			System.out.println("YES");
		else System.out.println("NO");
			T--;
		}
    }
}