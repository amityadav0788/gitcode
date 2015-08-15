// Trie.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <string.h>

#define ALPHABET_SIZE (26)
#define RELATIVE_POS(c) ((int)c-(int)'a')
#define ARRAY_SIZE(a) sizeof(a)/sizeof(a[0]

using namespace std;

struct trie_node
{
	int value;
	trie_node *children[ALPHABET_SIZE];
};

struct trie_tree
{
	trie_node *root;
	int count;
};

trie_node *getnode()
{
	trie_node *node = NULL;

	node = (trie_node*)malloc(sizeof(trie_node));

	if(node)
	{
		node->value = 0;
		for(int i=0;i<ALPHABET_SIZE;i++)
		{
			node->children[i] = NULL;
		}
	}
	return node;
}

void initialize(trie_tree *triet)
{
	triet->root = getnode();
	triet->count = 0;
}

void insert(trie_tree *tree, char key[])
{
	int length = strlen(key);
	trie_node *crawl = tree->root;
	int index;

	tree->count++;

	for(int i=0;i<length;i++)
	{
		index = RELATIVE_POS(key[i]);    //getting ith child of node
		if(!crawl->children[index])
		{
			crawl->children[index] = getnode();
		}

		crawl = crawl->children[index];
	}
	crawl->value = tree->count;
}

int search(trie_tree *tree,char key[])
{
	trie_node *crawl;
	crawl = tree->root;
	for(int i=0;i<strlen(key);i++)
	{
		int index = RELATIVE_POS(key[i]);
		if(!crawl->children[index])
			return 0;
		crawl = crawl->children[index];
	}
	return(crawl!= 0 && crawl->value );
}

int main(array<System::String ^> ^args)
{
	char keys[][8] = {"amit","ans","tony","to"};
	trie_tree tree;
	int i;

	initialize(&tree);

	for(i=0;i<4;i++)
    {
        insert(&tree,keys[i]);
    }

	if(search(&tree,"pam"))
		cout << "there";
	else cout<< "not there";

	int j;
	cin >> j;
	return 0;
}
