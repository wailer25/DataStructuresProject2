package algopractice;

import java.util.ArrayList;
import java.util.Random;

public class SwapAlgo {

	private double start;
	private boolean running;
	
	/**
	 * Starts the profiler timer
	 */
	public void pStart() {
		running = true;
		start = System.currentTimeMillis();
	}
	
	/**
	 * Stops the timer and returns the time taken in milliseconds
	 * returns 0 if timer was never started
	 * @return
	 */
	public double pStop() {
		if (running)
		{
			running = false;
			double stop = System.currentTimeMillis();
			return stop - start;
		}
		
		return 0;
	}
	
	
	/**
	 * nested loop that iterates through the list checking each element with the one in front of it
	 * swapping them if said element is smaller. if the whole list is iterated through without making a swap
	 * then it is sorted and the list breaks
	 * @param swap
	 * @return
	 */
	public void bubble(ArrayList<Integer> swap) {
		int hold;
		boolean sorted;

		for (int i = 0; i < swap.size(); i++)
		{
			sorted = true;
			for (int j = 0; j < swap.size() - i - 1; j++)
			{
				if (swap.get(j + 1) < swap.get(j))
				{
					hold = swap.get(j);
					swap.set(j, swap.get(j + 1));
					swap.set(j + 1, hold);
					sorted = false;
				
				}
			}
			
			if(sorted)
				break;
		}
		
	}
	
	/**
	 * nested loop that checks each element against the list itself and swaps two elements
	 * if the elements that appears sooner in the list is less than an element that appears later
	 * @param swap
	 * @return
	 */
	public void selection(ArrayList<Integer> swap) {
		int min = 0;
		
		for(int i = 0; i < swap.size(); i++)
		{
			min = i;
			for (int j = i; j < swap.size(); j++)
			{
				if (swap.get(j) < swap.get(min))
					min = j;
			}
			
			if (min != i)
			{
			int hold = swap.get(i);
			swap.set(i, swap.get(min));
			swap.set(min, hold);
			}

		}

	}
	
	/**
	 * uses a nested loop check each element and insert it before any larger element that precedes it
	 * @param swap
	 * @return
	 */
	public void insertion(ArrayList<Integer> swap) {

		for (int i = 1; i < swap.size(); i++)
		{
			int pos = i;
			//iterate through the list in reverse starting one position behind i
			for (int j = i - 1; j >= 0; j--)
			{
				//if a number larger than i is found behind it make note of the index
				if(swap.get(j) > swap.get(i))
				{
					pos = j;
				}
				
			}
			//if a number was found that is larger than the value at i insert the value before the position of that number
			if(pos != i)
				swap.add(pos, swap.remove(i));
		}
		
	}
	
	/**
	 * sorts by effectively breaking arraylist into sub arraylist and sorting them using insertion sort
	 * @param swap
	 */
	public void shell(ArrayList<Integer> swap) {
		// sets gap to be half the size of list swap
		int gap = swap.size() / 2;
		
		while(gap > 0)
		{
			// iterate through list from start of the gap
			for (int i = gap; i < swap.size(); i++)
			{

				int j = i;
				//loop while j is greater or equal to the size of the gap and 
				//the value at the position before the gap is greater than the value at the position after the gap
				while(j >= gap && swap.get(j - gap) > swap.get(i))
					j -= gap;
				//once the value at i is less than j or j is smaller than the gap insert i at j	
				swap.add(j, swap.remove(i));

			}
			
			gap /= 2;
		}

	}
	
	
	/**
	 * used as a helper method for mergeSplit(), makes a copy of values from initial ArrayList starting at index start and copies 
	 * adds to another starting at index copyStart until index copyEnd is reached
	 * @param initial
	 * @param start
	 * @param copy
	 * @param copyStart
	 * @param copyEnd
	 */
	private void copy(ArrayList<Integer> initial, int start, ArrayList<Integer> copy, int copyStart, int copyEnd) {

		int j = 0;
		
		while (start < initial.size() && j < copyEnd - copyStart)
		{
			copy.add(initial.get(start++));
			j++;
		}
	}
	
