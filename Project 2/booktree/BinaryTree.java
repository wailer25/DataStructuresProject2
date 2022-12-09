/*<listing chapter="6" section="3">*/
package booktree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.function.BiConsumer;


/**
 * Class for a binary tree that stores type E objects.
 *
 * @param <E> The element type
 * @author Koffman and Wolfgang
 *
 */
public class BinaryTree<E> implements Serializable {

    /*<listing chapter="6" number="1">*/
    /**
     * Class to encapsulate a tree node.
     *
     * @param <E> The element type
     */
    protected static class Node<E> implements Serializable {
        // Data Fields

        /**
         * The information stored in this node.
         */
        public E data;
        /**
         * Reference to the left child.
         */
        public Node<E> left;
        /**
         * Reference to the right child.
         */
        public Node<E> right;

        // Constructors
        /**
         * Construct a node with given data and no children.
         *
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Returns a string representation of the node.
         *
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }

    }
    /*</listing>*/
    // Data Field
    /**
     * The root of the binary tree
     */
    protected Node<E> root;

    /**
     * Construct an empty BinaryTree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a BinaryTree with a specified root. Should only be used by
     * subclasses.
     *
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree as its left
     * subtree and rightTree as its right subtree.
     *
     * @param data The data item to store in the root
     * @param leftTree the left child
     * @param rightTree the right child
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
            BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     *
     * @return The left subtree or null if either the root or the left subtree
     * is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     *
     * @return the right sub-tree or null if either the root or the right
     * subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }
    
    /**
     * prints out the tree using depth first traversal
     * 
     */
    public void dft() {
    	Stack<Node<E>> dft = new Stack<>();
    	
    	//push root on top of the stack
    	dft.push(root);
    	
    	while (!dft.isEmpty())
    	{
    		/* navigates to left most bottom node print nodes along its path
    	     * then pops and prints the right nodes from the bottom up on the left side of the tree
    	     * then repeats for right subtree */
    		//pops off the top of the stack and sets tmp node to it
    		Node<E> tmp = dft.pop();
    		//prints out the value of tmp node
    		System.out.print(tmp.data + " ");
    		//push tmp right child to top of the stack if it exists
    		if (tmp.right != null)
    			dft.push(tmp.right);
    		//push tmp left child to the top of the stack if it exists
    		if (tmp.left != null)
    			dft.push(tmp.left);
    		
    		
    		
    	}
    }
    
    /**
     * prints out the tree using breadth first traversal
     * by following the algorithm given by the assignment/book
     */
    public void bft() {
    	Queue<Node<E>> bft = new LinkedList<>();
    	/*
    	 * navigates to left and right node of tmp and prints the values
    	 * then navigates to the far left node of the next level, prints it,
    	 * then prints the nodes to the right of it in order
    	 */
    	//add root of the tree to the front of the queue
    	bft.add(root);
    	
    	while (!bft.isEmpty())
    	{
    		//removes the head of the queue and sets tmp to it
    		Node<E> tmp = bft.poll();
    		//prints value of current tmp node
    		System.out.print(tmp.data + " ");
    		//if left child of tmp is not null add it to back of the queue
    		if (tmp.left != null)
    			bft.add(tmp.left);
    		//if right child of tmp is not null add it to back of the queue
    		if (tmp.right != null)
    			bft.add(tmp.right);
    		
    		
    	}
    }
    
    /**
     * uses breadth first traversal to search for a user given data value
     * @param data
     * @return
     */
    public boolean bfs(E data) {
    	Queue<Node<E>> bfs = new LinkedList<>();
    	
    	bfs.add(root);
    	
    	while (!bfs.isEmpty())
    	{
    		Node<E> tmp = bfs.poll();
    		
    		if (tmp.data == data)
    			return true;
    		if (tmp.left != null)
    			bfs.add(tmp.left);
    			
    		if (tmp.right != null)
    			bfs.add(tmp.right);

    	}
    	return false;
    }
    
    /**
     * uses breadth first traversal to search for a user given data value
     * @param data
     * @return
     */
    public boolean dfs(E data) {
    	Stack<Node<E>> dfs = new Stack<>();
    	
    	dfs.add(root);
    	
    	while(!dfs.isEmpty())
    	{
    		Node<E> tmp = dfs.pop();
    		
    		if (tmp.right != null)
    			dfs.add(tmp.right);
    		if (tmp.left != null)
    			dfs.add(tmp.left);
    		
    		if (tmp.data == data)
    			return true;
    	}
    	
    	return false;
    }

    /**
     * Return the data field of the root
     *
     * @return the data field of the root or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        preOrderTraverse((e, d) -> {
            for (int i = 1; i < d; i++) {
                sb.append("  ");
            }
            sb.append(e);
            sb.append("\n");
        });
        return sb.toString();
    }

    /*<listing chapter="6" section="4">
    /**
     * Starter method for preorder traversal
     * @param consumer an object that instantiates the BiConsumer interface.
     *        Its method implements the abstract method apply.
     */
    public void preOrderTraverse(BiConsumer<E, Integer> consumer) {
        preOrderTraverse(root, 1, consumer);
    }

