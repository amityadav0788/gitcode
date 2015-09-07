// reverse_number.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <hash_map>
#include <string>
#include <sstream>

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	
	int N;
	cin >> N;
	std::hash_map<string,bool> result;
	int no_players = pow(2,N)-1;
	char player1[10],player2[10];
	for(int i=0;i<no_players;i++)
	{
		std::cin>>player1;
		std::cin>>player2;
		result[player2] = false;
		hash_map<string, bool>::iterator it = result.find(player1);
		
		if (it == result.end())
		{
			result[player1] = true;
		}
		else
			{
				std::cout<<it->first << " lal "<<it->second<<endl;
				if(it->second)
					result[player1] = true;
		}
	}
    for(hash_map<string,bool>::iterator i = result.begin(); i != result.end(); i++)
{
	std::cout<<i->first << " lol "<<i->second;
	if(i->second == true)
		
		{
			std::cout << i->first;
			break;
	}
}
	int hold;
	cin >> hold;
	return 0;
}

