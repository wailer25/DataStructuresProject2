package labheap;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Random;


public class MinHeap<T> extends Heap<T>{
	
	
	private Comparator<T> com;
	private ArrayList<T> minHeap;
	
	public MinHeap() {
		
		minHeap = new ArrayList<>();
		com = null;
	}
	
	public MinHeap(Comparator<T> com) {
		
		minHeap = new ArrayList<>();
		this.com = com;
		
	}
	
	/**
	 * adds an element to the heap then balances it to be a min heap by following the algorithm
	 * explained in the book on page 300
	 * @param data
	 */
	public void insert(T data) {

		minHeap.add(data);

		int child = minHeap.size() - 1;
		int parent = (child - 1)/2;
		while(parent >= 0 && compare(minHeap.get(parent), minHeap.get(child)) > 0)
		{
			T tmp = minHeap.get(parent);
			minHeap.set(parent, minHeap.get(child));
			minHeap.set(child, tmp);
			
			child = parent;
			parent = (child - 1)/2;
		}
	}
	
	/**
	 * prints the heap by level to improve readability
	 */
	public void print() {
		int level = 0;
		int i = 0;
		int j = 0;
		System.out.println("Min Heap: ");
		while (i < minHeap.size() - 1)
		{
			while (j < Math.pow(2, level) && i < minHeap.size())
			{
				System.out.print(minHeap.get(i) + " ");
				i++;
				j++;
			}
			j = 0;
			level++;
			System.out.println();
		}
	}
	
	

	@Override
	public int compare(T obj1, T obj2) {
		if (com != null)
		{
			return com.compare(obj1, obj2);
		}
		else
			return ((Comparable<T>)obj1).compareTo(obj2);
	}

}
