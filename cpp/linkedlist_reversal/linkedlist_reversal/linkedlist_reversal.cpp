// linkedlist_reversal.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <cstdio>

using namespace std;

struct LinkedListNode
{
	struct LinkedListNode *next;
	int data;
}head;

class LinkedList
{
public:
	struct LinkedListNode *head;
	void add(int i);
	void traverse();
	void reverse();
	//void traverse();

	LinkedList()
	{
		head = NULL;
	}
};

void LinkedList::reverse()
{
	if(head != NULL)
	{
		struct LinkedListNode *prev = new LinkedListNode();
		struct LinkedListNode *current = new LinkedListNode();
		struct LinkedListNode *next = new LinkedListNode();
		prev = NULL;
		current = head;
		 while (current != NULL)
		{
			next  = current->next;  
			current->next = prev;   
			prev = current;
		    current = next;
		}
		 head = prev;
	}
}

void LinkedList::traverse()
{
	if(head!= NULL)
	{
		struct LinkedListNode *temp = new LinkedListNode();
		temp = head;
		while(temp != NULL)
		{
			cout << temp->data << " -> ";
			temp = temp->next;
		}
	}
}

void LinkedList::add(int i)
{
	struct LinkedListNode *temp = new LinkedListNode();
	temp->data = i;
	temp->next = NULL;

	if (head == NULL)
	{
		head = temp;
		return;
	}
	struct LinkedListNode *temp1 = head;
	while(temp1->next != NULL)
	{
		temp1 = temp1->next;
	}

	temp1->next = temp;
}

int main(array<System::String ^> ^args)
{
    LinkedList ll;
	ll.add(3);
	ll.add(2);
	ll.traverse();
	ll.reverse();
	cout << "\n";
	ll.traverse();
	int i;
	cin >> i;
    return 0;
}
