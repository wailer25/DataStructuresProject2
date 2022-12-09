package queuelabmarket;

import java.util.LinkedList;
import java.util.Queue;

public class Counter {
	
	private boolean superExpress;
	private boolean express;
	private int served;
	private int lineMax;
	private int freeTime;
	private int waitTime;
	private int itemsProcessed;
	private Queue<Customer> line;
	

	/**
	 * Constructer initializes variables and sets whether counter will be express or super express
	 * @param superExpress
	 * @param express
	 */
	public Counter(boolean superExpress, boolean express) {
		this.superExpress = superExpress;
		this.express = express;
		line = new LinkedList<>();
		served = 0;
		lineMax = 0;
		freeTime = 0;
		waitTime = 0;
		itemsProcessed = 0;
	}
	
	public void setSExpress(boolean value)
	{
		superExpress = value;
	}
	
	public void setExpress(boolean value)
	{
		express = value;
	}
	
	//checks whether counter is super express counter
	public boolean isSuperExpress() {
		return superExpress;
	}

	//checks whether counter is express counter
	public boolean isExpress() {
		return express;
	}
	
	//checks whether line is empty
	public boolean isEmpty() {
		return line.isEmpty();
	}
	
	//return size of the line
	public int getSize() {
		return line.size();
	}
	
	//return the maximum amount of waiting customers that the line hit
	public int getLineMax() {
		return lineMax;
	}
	
	//return customer at front of line
	public Customer getCustomer() {
		return line.peek();
	}
	
	//return total amount of items counter has processed
	public int getItemsProcessed() {
		return itemsProcessed;
	}
	
	//return total amount of customers counter has served
	public int getServed() {
		return served;
	}
	
	//return total amount of counter wait time
	public int getWaitTime() {
		return waitTime;
	}
	
	//return total amount of counter free time
	public int getFreeTime() {
		return freeTime;
	}
	
	//adds a customer to the line and increases the lineMax if its the longest line has ever been
	public void enterQueue(Customer c) {
		 line.add(c);
		 if (line.size() > lineMax)
			 lineMax = line.size();
	}
	
	//removes customer from the line and increments the amount of customers served by one
	public void leaveQueue() {
		line.remove();
		served++;
	}
	
	//if there is a customer in line scan one of their items and increment the number of items processed by one
	public void scanItem() {
		if (!line.isEmpty())
		{
			int items = line.peek().getItemCount();
			line.peek().setItemCount(items - 1);
			itemsProcessed++;
		}
	}
	
	//increments freeTime by one
	public void incrementFreeTime() {
		freeTime++;
	}
	
	//increments waitTime by one
	public void incrementWaitTime() {
		waitTime++;
	}
	
	//returns the average wait time per customer if a customer is served else return 0
	public double avgWaitTime() {
		if (served > 0)
			return waitTime / served;
		return 0;
	}
	
	//returns the amount of free time of counter between customers
	public double avgFreeTime() {
		if (served > 0)
			return freeTime / served;
		return freeTime;
	}

}
