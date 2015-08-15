// earth_sortedsubstring.cpp : Defines the entry point for the console application.

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <conio.h>
#include <string.h>
#define loop(i,j,n) for(int i=j;i<n;i++)
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	int N,Q;
	std::cin>>N>>Q;
	long* array1 = (long*)malloc(sizeof(long)*N);
	long* array2 = (long*)malloc(sizeof(long)*N);

	loop(i,0,N)
	{
		std::cin >> array1[i];
	}
	loop(i,0,N)
	{
		std::cin>>array2[i];
	}
	loop(i,0,N)
	{
		//std::cout<<array1[i]<<" "<<array2[i];
	}
	while(Q!=0)
	{
		int scene,left,right,l,r;
		long sum = 0;
		std::cin >> scene >> left >> right;
		l = left-1;
		r = right-1;
		if(scene==1)
		{
			int i=l;
			while(i<=r)
			{
				if(i+1 <= r)
				{
					sum = sum + array1[i]+array2[i+1];
				}
				else 
				{
					sum = sum+array1[i];
				}
				i = i+2;
			}

		}
		else if(scene==2)
		{
			int i=l;
			while(i<=r)
			{
				if(i+1 < r)
					sum = sum + array2[i]+array1[i+1];
				else sum = sum+array2[i];
				i = i+2;
			}

		}
		std::cout<<sum<<endl;
		Q--;
	}
	int test;
	std::cin>>test;
	return 0;
}



