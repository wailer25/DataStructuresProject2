package stacklab;

import java.util.Random;
import java.util.Stack;


/**
 * Lab: Practice with Stack operations
 * 
 * See comments for assignment instructions.
 * 
 * @author Andrew Obiora
 *
 */
public class StackCopy {
	
	/**
	 * Make an identical copy of a Stack.
	 * post-condition: s is the same as it was before the method was called.
	 * @param s The Stack to copy.
	 * @return An identical copy of the Stack s.
	 */
	public static <E> Stack<E> copy(Stack<E> s) {
		
		// (1) Complete this method.  Although Java's Stack class provides methods
		//     for access by an index, you are not allowed to use those methods in
		//     this lab assignment.  You are also not allowed to use the clone method
		//     of Java's Stack class.  You are allowed to use only the following
		//     methods of Java's Stack class: empty, peek, pop, and push (and also 
		//     the constructor since you will need to construct a couple Stacks).
		
		// Stack Copy Algorithm: Assumes the Stack to copy is named s (as it is in the parameter of this method.
		//     Let copy be an initially empty Stack.
		//     Let temp be an initially empty Stack.
		//     while s is not empty
		//         Pop s and store the result in element.
		//         Push element onto temp.
		//     end while
		//     while temp is not empty
		//         Pop temp and store the result in element.
		//         Push element onto copy.
		//         Push element onto s.
		//     end while
		//     return copy
		Stack<E> copy = new Stack<>();
		Stack<E> tmp = new Stack<>();
		
		while(!s.isEmpty())
			tmp.push(s.pop());
		
		while(!tmp.isEmpty())
		{
			E element = tmp.pop();
			copy.push(element);
			s.push(element);
		}
		
		return copy;
	}
	
	/**
	 * Make a reverse copy of a Stack.
	 * post-condition: s is the same as it was before the method was called.
	 * @param s The Stack to copy.
	 * @return A reverse copy of the Stack s.
	 */
	public static <E> Stack<E> reverseCopy(Stack<E> s) {
		
		// (2) Complete this method.  Although Java's Stack class provides methods
		//     for access by an index, you are not allowed to use those methods in
		//     this lab assignment.  You are also not allowed to use the clone method
		//     of Java's Stack class.  You are allowed to use only the following
		//     methods of Java's Stack class: empty, peek, pop, and push (and also 
		//     the constructor since you will need to construct a couple Stacks).
		
		// Stack Reverse Copy Algorithm: Assumes the Stack to copy is named s (as it is in the parameter of this method.
		//     Let reverse be an initially empty Stack.
		//     Let temp be an initially empty Stack.
		//     while s is not empty
		//         Pop s and store the result in element.
		//         Push element onto temp.
		//         Push element onto reverse.
		//     end while
		//     while temp is not empty
		//         Pop temp and store the result in element.
		//         Push element onto s.
		//     end while
		//     return reverse
		
		Stack<E> reverse = new Stack<>();
		Stack<E> tmp = new Stack<>();
		
		while(!s.isEmpty())
		{
			E element = s.pop();
			tmp.push(element);
			reverse.push(element);
		}
		
		while(!tmp.isEmpty())
			s.push(tmp.pop());
		return reverse;
	}

	public static void main(String[] args) {
		
		// (3) Complete this main method to test your methods.

		// Create a Stack of whatever type of element you want, such as Strings,
		// Integers, etc, and put at least 5 different elements in it.
		Random rng = new Random();
		Stack<Integer> org = new Stack<>();
		
		for(int i = 0; i <= 5; i++)
			org.push(rng.nextInt(100));

		// Use the copy method to make a duplicate Stack
		System.out.println("Original Stack");
		
		Stack<Integer> copy = copy(org);
		// Use a for-each style loop to print the original Stack
		// This type of loop will work with Java's Stack class since it implements
		// the Iterable interface, although you don't typically iterate over a Stack
		// like this.

		for(int i: org)
			System.out.print(i + " ");
		System.out.println();
		
		System.out.println("Copy Stack");
		// Use a for-each style loop to print the copy
		
		for(int i: copy)
			System.out.print(i + " ");
		System.out.println();

		// Use the reverseCopy method to make a copy of the original Stack in reverse order

		Stack<Integer> r = reverseCopy(org);
		System.out.println("Original Stack");
		// Use a for-each style loop to print the original Stack
		
		for(int i: org)
			System.out.print(i + " ");
		System.out.println();
		
		System.out.println("Reverse copy Stack");
		// Use a for-each style loop to print the reverse copy 
		
		for(int i: r)
			System.out.print(i + " ");

	}

}
