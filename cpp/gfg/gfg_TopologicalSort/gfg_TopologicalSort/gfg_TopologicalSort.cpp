// gfg_TopologicalSort.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stack>
#include <list>
#include <iostream>
#include <conio.h>
#define loop(i,j,n) for(int i =j;i<n;i++)

using namespace std;

class Graph
{
	int V; // number of vertices

	list<int> *adj; //adaphjacecny list of gr

public:
	Graph(int n)
	{
		V = n;
		adj = new list<int>[n];
	}

	void addedge(int u, int v)
	{
		adj[u].push_back(v);
	}
	void TopologicalSort();
	void TopologicalSortUtil(int i,bool visited[],stack<int> &Stack);
};

void Graph::TopologicalSortUtil(int v,bool visited[],stack<int> &Stack)
{
	visited[v] = true;

	list<int>:: iterator i;
	for(i=adj[v].begin();i!=adj[v].end();++i)
	{
		if(!visited[*i])
			TopologicalSortUtil(*i,visited,Stack);
	}
	Stack.push(v);

}

void Graph::TopologicalSort()
{
	stack<int> Stack;

	bool *visited = new bool[V];
	loop(i,0,V)
		visited[i]=false;

	loop(i,0,V)
	{
		if(visited[i]!=true)
		{
			TopologicalSortUtil(i,visited,Stack);
		}
	}
	while(!Stack.empty())
	{
		std::cout<<Stack.top();
		Stack.pop();
	}
}

int _tmain(int argc, _TCHAR* argv[])
{
	Graph g(6);
	g.addedge(0,1);
	g.addedge(2,3);
	g.addedge(4,5);
	g.addedge(4,0);
	g.TopologicalSort();
	getch();
	return 0;
}

