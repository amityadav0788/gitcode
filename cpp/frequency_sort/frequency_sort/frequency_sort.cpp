// frequency_sort.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <algorithm>    // std::sort
#include <cstdlib>

using namespace std;

class BSTnode
{
public:
	BSTnode *left;
	BSTnode *right;
	int data;
	int freq;

	BSTnode(int n)
	{
		left=right= NULL;
		data = n;
		freq = 0;
	}
};

struct Frequency
{
	int data;
	int freq;
};

class BST_Tree
{
public:
	BSTnode* insert_BST(BSTnode *root, BSTnode *temp);
	void store(BSTnode *root, Frequency count[], int *);
};

BSTnode* BST_Tree::insert_BST(BSTnode *root, BSTnode *temp)
{
	if(root == NULL)
		return temp;
	if(root != NULL && root->data == temp->data)
		{
			root->freq++;
			
		}
	
	else if(temp->data < root->data)
		root->left = insert_BST(root->left,temp);
	else if(temp->data > root->data)
		root->right = insert_BST(root->right,temp);
	return root ;
}

void BST_Tree::store(BSTnode *root, Frequency count[],int *index)
{
	if(root == NULL)
		return ;

	store(root->left,count,index);

	count[*index].freq = root->freq;
	count[*index].data = root->data;
}

int cmp(const void *a, const void *b)
{
	 return ( (*(const Frequency*)b).freq - (*(const Frequency*)a).freq );
}

void print(int arr[],int n)
{
	cout << "\n printing";
	for (int i = 0; i<n; i++)
	{
		cout << arr[i] <<" ";
	}
}

int main(array<System::String ^> ^args)
{
	int A[] = {4,4,2,3,1,1,4,3,1,4};
	int number = sizeof(A)/sizeof(A[0]);
	BSTnode *root;
	BST_Tree tree;

	if(number > 0)
	{
		root = new BSTnode(A[0]);
		root->freq = 1;
	}

	for(int i=1;i<number;i++)
	{
		BSTnode *temp;
		temp = new BSTnode(A[i]);
		temp->freq = 1;

		root = tree.insert_BST(root,temp);
	}

	Frequency count[10];

	//count = (Frequency*)malloc(sizeof(Frequency)*number);
	int index = 0;
	tree.store(root,count,&index);

	qsort(count,index,sizeof(count[0]),cmp);

	int l = 0;
	for(int j =0;j<index;j++)
	{
		for(int k = count[j].freq;k>0;k--)
		{
			A[l++] = count[j].data;
		}
	}

	print(A,number);

	int roko;
	cin >> roko;
}
