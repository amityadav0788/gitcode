import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        ArrayList<Integer> input = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
        	String line1 = br.readLine();
        	int inp = Integer.parseInt(line1);
        	input.add(inp);
            
        }
        int sum = 0;
        int j = 0;
        while(j!=input.size())
        {
        	if(input.get(j)==0 && j>0)
        	{
        		sum = sum-input.get(j-1);
        		input.remove(j);
        		input.remove(j-1);
        		j--;
        	}
        	else
        	{
        		sum = sum+input.get(j);
        		//input.remove(j);
        		j++;
        		//System.out.println("hi1");
                
        	}
        }
        System.out.println(sum);
       }
}
