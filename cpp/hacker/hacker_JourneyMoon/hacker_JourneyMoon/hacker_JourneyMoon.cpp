// hacker_JourneyMoon.cpp : Defines the entry point for the console application.
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
#define loopint(i,j,n) for(int i=(j);i<((int)n);i++)
#define INT_MIN -99999;
#define RANGE 100000;
typedef long long ll;
using namespace std;

vector<int> al[10000];
bool visit[10000];
int country[10000];
int n,m;
int c=0;

void dfs(int node,int c)
{
    visit[node]=true;
    for(int i=0;i<al[node].size();i++)
    {
        if(!visit[al[node][i]])
        {
			country[c]++;
            dfs(al[node][i],c);
            
        }
    }
}

int _tmain(int argc, _TCHAR* argv[]) {
    int x,y;
    cin>>n>>m;
    for(int i=0;i<m;i++)
    {
        cin>>x>>y;
        al[x].push_back(y);
        al[y].push_back(x);
    }
    loopint(i,0,n)
		country[i]=0;
	int country_count=0;
	loopint(i,0,n)
	{
		if(!visit[i])
			{
				country[country_count]++;
				dfs(i,country_count);
				country_count++;
		}
	}
	int co=0;
	loopint(i,0,n)
	{
		if(country[i]==0)
		{
			co = i-1;
			break;
		}
	}
	int pro,sum = 0;
	loopint(i,0,co+1)
	{	
		pro =0;
		loopint(k,i+1,co+1)
		{
			pro = country[i]*country[k];
			sum = sum+pro;
		}
	}
	std::cout<<sum;
    getch();
    return 0;
}

