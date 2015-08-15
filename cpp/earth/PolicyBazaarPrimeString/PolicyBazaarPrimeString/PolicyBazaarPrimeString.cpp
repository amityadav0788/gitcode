// PolicyBazaarPrimeString.cpp : Defines the entry point for the console application.
//

// EmployeeRanking.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <string>
#include <conio.h>
#include <math.h>
#include <map>
#define loop(i,j,n) for(i=j;i<n;i++)
using namespace std;

bool isPrime(long);
bool isProime(int);

int _tmain(int argc, _TCHAR* argv[])
{
	int T;
	std::cin>>T;
	while(T!=0)
	{
		long count[26];
		int i;
		loop(i,0,26)
			count[i]=0;
		string input;
		std::cin>>input;
		long length = input.length();
		bool flag=true;
		if(!isPrime(length))
		{
			flag = false;
		}
		else
		{
			int j,k,l;
			loop(j,0,length)
				count[input[j]-'a']++;
			int distinct_number=0;
			loop(k,0,26)
			{
				if(count[k]>0)
					distinct_number++;
			}
			if(isPrime(distinct_number))
			{
				loop(k,0,26)
				{
					cout<<"k,count"<<k<<" "<<count[k]<<endl;	
					if(!isPrime(count[k])&&count[k]>0)
					{
						flag = false;
						break;
					}
				}
			}
			else flag = false;
		}
		if(flag)
			std::cout<<"YES"<<endl;
		else std::cout<<"NO"<<endl;
		T--;
	}
	int i;
	std::cin>>i;
	return 0;
	
}

bool isPrime(long len)
{
	int i;
	if(len == 1)
		return 0;

    if (len==2)
        return 1;

    if (len%2==0)
        return 0;
	long double j = sqrt((long double)len);
    for (i=3;i<=j;i+=2)
        if (len%i==0)
            return 0;

    return 1;
}

bool isPrime(int len)
{
	int i;
	if(len == 1)
		return 0;
    if (len==2)
        return 1;

    if (len%2==0)
        return 0;
	float j = sqrt((float)len);
    for (i=3;i<=j;i+=2)
        if (len%i==0)
            return 0;

    return 1;
}