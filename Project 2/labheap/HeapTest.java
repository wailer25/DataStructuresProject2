package labheap;

import java.util.Random;
public class HeapTest {
	public static void main(String[] args) {
		MinHeap<Integer> min = new MinHeap<>();
		MaxHeap<Integer> max = new MaxHeap<>();
		Random rng = new Random();
		for (int i = 0; i < 15; i++)
		{
			min.insert(rng.nextInt(101));
			max.insert(rng.nextInt(101));
		}
		min.print();
		max.print();
	}
}
