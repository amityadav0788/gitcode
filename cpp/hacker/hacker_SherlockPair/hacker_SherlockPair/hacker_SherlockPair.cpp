// hacker_SherlockPair.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <stack>
#include <queue>
#include <conio.h>
#define loop(i,j,n) for(long long i=(j);i<=((long long)n);i++)
#define loopint(i,j,n) for(int i=(j);i<=((int)n);i++)
#define INT_MIN -99999;
#define RANGE 100000;
typedef long long ll;
using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	int T;
	ll N,ct;
	std::cin >> T;
	ll* output = new ll[T];
		loopint(i,0,T)
			output[i]=0;
	if(T!=0)
	{
		std::cin >> N;
		ct=0;
		if(N!=0)
		{
			ll* count = new ll[10000];
			loop(i,0,N)
				count[i] = 0;
			ll* members = new ll[N];
			loop(i,0,N)
				std::cin >> members[i];
			loop(j,0,N)
				count[members[j]]++;
			loop(i,0,10000) 
			{
				if(count[i]!=0)
					{
						ct = ct+(count[i]*(count[i]-1));
					}
			}

		}
		else ct = 0;
		output[T] = ct;
		T--;
	}
	loopint(i,0,T)
		std::cout<<output[T]<<"\n";
	getch();
	return 0;
}