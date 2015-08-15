// hacker_supermanDiwali.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <string>
#include <conio.h>
#define loop(i,j,n) for(int i=(j);i<((int)n);i++)
#define INT_MIN -999;
using namespace std;

int addChars(string s);
int CharAdded(string s,int i);
bool checkShuffle(string s);

int _tmain(int argc, _TCHAR* argv[])
{
	int T,result;
	std::cin >> T;
	while(T--)
	{
		string s;
		//std::getline (std::cin,s);
		std::cin >> s;
		if(checkShuffle(s))
			result = 0;
		else
		{
			result = addChars(s);
		}
		std::cout << result ;
	}
	getch();
	return 0;
}

int addChars(string s)
{
	int out = CharAdded(s,s.size());
	return out;
}

int CharAdded(string str,int n)
{
    int l, h, gap;
	int **table = (int **)malloc(sizeof(int *)*n);
	for(int i=0;i<n;i++)
	{
		table[i] = (int *)malloc(sizeof(int)*n);
	}
    for (int a = 0; a < n; ++a)
        for (int b = 0;b<n;++b)
		{
			table[a][b]=0;
		}	
    for (gap = 1; gap < n; ++gap)
        for (l = 0, h = gap; h < n; ++l, ++h)
            table[l][h] = (str[l] == str[h])? table[l+1][h-1] :
                          (min(table[l][h-1], table[l+1][h]) + 1);
 
    // Return minimum number of insertions for str[0..n-1]
    return table[0][n-1];
}

bool checkShuffle(string s)
{
	int count[26];
	memset(count, 0, sizeof(count));

for(int i = 0; i < s.size(); i++){
    count[s[i] - 'a']++;
}

int odds = 0;

for(int i = 0; i < 26; i++){
    if(count[i] % 2 == 1){
        odds++;
    }
}

return odds <= 1;
}