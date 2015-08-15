import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;

class Graph
{
	private Map<Integer,List<Integer>> adjacencyList;
	
	public Graph(int n)
	{
		adjacencyList = new HashMap<Integer,List<Integer>>();
		for(int i =1;i<=n;i++)
		{
			adjacencyList.put(i,new LinkedList<Integer>());
		}
	}
	
	public void setEdge(int u,int v)
	{
		List<Integer> slist = adjacencyList.get(u);
		slist.add(v);
		List<Integer> dlist = adjacencyList.get(v);
		dlist.add(u);
	}
}

public class TreeColoring 
{
	public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
		if(T<=10)
		{
			while(T!=0)
			{
				String line1 = br.readLine();
				int N = Integer.parseInt(line1);
				Graph g = new Graph(N);
				for(int i=0;i<N;i++)
				{
					String line2 = br.readLine();
					String[] parts = line2.split(" ");
					int u = Integer.parseInt(parts[0]);
					int v = Integer.parseInt(parts[1]);
					g.setEdge(u,v);
				}
				
				T--;
			}
		}
    }
}