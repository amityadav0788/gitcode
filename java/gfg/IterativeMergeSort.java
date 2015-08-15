/* IMPORTANT: class must not be public. */


 //* uncomment this if you want to read input.
import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class IterativeMergeSort {

	static void MergeSort(int[] array,int n)
	{
		int currentSize;
		int left_start;
		for(currentSize = 1;currentSize<n-1;currentSize=2*currentSize)
		{
			for(left_start = 0;left_start<n-1;left_start+=2*currentSize)
			{
				int mid = left_start+currentSize-1;
				int right_end = Math.min(left_start+2*currentSize-1,n-1);
				merge(array,left_start,mid,right_end);
			}
		}
	}
	
	static void merge(int[] array,int left_start,int mid,int right_end)
	{
		int l=mid-left_start+1;
		int r = right_end-mid;
		int left[] = new int[l];
		int right[] = new int[r];;
		
		for(int i=0;i<l;i++)
			left[i] = array[i+left_start];
		for(int j =0;j<r;j++)
			right[j] = array[mid+j+1];
			
		int i1=0;
		int j1=0;
		int k=l;
		while(i1<l && j1<r)
		{
			if(left[i1]<right[j1])
			{
				array[k]=left[i1];
				i1++;
			}
			else if(left[i1]>right[j1])
			{
				array[k]=right[j1];
				j1++;
			}
			k++;
		}
		while(i1<l)
		{
			array[k] = left[i1];
			i1++;
			k++;
		}
		while(j1<r)
		{
			array[k] = right[j1];
			j1++;
			k++;
		}
	}
	
	static void printarray(int[] array)
	{
		for(int i=0;i<array.length;i++)
			System.out.println(array[i]);
	}

    public static void main(String args[] ) throws Exception {
        
         int array[] = {3,5,7,2,8,9};
		 printarray(array);
		 MergeSort(array,array.length);
		 printarray(array);
	}
}
