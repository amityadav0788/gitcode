// earth_BestInternetBrowser.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <conio.h>
using namespace std;

int main()
{
	int T;
	std::cin>>T;
	while(T!=0)
	{
		string input;
		std::cin>>input;
		int den = input.length();
		int num =0;
		int pos = input.find('.');
		string temp = input.erase(0,pos+1);
		std::cout<<"temp : "<<temp<<endl;
		int pos1 = temp.find('.');
		string temp1 = temp.substr(0,pos1);
		std::cout<<"temp1 : "<<temp1<<endl;
		for(int i=0;i<temp1.length();i++)
		{
			if((temp1[i]!='a')&&(temp1[i]!='e')&&(temp1[i]!='i')&&(temp1[i]!='o')&&(temp1[i]!='u'))
			{
				num++;
			}
		}
		std::cout<<num+4<<"/"<<den;
		T--;
	}
	getch();
    return 0;
}

