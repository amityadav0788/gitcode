#include "stdafx.h"
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <conio.h>
#include <algorithm>
#include<stdlib.h>
using namespace std;

void fun();
int _tmain(int argc, _TCHAR* argv[]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */ 
	union A{
		long int y[5];
		union B{
			double g;
			union C{
				int k;
				union D{
					char ch;
					int x[5];
				}s;
			}a;
			}b;
		}*p;
		p = (union A *) malloc (sizeof(union A));
		p->b.a.k = 15;
		printf("%d %d ",p->b.a.s.x[0],p->y[0]);
	getch();
    return 0;
}

void fun()
{
	char c;
	if((c=getchar())!='\n')
		fun();
	printf("%c",c);
}