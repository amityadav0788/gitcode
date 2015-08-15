// AddNumberLinkedList.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

//commented lines are using struct, second part is using classes

//struct node
//{
//	int data;
//	struct node *next;
//};
//
//struct node *createnode(int val)
//{
//	struct node *temp;
//	temp = (struct node*)malloc(sizeof(struct node));
//	temp->next = NULL;
//	temp->data = val;
//	return temp;
//}
//
//void push(struct node **head,int val)
//{
//	struct node *temp;
//		temp = createnode(val);
//		temp->next = *head;
//		*head = temp;
//}
//
//void print(struct node *head)
//{
//	if(head!=NULL)
//	{
//		std::cout<<head->data<<" ";
//		print(head->next);
//	}
//}
//
//void addlist(struct node *first,struct node *second)
//{
//	int carry=0,sum;
//	struct node *output = NULL;
//	struct node *temp = NULL;
//	struct node *prev = NULL;
//	while(first!=NULL || second!=NULL)
//	{
//		sum = carry+(first?first->data:0)+(second?second->data:0);
//		carry = (sum>=10)?1:0;
//		std::cout<<"carry is "<<carry<<endl;
//		std::cout<<"prev sum is "<<sum<<endl;
//		sum = sum%10;
//		std::cout<<"sum is "<<sum<<endl;
//		
//		temp = createnode(sum);
//		if(output==NULL)
//			output=temp;
//		else prev->next = temp;
//		 prev = temp;
//		 if(first) first = first->next;
//		 if(second) second = second->next;
//	}
//	if (carry > 0)
//      temp->next = createnode(carry);
//	print(output);
//}
//
//int _tmain(int argc, _TCHAR* argv[])
//{
//	struct node *first,*second;
//
//	first = NULL;
//	//first->next = NULL;
//
//	second = NULL;
//	//second->next = NULL;
//	//std::cout<<"Enter the first list";
//	push(&first,6);
//	push(&first,7);
//	push(&second,5);
//	push(&second,7);
//	//print(first);
//	addlist(first,second);
//	int i;
//	std::cin>>i;
//	return 0;
//}

class LinkedListNode
{
	friend class LinkedList;
public:
	LinkedListNode *next;
	int data;
public:
	LinkedListNode()
	{
		next = NULL;
	}
	LinkedListNode(int d)
	{
		data = d;
		next = NULL;
	}

	int getdata()
	{
		return data;
	}

	LinkedListNode* getNext()
	{
		return next;
	}
};

class LinkedList
{
public:
	LinkedListNode *head;
public:
	LinkedList()
	{
		head=NULL;
	}

	void addnode(int d)
	{
		if(head==NULL)
			head = new LinkedListNode(d);
		else 
		{
			while(head->next!=NULL)
			{
				head = head->next;
			}
			LinkedListNode *temp = new LinkedListNode(d);
			head->next = temp;
		}
	}

	void printlist()
	{
		while(head != NULL)
		{
			std::cout<<head->data<<endl;
			head= head->next;
		}
	}
};

void addnodes(LinkedList *first,LinkedList *second)
{
	LinkedListNode temp;
	LinkedList output;
	int carry =0,sum;
	while(first->head!= NULL || second->head != NULL)
	{
		sum = carry+(first?first->head->data:0)+(second?first->head->data:0);
		carry = (sum>=10)?1:0;
		std::cout<<"carry is "<<carry<<endl;
		std::cout<<"prev sum is "<<sum<<endl;
		sum = sum%10;
		std::cout<<"sum is "<<sum<<endl;
		
		//temp = LinkedListNode(sum);
		output.addnode(sum);
//		else prev->next = temp;
//		 prev = temp;
		 if(first->head) first->head = first->head->next;
		 if(second->head) second->head = second->head->next;
//	}
	}
	if (carry > 0)
		output.addnode(carry);
	output.printlist();
}

int _tmain(int argc, _TCHAR* argv[])
{
	LinkedList first;
	LinkedList second;
	first.addnode(6);
	first.addnode(5);
	second.addnode(7);
	second.addnode(8);
	//first.printlist();
	addnodes(&first,&second);
	int i;
	std::cin>>i;
}