    /**
     * Performs a recursive pre-order traversal of the tree,
     * applying the action specified in the consumer object.
     * @param node The local root
     * @param depth The depth
     * @param consumer object whose accept method specifies the action 
     * to be performed on each node.
     */
    private void preOrderTraverse(Node<E> node, int depth, 
            BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth + 1, consumer);
            preOrderTraverse(node.right, depth + 1, consumer);
        }
    }
    /*</listing>*/

    /*<exercise chapter="6" section="4" type="programming" number="4">*/
    /**
     * Perform a post-order traversal of the tree.
     * Performs a post-order traversal of the tree passing each node and 
     * the node's depth to the consumer function.
     * @param consumer The consumer of each node
     */
    public void postOrderTraverse(BiConsumer<E, Integer> consumer) {
        postOrderTraverse(root, 1, consumer);
    }

    /**
     * Perform a pre-order traversal.
     * Performs a pre-order traversal of the subtree whose root is at node
     * passing the node and the depth to the consumer function.
     * @param node The local root
     * @param depth The depth
     * @param consumer The consumer of each node
     */
    private void postOrderTraverse(Node<E> node, int depth, 
            BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            postOrderTraverse(node.left, depth + 1, consumer);
            postOrderTraverse(node.right, depth + 1, consumer);
            consumer.accept(node.data, depth);
        }
    }
    /*</exercise>*/

    /*<exercise chapter="6" section="4" type="programming" number="5">*/
    /**
     * Perform a in-order traversal of the tree.
     * Performs a in-order traversal of the tree passing each node and 
     * the node's depth to the consumer function.
     * @param consumer The consumer of each node
     */
    public void inOrderTraverse(BiConsumer<E, Integer> consumer) {
        inOrderTraverse(root, 1, consumer);
    }

    /**
     * Perform a in-order traversal.
     * Performs a in-order traversal of the subtree whose root is at node
     * passing the node and the depth to the consumer function.
     * @param node The local root
     * @param depth The depth
     * @param consumer The consumer of each node
     */
    private void inOrderTraverse(Node<E> node, int depth, 
            BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            inOrderTraverse(node.left, depth + 1, consumer);
            consumer.accept(node.data, depth);
            inOrderTraverse(node.right, depth + 1, consumer);
        }
    }
    /*</exercise>*/

    /*<listing chapter="6" number="2">*/
    /**
     * Method to read a binary tree.
     *
     * @pre The input consists of a pre-order traversal of the binary tree. The
     * line "null" indicates a null tree.
     * @param scan the Scanner attached to the input file
     * @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }
    /*</listing>*/

    /*<exercise chapter="6" section="3" type="programming" number="1">*/
    /**
     * Method to return the pre-order traversal of the binary tree as a sequence
     * of strings each separated by a space.
     *
     * @return A pre-order traversal as a string
     */
    public String preorderToString() {
        return preorderToString(root);
    }

    private String preorderToString(Node<E> node) {
        StringJoiner sj = new StringJoiner(" ");
        sj.add(node.toString());
        if (node.left != null) {
            sj.add(preorderToString(node.left));
        }
        if (node.right != null) {
            sj.add(preorderToString(node.right));
        }
        return sj.toString();
    }
    /*</exercise>*/

    /*<exercise chapter="6" section="3" type="programming" number="2">*/
    /**
     * Method to return the post-order traversal of the binary tree as a
     * sequence of strings each separated by a space.
     *
     * @return A post-order traversal as a string
     */
    public String postorderToString() {
        return postorderToString(root);
    }

    private String postorderToString(Node<E> node) {
        StringJoiner sj = new StringJoiner(" ");
        if (node.left != null) {
            sj.add(postorderToString(node.left));
        }
        if (node.right != null) {
            sj.add(postorderToString(node.right));
        }
        sj.add(node.toString());
        return sj.toString();
    }
    /*</exercise>*/

    /*<exercise chapter="6" section="3" type="programming" number="3">*/
    /**
     * A method to display the in-order traversal of a binary tree placing a
     * left parenthesis before each subtree and a right parenthesis after each
     * subtree. For example the expression tree shown in Figure 6.12 would be
     * represented as (((x) + (y)) * ((a) / (b))).
     *
     * @return An in-order string representation of the tree
     */
    public String inorderToString() {
        return inorderToString(root);
    }

    private String inorderToString(Node<E> node) {
        StringJoiner sj = new StringJoiner(" ", "(", ")");
        if (node.left != null) {
            sj.add(inorderToString(node.left));
        }
        sj.add(node.toString());
        if (node.right != null) {
            sj.add(inorderToString(node.right));
        }
        return sj.toString();
    }
    /*</exercise>*/

}
/*</listing>*/