	//I leaned heavily on the book from pg 392-396 for merge sort algorithms but tried to be more generous with
	//comments in these sections to show that I understand it
	/**
	 * recursively splits an ArrayList until each list is 1 element then uses mergeCombine to recombine them in order
	 * @param swap
	 */
	public void mergeSort(ArrayList<Integer> swap) {
		
		//checks to see if list has more than one element
		if (swap.size() > 1)
		{
			//splits list in half to denote mid point
			int mid = swap.size() / 2;
			
			ArrayList<Integer> a = new ArrayList<>();
			ArrayList<Integer> b = new ArrayList<>();
			
			//splits list in half and copies them to 2 ArrayLists
			copy(swap, 0, a, 0, mid);
			copy(swap, mid, b, 0, swap.size() - mid);
			
			//recursive call to continuously subdivide lists in two until each list has one element
			mergeSort(a);
			mergeSort(b);
			
			//recombine list in sorted order
			mergeCombine(swap, a, b);
		}
	}
	
	
	/**
	 * takes elements from ArrayLists a and b and sets the values in ascending order to ArrayList swap
	 * @param swap
	 * @param a
	 * @param b
	 */
	private void mergeCombine(ArrayList<Integer> swap, ArrayList<Integer> a, ArrayList<Integer> b) {
		//index for list a
		int i = 0;
		//index for list b
		int j = 0;
		//index for list swap
		int k = 0;
		
		/*loops until either end of list a or b is reached replacing the values in swap
		 * in ascending order
		 */
		while (i < a.size() && j < b.size()) {
			if (a.get(i) < b.get(j))
			{
				//reads in k and i as is then increments them after set/get method calls are resolved
				swap.set(k++, a.get(i++));
			}
			else
				swap.set(k++, b.get(j++));
		}
		
		//if any values are left over in list a or b replaces iterates through list and replaces values in swap
		while (i < a.size())
			swap.set(k++, a.get(i++));
		while (j < b.size())
			swap.set(k++, b.get(j++));
	}
	
	
	private int partition(ArrayList<Integer> swap, int first, int last)
	{
		int pivot = swap.get(first);
		int up = first;
		int down = last;
		
		//loop while index of up is less than down
		do
		{
			//iterate through list until value is reached that is greater than pivot or last is reached
			while(up < last && swap.get(up) <= pivot)
				up++;
			
			//iterate through list until value that is less than or equal to pivot is reach or first is reached
			while(down > first && swap.get(down) > pivot)
				down--;
			
			//if index of up is less than index of down then swap the values at those positions
			if(up < down)
			{
				int tmp = swap.get(up);
				swap.set(up, swap.get(down));
				swap.set(down, tmp);
			}
		} while(up < down);
		
		//swap value at index down with the pivot value
		swap.set(first, swap.get(down));
		swap.set(down, pivot);
		
		//returns down as that will be the new pivot index
		return down;
	}
	
	public void quick(ArrayList<Integer> swap, int first, int last)
	{

		if (first < last)
		{
			int pivot = partition(swap, first, last);
			
			quick(swap, first, pivot - 1);
			quick(swap, pivot + 1, last);
		}
		
		
	}
	
	public void quickSort(ArrayList<Integer> swap)
	{
		quick(swap, 0, swap.size() - 1);
	}
	
	
	
	/**
	 * places all elements into a minHeap then removes the first element from the heap into the list
	 * then sets the element in the list at position i to said element from the heap. rebalances the heap
	 * with each removal
	 * @param swap
	 * @return
	 */
	public void heapSort(ArrayList<Integer> swap)
	{

		IntHeap heap = new IntHeap();
		heap.toHeap(swap);
		int i = 0;
		
		while (!heap.isEmpty())
		{
			swap.set(i, heap.remove());
			i++;
		}

	}
	
	public void runAll(int size, int bounds) {
		Random rng = new Random();
		ArrayList<Integer> bub = new ArrayList<>();
		ArrayList<Integer> sel = new ArrayList<>();
		ArrayList<Integer> ins = new ArrayList<>();
		ArrayList<Integer> she = new ArrayList<>();
		ArrayList<Integer> mer = new ArrayList<>();
		ArrayList<Integer> qui = new ArrayList<>();
		ArrayList<Integer> hea = new ArrayList<>();
		
		double runtime;
		for (int i = 0; i < size; i++)
		{
			int tmp = rng.nextInt(bounds);
			bub.add(tmp);
			sel.add(tmp);
			ins.add(tmp);
			she.add(tmp);
			mer.add(tmp);
			qui.add(tmp);
			hea.add(tmp);
		}
		pStart();
		bubble(bub);
		runtime = pStop();
		System.out.println("Bubble Sort time taken: " + runtime);
		for (int i : bub)
			System.out.print(i + " ");
		System.out.println();
		
		pStart();
		selection(sel);
		runtime = pStop();
		System.out.println("Selection Sort time taken: " + runtime);
		for (int i : sel)
			System.out.print(i + " ");
		System.out.println();
		
		pStart();
		insertion(ins);
		runtime = pStop();
		System.out.println("Insertion Sort time taken: " + runtime);
		for (int i : ins)
			System.out.print(i + " ");
		System.out.println();
		
		pStart();
		shell(she);
		runtime = pStop();
		System.out.println("Shell Sort time taken: " + runtime);
		for (int i : she)
			System.out.print(i + " ");
		System.out.println();
		
		pStart();
		mergeSort(mer);
		runtime = pStop();
		System.out.println("Merge Sort time taken: " + runtime);
		for (int i : mer)
			System.out.print(i + " ");
		System.out.println();
		
		pStart();
		quickSort(qui);
		runtime = pStop();
		System.out.println("Quick Sort time taken: " + runtime);
		for (int i : qui)
			System.out.print(i + " ");
		System.out.println();
		
		pStart();
		heapSort(hea);
		runtime = pStop();
		System.out.println("Heap Sort time taken: " + runtime);
		for (int i : hea)
			System.out.print(i + " ");
		System.out.println();
	}
}
