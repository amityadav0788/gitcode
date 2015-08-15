// earth_SubstringQueries.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <conio.h>
#include <string.h>
#include <iostream>
#define loop(i,j,n) for(int i =j;i<n;i++)
using namespace std;

bool anagram(char *,char *);
char *substring(char *string, int position, int length);
int _tmain(int argc, _TCHAR* argv[])
{
	int T;
	std::cin>>T;
	while(T!=0)
	{
		char input[10000];
		char *pointer;
		scanf("%s",input);
		//gets(input);
		int Q;
		std::cin>>Q;
		while(Q!=0)
		{
				int count = 0;
			int position = 1, length = 1, temp, string_length;
			temp = string_length = strlen(input);
			char test[10000];
			scanf("%s",test);
			int test_size = strlen(test);

			while (position <= string_length)
			{
				while (length <= temp)
				{
					pointer = substring(input, position, length);
					int pointer_size = strlen(pointer);
					if(pointer_size == test_size)
					{
						if(anagram(pointer,test))
						{
							count++;
							std::cout<<pointer<<" "<<test<<endl;
						}
					}
					free(pointer);
					length++;
				}
				temp--;
				position++;
				length = 1;
			
			}
			Q--;
			std::cout<<count<<endl;
		}
		T--;
	}
	getch();
	return 0;
}

char *substring(char *string, int position, int length)
{
   char *pointer;
   int c;
 
   pointer = (char*)malloc(length+1);
 
   if (pointer == NULL)
   {
      exit(1);
   }
 
   for (c = 0 ; c < length ; c++)
   {
      *(pointer+c) = *(string+position-1);      
      string++;   
   }
 
   *(pointer+c) = '\0';
 
   return pointer;
}

bool anagram(char *pointer, char *test)
{
	int count_pointer[256], count_test[256];
	loop(i,0,256)
	{
	   count_pointer[i] =0;
		count_test[i] = 0;
	}
	loop(i,0,strlen(pointer))
	{
		count_pointer[pointer[i]]++;
	}
	loop(i,0,strlen(test))
	{
		count_test[test[i]]++;
	}
	loop(i,0,256)
	{
		if(count_test[i]!=0)
		{
			if(count_test[i]>count_pointer[i])
				return false;
		}
	}
	return true;
}