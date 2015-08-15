import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TooFastTooFurious {
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        int[] Dom = new int[N];
        int[] Brian = new int[N];
        String line1 = br.readLine();
        String line2 = br.readLine();
        String[] Dom_input = line1.split(" ");
        String[] Brian_input = line2.split(" ");
        for(int i =0;i<N;i++)
        {
        	Dom[i] = Integer.parseInt(Dom_input[i]);
        	Brian[i] = Integer.parseInt(Brian_input[i]);
        }
        int maxDom = 0,maxBrian = 0;
        for(int j=0;j<N-1;j++)
        {
        	if(Math.abs(Dom[j+1]-Dom[j]) > maxDom)
        			maxDom = Math.abs(Dom[j+1]-Dom[j]);
        	if(Math.abs(Brian[j+1]-Brian[j]) > maxBrian)
        			maxBrian = Math.abs(Brian[j+1]-Brian[j]);
        }
        if(maxDom>maxBrian)
        {
        	System.out.println("Dom");
        	System.out.println(maxDom);
        }
        else if(maxDom<maxBrian)
        {
        	System.out.println("Brian");
        	System.out.println(maxBrian);
        }
        else
             	System.out.println("Tie");
	}
}
