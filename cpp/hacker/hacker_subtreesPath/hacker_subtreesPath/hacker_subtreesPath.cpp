// hacker_subtreesPath.cpp : Defines the entry point for the console application.

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

#define INT_MIN -99999;
typedef long long ll;
using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	 ll N,Q;
	 std::queue<ll> results;
	
	std::cin >> N;
	
	ll **graph = (ll**)malloc(sizeof(ll*)*N);
	loop(i,1,N)
		graph[i] = (ll*)malloc(N*sizeof(ll));

	ll *value = (ll*)malloc(sizeof(ll)*N);
	loop(i,1,N)
		value[i] = 0;
		
	loop(i,1,N)
	{
		loop(j,1,N)
			graph[i][j] = 0;
	}

	loop(k,1,N-1)
	{
		int x,y;
		std::cin >> x >> y;
		if(x==1)
		{
			graph[x][y] = 1;
			graph[y][x] = -1;
			//parent[y] = x;
		}
		else if(y==1)
		{
			graph[y][x] = 1;
			graph[x][y] = -1;
			//parent[x] = y;
		}
		else
		{
			loop(l,1,N)
			{
				if(graph[x][l] == -1)
				{
					graph[x][y] = 1;
					graph[y][x] = -1;
					//parent[y] = x;
					break;
				}
				if(graph[y][l] == -1)
				{
					graph[y][x] = 1;
					graph[x][y] = -1;
					//parent[x] = y;
					break;
				}
			}
			
		}
	}
	std::cin >> Q;
	std::stack<ll> childs;
	loop(q,1,Q)
	{
		char* query;
		ll a,b;
		std::cin >> query >> a >> b;
		char add_string[] = "add";
		char max_string[] = "max";
		if(strcmp(query,add_string)==0)
		{
			childs.push(a);
			while(childs.size())
			{
				loop(i,1,N)
				{
					if(graph[a][i]==1)
					{
							value[i]+=b;
							childs.push(i);
					}
				}
				a = childs.top();
				childs.pop();
			}
			value[a]+=b;
		}

		if(strcmp(query,max_string)==0)
		{
			ll max1 =INT_MIN;
			ll max2 =INT_MIN;
			while(a!=b)
			{
			loop(i,1,N)
				{
					if(graph[a][i] == -1)
					{
						max1 = max(value[a],max1);
						a = i;
					}
					if(graph[b][i] == -1)
					{
						max2 = max(value[b],max2);
						b = i;
					}
				}
			}
			results.push(max(max1,max2));
		}
	}
	while(results.size()!=0)
		{
			std::cout<<results.front()<<"\n";
			results.pop();
	}
	getch();
	return 0;
}

