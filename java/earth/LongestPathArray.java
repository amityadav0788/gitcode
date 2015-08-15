import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LongestPathArray {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
		while(T!=0)
		{
			String line1 = br.readLine();
			String[] parts = line1.split(" ");
			int N = Integer.parseInt(parts[0]);
			int M = Integer.parseInt(parts[1]);
			int[][] input = new int[N][M];
			int[][] output = new int[N][M];
			for(int i =0;i<N;i++)
			{
				String line2 = br.readLine();
				String[] parts2 = line2.split(" ");
				for(int j=0;j<M;j++)
				{
					input[i][j] = Integer.parseInt(parts2[j]);
				}
			}
			 output[0][0] = 1;
			for(int i1=1;i1<N;i1++)
			{
				if(input[i1-1][0]!=-1)
				{
					if(input[i1][0] > input[i1-1][0])
						output[i1][0] = output[i1-1][0]+1;
					else output[i1][0] =-1;
				}	
			}
			for(int j1=1;j1<M;j1++)
			{
				if(input[0][j1-1]!=-1)
				{
					if(input[0][j1] > input[0][j1-1])
						output[0][j1] = output[0][j1-1]+1;
					else output[0][j1] =-1;
				}	
			}
			int length = 0;
			for(int i2=1;i2<N;i2++)
			{
				for(int j2=1;j2<M;j2++)
				{
					if(input[i2-1][j2]==-1 && input[i2][j2-1]==-1)
						output[i2][j2]=-1;
					else if(input[i2-1][j2]==-1 && input[i2][j2-1]!=-1)
					{
						if(input[i2][j2] > input[i2][j2-1])
						output[i2][j2] = output[i2][j2-1]+1;
					else output[i2][j2] =-1;
					}
					else if(input[i2-1][j2]!=-1 && input[i2][j2-1]==-1)
					{
						if(input[i2][j2] > input[i2-1][j2])
						output[i2][j2] = output[i2-1][j2]+1;
					else output[i2-1][j2] =-1;
					}
					else
					{
						if(input[i2][j2] > input[i2][j2-1])
							output[i2][j2] = output[i2][j2-1]+1;
						else output[i2][j2] =-1;
						if(input[i2][j2] > input[i2-1][j2])
							output[i2][j2] = Math.max(output[i2][j2],output[i2-1][j2]+1);
					}
				}
			}
			for(int i3=0;i3<N;i3++)
			{
				for(int j3=0;j3<M;j3++)
				{
					length = Math.max(length,output[i3][j3]);
				}
			}
			System.out.println(length);
			T--;
		}
    }
}