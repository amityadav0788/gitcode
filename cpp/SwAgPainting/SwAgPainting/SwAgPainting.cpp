// SwAgPainting.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	long long T,Weight[26];
	long long N;
	std::cin>>T;
	while(T--)
	{
		std::cin>>N;
		long long one_sum=0;
		for(int j=0;j<26;j++)
			{
				std::cin>>Weight[j];
				one_sum+=Weight[j];
		}
		long long Net_N = 12*N;
		long long output = 0;
		long long occ=Net_N/26;
		int rem = Net_N%26;
		if(occ>1)
		{
			long long temp = occ-1;
			//long long temp_temp = (temp*temp+1)/2;
			while(temp>=0)
			{
				output=output+(one_sum+26*temp);
				temp--;
			}
			for(int k=0;k<rem;k++)
			{
				output = output+Weight[k]+occ;
			}
		}
		else if(occ==1)
		{
			output = one_sum;
			for(int k=0;k<rem;k++)
			{
				output = output+Weight[k]+occ;
			}
		}
		else
		{
			for(int k=0;k<rem;k++)
				output+=Weight[k];
		}
		std::cout<<output;
	
	}
	int i;
	std::cin>>i;
	return 0;
}

