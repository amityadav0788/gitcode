// finding_dist_tree.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <cstddef>

using namespace std;

struct BTnode
{
    int data;
    struct BTnode *left;
    struct BTnode *right;
}*root;

class BTree
{

	BTnode *root;
public:
	void insert(BTnode *,BTnode *);
	void dist(int data1,int data2);
	BTree()
	{
	  root = NULL;
	}
};

void BTree::insert(BTnode *root,BTnode *node)
{
	
	if(root == NULL)
	{
		root = new BTnode;
		root->left = node->left;
		root->right=node->right;
		root->data = node->data;
		return;
	}
	else if(root->left != NULL && root->right != NULL)
	{
		insert(root->left,node);

	}
	else if(root->left == NULL)
	{
		insert(root->left,node);
	}
	else insert(root->right,node);
}

int main(array<System::String ^> ^args)
{
	int a[] = {2,4,8,9};
	//BTnode *root = new BTnode();
	//root->data = a[0];
	BTree tree;
	BTnode *root = new BTnode;
	BTnode *temp = new BTnode;
	temp->data = 2;
	tree.insert(root,temp);
    return 0;
}
