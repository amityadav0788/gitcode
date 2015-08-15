// iRageCapital_BruceChocolate.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <algorithm>
#include <math.h>
using namespace std;


int compar(const void *a, const void *b) {
    int ia = *((int*)a);
    int ib = *((int*)b);
    return ia - ib;
}

int gcd(int a, int b)
{
    a = a % b;
    if (a == 0)
    {
        return b;
        b = b % a;
    }
    if (b == 0)
    {
        return a;
    }
}

int _tmain(int argc, _TCHAR* argv[])
{
	int T;
	std::cin >> T;
	while(T!=0)
	{
		int N,P;
		std::cin >> N >> P;
		int ** pair = (int**)malloc(sizeof(int*)*P);
		for(int i=0;i<P;i++)
			pair[i] = (int*)malloc(sizeof(int)*2);
			
		int * input = (int*)malloc(sizeof(int)*N-1);
		for(int i=0;i<N-1;i++)
		{
			std::cin >> input[i];
		}
		for(int j=0;j<P;j++)
		{
			std::cin >> pair[j][0] >> pair[j][1];
		}
		std::cout << pair[0][0]-1 << " " << pair[0][1]-1 << endl;
		//qsort(pair, P*2, 2*sizeof(int), compar);
		int out = 0;
		std::cout << pair[0][0]-1 << " " << pair[0][1]-1 << endl;
		std::cout<<"gcd of no "<<input[pair[0][0]-1] << " "<<input[pair[0][1]-1] << " "<<gcd(input[pair[0][0]-1],input[pair[0][1]-1]);
		out = gcd(input[pair[0][0]-1],input[pair[0][1]-1]);
		for(int i=1;i<P;i++)
		{
			int k = i,pre = 0,max = 0;
			if(pair[i][0] == pair[i--][0])
			{
				max = std::max(max,gcd(input[pair[i][0]-1],input[pair[i][1]-1]));
			}
			else
			{
				out = out+max;
				max = gcd(input[pair[i][0]-1],input[pair[i][1]-1]);
			}
		}
		std::cout<<out<<endl;
		int k;
		std::cin>>k;
		T--;
	}
	return 0;
}

