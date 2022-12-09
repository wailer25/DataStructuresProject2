package booktree;

import java.util.Random;
public class TreeTest {

	public static void main(String[] args) {
		TreeTest tst = new TreeTest();
		tst.runTest();
		
	}
	
	public void runTest() {
		BinarySearchTree<Integer> bs1 = new BinarySearchTree<>();
		BinarySearchTree<Integer> bs2 = new BinarySearchTree<>();
		BinarySearchTree<Integer> bs3 = new BinarySearchTree<>();
		BinarySearchTree<Integer> bs4 = new BinarySearchTree<>();
		Random rng = new Random();
		
		for (int i = 0; i < 7; i++)
		{
			bs1.add(rng.nextInt(101));
			bs2.add(rng.nextInt(75, 176));
			bs3.add(rng.nextInt(121, 290));
			bs4.add(rng.nextInt(23, 98));
		}

		bs1.bft();
		System.out.println();
		bs1.dft();
		System.out.println();
		bs2.bft();
		System.out.println();
		bs2.dft();
		System.out.println();
		bs3.bft();
		System.out.println();
		bs3.dft();
		System.out.println();
		bs4.bft();
		System.out.println();
		bs4.dft();
	}
}
