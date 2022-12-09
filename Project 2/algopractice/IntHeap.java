package algopractice;

import java.util.ArrayList;
public class IntHeap {
	
	ArrayList<Integer> heap;
	
	public IntHeap() {
		heap = new ArrayList<>();
	}
	
	public void insert(int data) {

		heap.add(data);

		//set the child to the position of the node just added
		int child = heap.size() - 1;
		int parent = (child - 1)/2;
		//loop while the parent node is within the bounds of the heap and the child node is less than the parent
		while(parent >= 0 && heap.get(child) < heap.get(parent))
		{
			//swap the positions of the child and parent
			int tmp = heap.get(parent);
			heap.set(parent, heap.get(child));
			heap.set(child, tmp);
			
			//the child is now in the position of the old parent and the parent is updated to reflect the childs
			//new position
			child = parent;
			parent = (child - 1)/2;
		}
	}
	
	public int remove() {
		int top = heap.get(0);
		
		if (heap.size() == 1)
			return heap.remove(0);
		
		//put the removed element at the top of the heap
		heap.set(0, heap.remove(heap.size() - 1));
		int parent = 0;
		/*left child of parent node is in its position*2 + 1 and right in position*2 + 2
		  ie if the current parent is the leftmost child on level 2(index 3) its left child will be
		  in position 7 as there will be 3 nodes on the same level to the right meaning the right node
		  of that same parent will be in position 8 */
		int left = (parent * 2) + 1;
		int right = (parent * 2) + 2;
		boolean isHeap = false;
		
		//loop while the heap is not balanced and the children are within the bounds of the heap
		while(!isHeap && left < heap.size() && right < heap.size())
		{
			//set min to left child by default
			int min = left;
			//if the right child is less than the left it becomes the new min
			if (heap.get(right) < heap.get(left))
				min = right;
			// if the child is less than the parent it becoms the new parent and the index of the current children
			// are now set to the index of its children
			if (heap.get(min) < heap.get(parent))
			{
				int tmp = heap.get(parent);
				heap.set(parent, heap.get(min));
				heap.set(min, tmp);
				
				parent = min;
				left = (parent * 2) + 1;
				right = (parent * 2) + 2;
			}
			else
				isHeap = true;
		}
		return top;
	}
	
	public void toHeap(ArrayList<Integer> swap)
	{
		for (int i: swap)
			insert(i);
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
}
