// reverse_number.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	
	int number;
		cout << "input number";
	cin >> number;
	int hold;
	int output = 0;
	
	int count = 0;
	while(number != 0)
	{
		output*=10;
		output = output+number%10;
		number = number/10;
	}

	cout << "reverse is %d" << output;
	cin >> hold;
	return 0;
}

