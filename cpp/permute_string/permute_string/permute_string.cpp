// permute_string.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <string>

using namespace std;

void permute(string a,string b)
{
	int len = b.length();
	if(len == 0)
		cout << "\n"<<a;
	else
	{
		for(int i =0 ; i< len; i++)
			permute(a+b.at(i),b.substr(0,i)+b.substr(i+1,len));
	}
}

int main(array<System::String ^> ^args)
{
    string a = "amit";
	permute("",a);

	int j;
	cin >> j;
	return 0;
}
