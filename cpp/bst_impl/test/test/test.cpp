// test.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <conio.h>
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stack>
using namespace std;
static int summations=0;
long sum(long m,long n)
{
	summations++;
	return m+n;
}
//std::string  print_multiple_of_all_ones(int num);
int mult(string s);
int _tmain(int argc, _TCHAR* argv[])
{
	int a;
	string s;
	getline(cin,s);
	a=mult(s);
	std::cout<<a;
    int i;
	cin >> i;
	return 0;

}

int mult(string s)
{
	 std::stack<char> st;
    int res=0;
    for(int i=0;i<s.length();i++)
        {
        if((s[i]=='{')||(s[i]=='[')||(s[i]=='('))
            st.push(s[i]);
        else if(s[i]=='}')
            {
            if(st.top()=='{')
                {
                st.pop();
                res=1;
            }
            else 
			{res=0;break;}
        }
        else if(s[i]==']')
            {
            if(st.top()=='[')
            {
            st.pop();
            res=1;
            }
            else {res=0;break;}
        }
        else if(s[i]==')')
            {
            if(st.top()=='(')
                {
                st.pop();
                res=1;
            }
            else {res=0;break;}
        }
    }
 return res;
}



