// gfg_EmployeeHierarchy.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <string>
#include <conio.h>
#include <map>

using namespace std;

class Employee
{
private:
	string name;
	int id;
	int salary;
	int rating;
	string boss;
	int designation;

public:
	Employee(string n,int i,int s, int r,string b,int d)
	{
		name = n;
		id = i;
		salary = s;
		rating = r;
		boss =b;
		designation =d;
	}

	string getname()
	{
		return name;
	}
	string getboss()
	{
		return boss;
	}
};

class HierarchyTreeNode
{
private:
	string data;
	vector<HierarchyTreeNode> child;
public:
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
	HierarchyTreeNode* Root(){return root;}
	void addnode(HierarchyTreeNode &newNode);
	HierarchyTreeNode* search(string data);
};

void HierarchyTree::addnode(HierarchyTreeNode &newNode)
{
	HierarchyTreeNode* temp;
	if(root==NULL)
	{
		*root = newNode;
		return;
	}
}

HierarchyTreeNode createnode(string data)
{
	HierarchyTreeNode node(data);
	return node;
}

int _tmain(int argc, _TCHAR* argv[])
{
	int N;
	std::cin>>N;

	multimap<string, Employee> hierarchy;
	vector<Employee> emp;

	while(N!=0)
	{
		string name;
		int id;
		int salary;
		int rating;
		string boss;
		int d;
		std::cout<<"enter the name,id,salary,rating, boss name and designation"<<endl;
		std::cin>>name>>id>>salary>>rating>>boss>>d;

	
		Employee e(name,id,salary,rating,boss,d);
		emp.push_back(e);
		if(d==3)
		{
			hierarchy.insert(std::make_pair("CEO",e));
		}
		else
		{
			hierarchy.insert(std::make_pair(boss,e));
		}
		N--;
	}
	Employee ceo = hierarchy.find("CEO")->second;
	HierarchyTreeNode root = createnode(ceo.getname());
	HierarchyTree tree = HierarchyTree();
	std::cout<<emp.size();
	for(int i = 0; i < emp.size(); i++)
	{
		if(emp.at(i).getboss()==ceo.getname())
		{
			HierarchyTreeNode temp = createnode(emp.at(i).getname());
			root.addchild(temp);
		}
	}
	/*if(root.search("sumit"))
		std::cout<<"heehaa";
	else std::cout<<"hoohaa";*/
	//tree.addnode(root);

	getch();
	return 0;
}

