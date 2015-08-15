// hacker_supermanDiwali.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <conio.h>
#define loop(i,j,n) for(int i=(j);i<((int)n);i++)
#define INT_MIN -999;
using namespace std;


int getmax(int **a,int *m,int i, int j, int I, int N);

int _tmain(int argc, _TCHAR* argv[])
{
	int H,N,I,result=-1;
	std::cin >> N >> H >> I;
	
	int **persons = (int**)malloc(sizeof(int*)*N);
	loop(i,0,N)
		persons[i] = (int*)malloc(H*sizeof(int));
    
	loop(i,0,N)
	{
		loop(j,0,H)
			persons[i][j] = 0;
	}

	int **dynamic_persons = (int**)malloc(sizeof(int*)*N);
	loop(i,0,N)
		dynamic_persons[i] = (int*)malloc(H*sizeof(int));
	loop(i,0,N)
	{
		loop(j,0,H)
			dynamic_persons[i][j] = 0;
	}

	loop(i,0,N)
	{
		int PF;
		std::cin >> PF;
		loop(j,0,PF)
		{
			int Floor;
			std::cin >> Floor;
			persons[i][Floor-1]++;
		}
	}
	int *max = (int*)malloc(sizeof(int*)*H);
	loop(i,0,H)
		max[i] = 0;

	int temp = INT_MIN;
	loop(i,0,N)
	{
		dynamic_persons[i][0] = persons[i][0];
		if(dynamic_persons[i][0] > max[0])
		{
			max[0] = dynamic_persons[i][0];
		}
	}
	loop(j,1,I)
	{
	loop(i,0,N)
	{
			dynamic_persons[i][j] = dynamic_persons[i][j-1]+persons[i][j];
			if(dynamic_persons[i][j] > max[j])
			{
				max[j] = dynamic_persons[i][j];
			}
		}
	}
	loop(j,I,H)
	{
		loop(i,0,N)
		{
			dynamic_persons[i][j] = getmax((int **)dynamic_persons,(int*)max,i,j,I,N)+persons[i][j];
			if(dynamic_persons[i][j] > max[j])
			{
				max[j] = dynamic_persons[i][j];
			}
		}

	}
	loop(i,0,N)
	{
		if(dynamic_persons[i][H-1] > result)
			result = dynamic_persons[i][H-1];
	}
	std::cout << result ;
	getch();
	return 0;
}

int getmax(int *a[],int m[],int i, int j, int I, int N)
{
	int returnvalue = INT_MIN;
	if(m[j-I] >= a[i][j-1])
		returnvalue = m[j-I];
	else returnvalue = a[i][j-1];
	return returnvalue;
}