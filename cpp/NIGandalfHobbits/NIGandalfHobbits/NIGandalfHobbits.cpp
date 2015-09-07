// NIGandalfHobbits.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <algorithm>

using namespace std;

/* Remove additional printf/scanf statements before submission */
int GetMaxPositions(int m, int n, int val[100][100])
{
    /* Your code goes here */
	int pre[100][100];
	int i, j;
	for(i = 0; i < m; ++i)
        for(j = 0; j < n; ++j)
				pre[i][j]=0;
	for(i=m-1;i>=0;i--)
	{
		int row_temp = 0; // to get row wise optimum value
		int col_temp=0;   // to get column wise optimum value
		int l = i+1;   // counter that helps to get col_temp
		
		for(j=n-1;j>=0;j--)
		{
			row_temp = pre[i][j];  // to get row wise optimum value
			int *a = new int(n-1-j+1);
			/*
			loop and auxilary array to get optimum value in current row since there are only 3 rows so no loop required 
			on i
		*/
			for(int size_a =0 ;size_a<n-1-j;size_a++)
				a[size_a]=-1;
			int k=0;
			while(k<=n-1-j && val[i][j]<=val[i][j+k])
			{
				a[k] = pre[i][j+k]+1;
				k++;
			}
			sort(a,a+n-1-j+1);
			col_temp = a[n-1-j];
		/*
			no loop required for rows can be done in max 3 condition check to find max value
		*/
		while(l<=m-1 && val[i][j]>=val[l][j])
			{
				if(row_temp < pre[l][j])
					row_temp = pre[l][j]+1;
				l++;
			}
		//cout<<"values "<<row_temp << " "<<col_temp<<endl;
		pre[i][j] = (row_temp>col_temp)?row_temp:col_temp; // compare row wise optimum and column wise optimum
		//cout<<"************ = ";
		//cout<<pre[i][j]<<endl;
		}
	}
	int ret=-1;
	for(i = 0; i < m; ++i)
        {
			for(j = 0; j < n; ++j)
			{
				if(pre[i][j]>ret)
				{
					ret = pre[i][j];
				}
			}
	}
            
    return ret;
}

int _tmain(int argc, _TCHAR* argv[])
{
	int m, n;
    int i, j;
    int val[100][100];
    cin>>m>>n;
    for(i = 0; i < m; ++i)
        for(j = 0; j < n; ++j)
            cin>>val[i][j];
    cout<<GetMaxPositions(m, n, val);
	int hold;
	cin>>hold;
    return 0;
}

