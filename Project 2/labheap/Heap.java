package labheap;

import java.util.Comparator;


public abstract class Heap<T> implements Comparator<T> {


	
	public abstract int compare(T obj1, T obj2);
}
