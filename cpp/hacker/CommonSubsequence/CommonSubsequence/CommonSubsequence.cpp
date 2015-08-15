// earth_sortedsubstring.cpp : Defines the entry point for the console application.

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <conio.h>
#include <string.h>
using namespace std;

int lcs(char*,char*, int, int);
int _tmain(int argc, _TCHAR* argv[])
{
	int T;
	std::cin>>T;
	while(T!=0)
	{
	char string1[100000];
	char string2[100000];
   int string1_length;
   int string2_length;
 
   //printf("Enter a string\n");
   std::cin >> string1;
   std::cin >> string2;
   string1_length = strlen(string1);
    string2_length = strlen(string2);
	std::cout<<string1_length<<" "<<string2_length;
	int l = lcs(string1,string2,string1_length,string2_length);

	if(l!=0)
		std::cout<<"YES";
	else
		std::cout<<"NO";
	T--;
	}
	int j;
	std::cin>>j;
}

int lcs( char *X, char *Y, int m, int n )
{
   int L[7][7];
   int i, j;
  for(int i=0;i<=m;i++)
 	{
 		L[i][0] = 0;
 	}
 	for(int i=0;i<=n;i++)
 	{
 		L[0][i] = 0;
 	}
   for (i=1; i<=m; i++)
   {
     for (j=1; j<=n; j++)
     {
       if (i == 0 || j == 0)
         L[i][j] = 0;
  
       else if (X[i-1] == Y[j-1])
         L[i][j] = L[i-1][j-1] + 1;
  
       else
         L[i][j] = max(L[i-1][j], L[i][j-1]);
     }
   }
    
   return L[m][n];
}
