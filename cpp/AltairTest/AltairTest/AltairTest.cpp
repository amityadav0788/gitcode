// AltairTest.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <stdio.h>
using namespace std;

struct one
{
	one()
	{
		testme();
	}
	void testme()
	{
		std::cout<<"hi";
	}
};
struct two:one
{
	two()
	{
		testme();
	}
	void testme()
	{
		std::cout<<"hello";
	}
};
int _tmain(int argc, _TCHAR* argv[])
{
	two *t = new two;

	int j;
	std::cin>>j;
	return 0;
}

