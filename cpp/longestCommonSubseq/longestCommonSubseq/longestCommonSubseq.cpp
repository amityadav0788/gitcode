// longestCommonSubseq.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <string>
#include <cstdlib>

using namespace std;


void lcs(string X, string Y, int x, int y)
{
	int** temp_array = new int*[x+1];
		for(int k = 0; k < x; ++k)
			temp_array[k] = new int[y+1];

	for(int i =0;i < x;i++)
	{
		for(int j =0;j<y;j++)
		{
			if(i==0 || j==0)
				temp_array[i][j] = 0;
			else if(X[i-1] == Y[j-1])
				temp_array[i][j] == temp_array[i-1][j-1] + 1;
			else temp_array[i][j] = max(temp_array[i-1][j],temp_array[i][j-1]);
		}

		print_lcs(temp_array,x,y)
	}
}

void print_array(int **array,int x, int y)
{

}

int main(array<System::String ^> ^args)
{
    string string1, string2;
	cout << "input both strings";
	getline (cin,string1);
	getline (cin,string2);

	int length1 = string1.length();
	int length2 = string2.length();

	lcs(string1,string2,length1,length2);

	return 0;
}
