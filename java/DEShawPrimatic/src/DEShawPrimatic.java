import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DEShawPrimatic {
	static int Max = 10000;
	static int[] isPrime = new int[Max+10];
	static int[] dp = new int[Max+10];
	static Vector<Integer> al = new Vector<>();
	private static void start()
	{
		int i,j;
		isPrime[0]=1;
		isPrime[1]=1;
		for(i=2;i<=Max;i++)
		{
			if(isPrime[i]==0)
			{
				for(j=i*i;j<=Max;j+=i)
				{
					isPrime[j]=1;
				}
			}
		}
		for(i=2;i<=Max;i++)
		{
			if(isPrime[i]==0)
			{
				al.add(i);
			}
		}
		isPrime[4]=isPrime[27]=isPrime[3125]=0;
		al.add(4);
		al.add(27);
		al.add(3125);
	}
	public static void main(String[] args) throws Exception
	{
		int i,t,n,sz,j;
		start();
		Arrays.sort(isPrime);
		dp[2]=1;
		dp[3]=1;
		sz=al.size();
		
		for(i=4;i<Max+1;i++)
		{
			//dp[i]=mod;
			if(isPrime[i]==0)
			{
				dp[i]=1;
				continue;
			}
			for(j=0;j<sz;j++)
			{
				if(i-al.get(j)<2)
					break;
				dp[i]=Math.min(dp[i], dp[i-al.get(j)]+1);
			}
		}
		/*for(i = 2;i<Max+1;i++)
		{
			if(isPrime[i]==0)
			{
				if(dp[i]!=1)
					return;
			}
			else if(i%2==0)
				{
					if(dp[i]!=2)
						return;
				}
			else
			{
				if(dp[i]>3)
					return;
			}
		}*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
        line = br.readLine();
        t = Integer.parseInt(line);
        while(t!=0)
        {
        	line = br.readLine();
        	n = Integer.parseInt(line);
        	System.out.println(dp[n]);
        	t--;
        }
	}

}
