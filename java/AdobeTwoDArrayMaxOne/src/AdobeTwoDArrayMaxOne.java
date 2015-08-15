import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdobeTwoDArrayMaxOne {
	public static void main(String[] args)
	{
		int N;
		try
		{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String tmp = bfr.readLine();
		N = Integer.parseInt(tmp);
		//int[][] A = new int[3][3];
		int A[][] = {{1,1,0},{1,0,0},{0,0,0}};
		int i = countOne(A,N);
		System.out.println(i);
		}
		catch(Exception e)
		{
			
		}
	}

	private static int countOne(int A[][],int N)
	{
		int max_row = 0, max = -1;
		for(int i=0;i<N;i++)
		{
			int j=0;
			while(j<N && A[i][j]!=1)
			{
				j++;
			}
		}return max_row;
		
	}
}
