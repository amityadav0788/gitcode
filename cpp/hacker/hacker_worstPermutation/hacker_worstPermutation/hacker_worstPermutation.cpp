// hacker_worstPermutation.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <conio.h>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	long long int N,K;
	std::cin >> N >>K;
	long long int *list = (long long int*)malloc(sizeof(long long int)*N);
	for(long long i=0;i<N;i++)
		std::cin>>list[i];

	for(long long int j=0;j<N,K>0;j++)
	{
		long long int big_pos = distance(list+j,max_element(list+j,list+N-1));

		std::cout <<"big pos "<<big_pos << "\n";
		std::cout <<"list j "<<list[j] << "\n";
		std::cout <<"list bigpos "<<list[j+big_pos] << "\n";
			if(list[j]<list[j+big_pos])
		{
			long long int swap;
			swap = list[j];
			list[j] = list[j+big_pos];
			list[j+big_pos] = swap;
			K--;
		}
	}

	for(long long int i=0;i<N;i++)
		std::cout << list[i] << " ";
    
	getch();

	return 0;
}

