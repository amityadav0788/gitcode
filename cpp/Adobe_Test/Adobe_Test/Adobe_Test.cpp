// Adobe_Test.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <stdlib.h>
#include <stack>
using namespace std;

int isBracketMatched(string);
int _tmain(int argc, _TCHAR* argv[])
{
	
	int j = isBracketMatched("{}[");
	std::cout<<j;
	int i;
	std::cin>>i;
	return 0;
}

int isBracketMatched(string s) {
       stack<char> stk;

       for(int i = 0 ; s[i] != '\0' ; i++) {
               switch(s[i]) {
               case '{' :
               case '[' :
               case '(' :
                       stk.push(s[i]);
                       break;
               case '}' :
                       if(stk.top() == '{') {
                               stk.pop();
                       } else {
                               stk.push(s[i]);
                       }
                       break;
               case ']' :
                                       if(stk.top() == '[') {
                                               stk.pop();
                                       } else {
                                               stk.push(s[i]);
                                       }
                                       break;
               case ')' :
                                       if(stk.top() == '(') {
                                               stk.pop();
                                       } else {
                                               stk.push(s[i]);
                                       }
                                       break;
               }
       }

       if(stk.empty()) {
               return 1;
       } else {
               return 0;
       }
}


