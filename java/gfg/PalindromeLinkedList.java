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

class PalindromeLinkedList {

    public static void main(String args[] ) throws Exception {
        
         LinkedList<Integer> ll = new LinkedList<Integer>();
		 ll.add(2);
		 ll.add(3);
		 ll.add(2);
		 if(checkPalindrome(ll))
			System.out.println("yes");
		else System.out.println("no");
		 System.out.println(ll);
	}
	
	bool checkPalindrome(LinkedList ll)
	{
		LinkedList<integer> iteratorfast = ll.listIterator();
		LinkedList<integer> iteratorslow = ll.listIterator();
		
	}
}
