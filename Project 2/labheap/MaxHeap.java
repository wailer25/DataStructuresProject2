package labheap;

import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeap<T> extends Heap<T>{
	
	
	private Comparator<T> com;
	private ArrayList<T> maxHeap;
	
	public MaxHeap() {
		
		maxHeap = new ArrayList<>();
		com = null;
	}
	
	public MaxHeap(Comparator<T> com) {
		
		maxHeap = new ArrayList<>();
		this.com = com;
		
	}
	
	/**
	 * adds an element to the heap then balances it to be a max heap by following the algorithm
	 * explained in the book on page 300
	 * @param data
	 */
	public void insert(T data) {

		maxHeap.add(data);

		int child = maxHeap.size() - 1;
		int parent = (child - 1)/2;
		while(parent >= 0 && compare(maxHeap.get(parent), maxHeap.get(child)) < 0)
		{
			T tmp = maxHeap.get(parent);
			maxHeap.set(parent, maxHeap.get(child));
			maxHeap.set(child, tmp);
			
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
		System.out.println("Max Heap: ");
		while (i < maxHeap.size() - 1)
		{
			while (j < Math.pow(2, level) && i < maxHeap.size())
			{
				System.out.print(maxHeap.get(i) + " ");
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
