// MoonFrogGridTraversal.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
//#define SIZE 5  //size of our grid area ,can also be more than 5X5
//#define MAX_PATH_SIZE 25   //we need to store the shortest path in an array/vector of x and y co-ordinates
#define NO_OF_DIRECTIONS 2  //we can traverse in four directions in this case, may be different in different grid problems
using namespace std;

int originalGrid[100][100]; //stores the input grid
bool flag[100][100]; //a boolean array.any value in this=true means that point in the grid has been seen at/before that time instant

int Size_N,Size_M,Size_K;
int direction[NO_OF_DIRECTIONS][2]={{0,1},{-1,0}}; // RIGHT , UP , LEFT ,BOTTOM -the order is important in the sense that
//RIGHT and BOTTOM must come before UP and LEFT as final point in lower right corner

bool finish=false; // a boolean variable to stop the graph traversal as soon as we reach the end point the first time
//this is required since there may be more than one path from start point to end point and we do not need to print all
//those paths.We just need the first shortest path and if we choose the direction array carefully DFS will ensure us that
//as soon as we reach the end point it is the shortest path indeed

struct Point
{                 //stores a grid/path point
int x;
int y;
};

//Point path[MAX_PATH_SIZE];//this array will store the final result of the shortest path
int nextX,nextY;

void init(int Size_N,int Size_M,int Size_K)
{
	for(int i=1;i<=Size_N;i++)
		for(int j=1;j<=Size_M;j++)
			originalGrid[i][j] = 0;
	int special_x,special_y;
	for(int k=0;k<Size_K;k++)
	{
		std::cin>> special_x >> special_y;
		originalGrid[special_x][special_y] = 1;
	}

}

/*void printPath(int pathIndex) //prints our path array . pathIndex=length of the path
{
for(int i=0;i<pathIndex;i++)
{
printf("(%d, %d)\n", path[i].x, path[i].y);
}
}*/

void dfs(int x,int y,int pathIndex,int Size_K,int Size_N,int Size_M,int *count)
{
	if(x==Size_N-1 && y==Size_M-1 && *count==Size_K) //we have found the best path/the end point , print it
{
pathIndex++;
//printPath(pathIndex);
//finish=true; //we do not need to continue with our graph traversal any more.we have found shortest path
return;
}

if(finish)  //this will only be true if any of the recursive calls had found the first end point.Then this is needed
return;  //to stop all other recursive sub-calls  from propagating furthers

for(int i=0;i<NO_OF_DIRECTIONS;i++) //we need to move in all four directions but in the specified direction
{
nextX=x+direction[i][0]; //calculating next point in the grid
nextY=y+direction[i][1];

//if it is a valid point, and it is not a wall and,it has not been traversed before
//all conditions need to be true in order to consider it as a part of the path
if(nextX>=0 && nextY >=0 && nextX<Size_N && nextY<Size_M && originalGrid[nextX][nextY]==0 && flag[nextX][nextY]==false)
{
flag[nextX][nextY]=true; // we mark this point as seen
//path[pathIndex].x=nextX; //stores the current grid point in the path array for shortest path .
//path[pathIndex].y=nextY; //this is because dfs will give us the shortest path
dfs(nextX,nextY,pathIndex+1,Size_K,Size_N,Size_M,(count)++); // recursive sub-call .this means we need to continue our graph traversal
//as we have still not reached end point.Note we have incremented pathIndex as now
//the current point is part of our shortest path and pathIndex is our pathLength
flag[nextX][nextY]=false;//we  mark this point as unseen so that some other recursive call can use
//this point in its path
}
}
}
void countPath(int Size_N,int Size_M,int Size_K)  //always for recursive functions keep a base function to which program control returns
{                        //after all the recursive calls finish
//we are starting our Depth-First Traversal from (0,0) and path length=1 as (0,0) is already part of path
	int count = 0;
	for(int i=0;i<Size_K;i++)
		dfs(1,1,0,i,Size_N,Size_M,&count);
}
//our execution starts from here
int _tmain(int argc, _TCHAR* argv[])
{
std:cin>>Size_N >> Size_M >> Size_K;
	init(Size_N,Size_M,Size_K); //initialize all our variables here
countPath(Size_N,Size_M,Size_K); //use this to find the shortest path
//system("pause");
return 0;
}

