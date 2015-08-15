// bst_impl.cpp : main project file.

#include "stdafx.h"
#include <stdio.h>
#include <iostream>

using namespace std;

class BSTnode
{
public:
	BSTnode *left;
	BSTnode *right;
	int data;

	BSTnode(int n)
	{
		right=left = NULL;
		data = n;
	}

};

class BST_Tree
{

public:
	void insert_tree(BSTnode *, BSTnode *);
	void inorder_traversal(BSTnode *);
	void preorder_traversal(BSTnode *);
	bool search_tree(BSTnode *,int);
};

void BST_Tree::insert_tree(BSTnode *root, BSTnode *temp)
{
	while(root->left!=NULL && root->right!=NULL)
	{
		if(root->data < temp->data)
			insert_tree(root->left, temp);
		else insert_tree(root->right, temp);
	}

	if(root->data < temp->data)
		root->left = temp;
	else root->right = temp;
}

bool BST_Tree::search_tree(BSTnode *root, int number)
{
	return true;
}

int main(array<System::String ^> ^args)
{
    int number_nodes;
	cout << "number of nodes";
	cin >> number_nodes;
	int *A = new int(number_nodes);
	BST_Tree tree;
	BSTnode *root;

	if(number_nodes > 0)
	{
		
		cout << "enter numbers";

		cin >> A[0];
		root = new BSTnode(A[0]);

		for(int i =1; i<number_nodes;i++)
		{
			cin >> A[i];

			BSTnode *temp;
			temp = new BSTnode(A[i]);
			tree.insert_tree(root,temp);
		}
	}
	char s;
	cout<<"press s, if u wanna search";
	cin >> s;

	while(s=='s')
	{
		int number;
		cout << "enter number";
		cin >> number;
		bool found = tree.search_tree(root,number);

		if(found)
			cout << "got it !!";
		else cout << "sorry mate";

		cout<<"press s, if u wanna search";
		cin >> s;
	}
}
