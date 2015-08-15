// EmployeeRanking.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <string>
#include <conio.h>
#include <map>

using namespace std;

class HierarchyTreeNode
{
	friend class HierarchyTree;
private:
	string data;
	vector<HierarchyTreeNode> child;  // n-ary tree
public:
	HierarchyTreeNode()
	{
	}
	HierarchyTreeNode(string d)
	{
		data = d;
	}

	string getdata()
	{
		return data;
	}

	void addchild(HierarchyTreeNode &e)
	{
		child.push_back(e);
	}

	bool search(string name)
	{
		if(child.size()!=NULL)
		{
			for(int i = 0; i < child.size(); i++)
			{
				if(child.at(i).getdata()==name)
					return true;
			}
			return false;
		}
	}
};

class HierarchyTree
{
	HierarchyTreeNode* root;
public:
	HierarchyTree(){root = NULL;}
	HierarchyTree(string rootName){root->data = rootName;};
	HierarchyTreeNode* Root(){return root;}
	void addnode(HierarchyTreeNode newNode,HierarchyTreeNode &newNode1);
	HierarchyTreeNode* search(string data);
	int HierarchyTree::rank(HierarchyTreeNode &Node);
};


void HierarchyTree::addnode(HierarchyTreeNode existNode,HierarchyTreeNode &newNode1)
{
	if(root==NULL)
	{
		*root = existNode;
		return;
	}
	else
	{
		existNode.addchild(newNode1);
	}
}

HierarchyTreeNode* HierarchyTree::search(string data)
{
	HierarchyTreeNode* temp;
	temp = root;
	if(temp==NULL)
	{
		return NULL;
	}
	while(temp->child.size()!=NULL)
		{
			for(int i = 0; i < temp->child.size(); i++)
			{
				if(temp->child.at(i).getdata()==data)
					return temp;
				//else temp = temp->child.at(i);
			}
		}
}

int HierarchyTree::rank(HierarchyTreeNode &Node)
{
	int count = 0;
	while(Node.child.size()!=0)
	{
		for(int i = 0; i < Node.child.size(); i++)
			{
				//count+=this.rank(Node.child.at(i));
			}
		count+=Node.child.size();
	}
	return count;
}

int _tmain(int argc, _TCHAR* argv[])
{
	//multimap<string, string> hierarchy;
	//vector<string> emp;
	//HierarchyTree tree = HierarchyTree();

	while(1)
	{
		int scene;
		std::cin >> scene;
		if(scene==1)
		{
			string input1,input2;
			std::cin>>input1>>input2;
			//emp.push_back(input1);
			//hierarchy.insert(std::make_pair(input2,input1));
			if(input2.compare("NULL")==0)
			{
				HierarchyTree tree = HierarchyTree(input1);
			}
			HierarchyTreeNode node = HierarchyTreeNode(input1);
			HierarchyTreeNode* temp = tree.search(input2);
			tree.addnode(*temp,node);
		}
		else if(scene ==2)
		{
			string input1;
			std::cin>>input1;
			HierarchyTreeNode* temp = tree.search(input1);
			std::cout<<tree.rank(*temp);
		}
		else if(scene ==3)
		{
			string input1;
			std::cin>>input1;
		}
		else if(scene ==4)
		{
			string input1,input2;
			std::cin>>input1>>input2;
		}
		else if(scene ==5)
		{
			string input1;
			int K;
			std::cin>>input1>>K;
		}
	}
	return 0;
}

