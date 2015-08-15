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

char* substring(char*, int, int);
int _tmain(int argc, _TCHAR* argv[])
{
	char string[100], *pointer;
   int position = 1, length = 1, temp, string_length;
 
   //printf("Enter a string\n");
   gets(string);
 
   temp = string_length = strlen(string);
 
   //printf("Substring of \"%s\" are\n", string);
   int count = 0;
   while (position <= string_length)
   {
      while (length <= temp)
      {
         pointer = substring(string, position, length);
         //printf("%s\n", pointer);
		 std::cout<<pointer<<endl;
		 std::cout<<strlen(pointer)<<endl;
		 int count_temp=0;
		 if(strlen(pointer)==1)
			 count++;
		 else
		 {
			 for(int i =1;i<strlen(pointer);i++)
			 {
				 if(pointer[i]<pointer[i-1])
				 {
					count_temp = 0;
					break;
				 }
				 count_temp++;
			 }
			 if(count_temp!=0)
				 count++;
		 }
         free(pointer);
         length++;
      }
      temp--;
      position++;
      length = 1;
   }
   std::cout<<count;
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

