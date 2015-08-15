import java.io.BufferedReader;
import java.io.InputStreamReader;

class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
        while(T!=0)
        {
        	String lin1 = br.readLine();
        	int N = Integer.parseInt(lin1);
        	int[] input = new int[N];
        	String lin2 = br.readLine();
        	String[] inputString = lin2.split(" ");
        	int M=0,R=0,l=0,r=N-1;
        	float MT=0,RT=0;
         	for (int i = 0; i < N; i++) {
        	input[i] = Integer.parseInt(inputString[i]);
        	}
         	/*if(input[l]%2==0)
 				MT = MT+(input[l]/2);
 			else MT = MT+(input[l]/2)+1;*/
         	MT = MT+((float)input[l]/2);
         	RT = input[r];
         	M=1;
         	if(N>1)
         		R=1;
         	while(l!=r-1 && l!=r)
         	{
         		if(MT<=RT)
         		{
         			l++;
         			M++;
         			/*if(input[l]%2==0)
         				MT = MT+(input[l]/2);
         			else MT = MT+(input[l]/2)+1;*/
         			MT = MT+((float)input[l]/2);
             		System.out.println("MT "+MT + " RT "+RT);
         		}
         		else
         		{
         			r--;
         			R++;
         			RT = RT+input[r];
             		//System.out.println("MT "+MT + " RT "+RT);
         			
         		}
         	}
         	System.out.println(M+" "+R);
        T--;
        }
      }
}
