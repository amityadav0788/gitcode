import java.io.*;
import java.util.*;

public class EmployeeManagerCount {
	static Map<String,Integer> result = new HashMap<String,Integer>();
	
	public static void main(String[] args)
	{
		try
		{
		Map<String,String> dataSet = new HashMap<String,String>();
		
		dataSet.put("A", "C");
        dataSet.put("B", "C");
        dataSet.put("C", "F");
        dataSet.put("D", "E");
        dataSet.put("E", "F");
        dataSet.put("F", "F");
		Count(dataSet);
		//for(Map.Entry<String, Integer> o : result.entrySet())
			//System.out.println("Mgr "+o.getKey()+" Count "+o.getValue());
		System.out.println("Result "+result);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void Count(Map<String,String> dataset)
	{
		Map <String,List<String>> reverseMap = new HashMap<String,List<String>>();
		for(Map.Entry<String, String> entry : dataset.entrySet())
		{
			String emp = entry.getKey();
			String mgr = entry.getValue();
			
			if(!mgr.equals(emp))
			{
				List<String> listMgr = reverseMap.get(mgr);
				if(listMgr==null)
					listMgr = new ArrayList<String>();
				listMgr.add(emp);
				
				reverseMap.put(mgr, listMgr);
			}
		}
				for(String magr : dataset.keySet())
					countUtil(magr,reverseMap);
	}
	
	public static int countUtil(String mgr,Map <String,List<String>> reverseMap)
	{
		//Map <String,Integer> out = new HashMap<String,Integer>();
		int count = 0;
		
		if(!reverseMap.containsKey(mgr))
		{
			result.put(mgr, 0);
		}
		else if(result.containsKey(mgr))
			count = result.get(mgr);
		
		else
		{
			List<String> temp = reverseMap.get(mgr);
			count = temp.size();
			for(String againtemp : temp)
				count+=countUtil(againtemp,reverseMap);
			result.put(mgr, count);
		}
		return count;
	}
}