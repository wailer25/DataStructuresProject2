package queuelabmarket;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Checkout {

	private Counter[] registers;
	private double arrivalRate;
	private int numSuper;
	private int numExp;
	private int maxItems;
	private int maxSimTime;
	
	/**
	 * Constructor for the checkout creates an array of counters consisting of 1 super, 2 express, and user determined
	 * standard lines
	 * @param numStandLines
	 */
	public Checkout(int numStandLines) {
		registers = new Counter[3 + numStandLines];
		
		for (int i = 0; i < registers.length; i++)
		{
			if (i == 0)
				registers[i] = new Counter(true, false);
			else if (i > 0 && i < 3)
				registers[i] = new Counter(false, true);
			else
				registers[i] = new Counter(false, false);
		}
	}
	
	/**
	 * prompts user to enter data for arrivalRate, max number of items per super, express, and standard lines
	 * as well as how long the simulation runs for
	 */
	public void enterData() {
		JFrame jf = new JFrame();
		JOptionPane jop = new JOptionPane();
		
		String ar = jop.showInputDialog(jf, "Enter customer arrival rate per hour");
		double ard = Double.parseDouble(ar) / 3600;
		arrivalRate = ard;
		
		String ns = jop.showInputDialog(jf, "Enter max number of items per Super Express line");
		numSuper = Integer.parseInt(ns);
		
		String ne = jop.showInputDialog(jf, "Enter max number of items per Express line");
		numExp = Integer.parseInt(ne);
		
		String mi = jop.showInputDialog(jf, "Enter max number of items per customer");
		maxItems = Integer.parseInt(mi);
		
		String mst = jop.showInputDialog(jf, "Enter max simulation time in seconds");
		maxSimTime = Integer.parseInt(mst);
		
		
		
		
	}
	
	/**
	 * if Math.random() returns an integer that is less than arrival rate add a customer to the line
	 * with the least amount of people in it
	 */
	public void checkArrival() {
		if (Math.random() < arrivalRate)
		{
			Random rng = new Random();
			int x = 0;
			int items = rng.nextInt(1, maxItems + 1);
			int min;
			
			//temporarily set min index to the first occurance of a standard line
			do
			{
				min = x;
				x++;
			} while(registers[x].isSuperExpress() || registers[x].isExpress());
			
			
			for (int i = 0; i < registers.length; i++)
			{
				/*if line at index i is super express line check to see if customer is eligible to enter
				 *if customer is eligible for super express line check to see if it is the line with the least people
				 */
				if (registers[i].isSuperExpress() && items < numSuper)
				{
					if (registers[i].getSize() < registers[min].getSize())
						min = i;
				}
				
				/*if line at index i is an express line check to see if customer is eligible to enter
				 *if customer is eligible for express line check to see if it is the line with the least people
				 */
				if (registers[i].isExpress() && items < numExp)
				{
					if (registers[i].getSize() < registers[min].getSize())
						min = i;
				}
				
				//check to see if line at index i is the line with the least people
				if (registers[i].getSize() < registers[min].getSize())
					min = i;
				
			}
			
			//customer joins smallest line they are eligible to enter
			registers[min].enterQueue(new Customer(items));
		}
	}
	/**
	 * uses a for loop to run a market checkout sim where int time simulates seconds
	 */
	public void runSim() {
		
		for (int time = 0; time <= maxSimTime; time++)
		{
			//check checkout for arrivals every second
			checkArrival();
			
			for (int i = 0; i < registers.length; i++)
			{
				//for every counter if 5 seconds have passed and a customer is currently being served scan an item
				if (time % 5 == 0)
				{
					if (!registers[i].isEmpty())
					{
						registers[i].scanItem();
						
						//if customer being served has no items left to scan leave the line
						if(registers[i].getCustomer().getItemCount() == 0)
							registers[i].leaveQueue();
					}
				}
				
				//if counter line is empty increment free time otherwise increment increment customer wait time
				if (registers[i].isEmpty())
					registers[i].incrementFreeTime();
				else
					registers[i].incrementWaitTime();
			}
		}
	}
	
	public void displayStats() {
		
		//initialize statistics for total wait time, free time, customers served, total customers, and items
		int totalWait = 0;
		int totalFree = 0;
		int totalServed = 0;
		int totalCustomers = 0;
		int totalItems = 0;
		// int seconds per hours used for later calculations
		int sPH = maxSimTime / 3600;
		
		/* iterate through array of counters displaying number of items scanned, average free time
		 * average wait time, max amount of customers in line at any point in time, and number of customers served
		 */
		for (int i = 0; i < registers.length; i++)
		{
			if(registers[i].isSuperExpress())
			{
				System.out.println("Average wait time for Line " 
						+ i + ", Super Express: " + registers[i].avgWaitTime());
				System.out.println("Maximum number of customers waiting in Line " 
						+ i + ", Super Express: " + registers[i].getLineMax());
				System.out.println("Number of customers served per hour in Line "
						+ i + ", Super Express: " + registers[i].getServed() / sPH);
				System.out.println("Items processed per hour in Line "
						+ i + ", Super Express: " + registers[i].getItemsProcessed() / sPH);
				System.out.println("Average free time for Line "
						+ i + ", Super Express: " + registers[i].avgFreeTime());
				
			}
			else if(registers[i].isExpress())
			{
				System.out.println("Average wait time for Line " 
						+ i + ", Express: " + registers[i].avgWaitTime());
				System.out.println("Maximum number of customers waiting in Line " 
						+ i + ", Express: " + registers[i].getLineMax());
				System.out.println("Number of customers served per hour in Line "
						+ i + ", Express: " + registers[i].getServed() / sPH);
				System.out.println("Items processed per hour in Line "
						+ i + ", Express: " + registers[i].getItemsProcessed() / sPH);
				System.out.println("Average free time for Line "
						+ i + ", Express: " + registers[i].avgFreeTime());
			}
			else
			{
				System.out.println("Average wait time for Line " 
						+ i + ", Standard: " + registers[i].avgWaitTime());
				System.out.println("Maximum number of customers waiting in Line " 
						+ i + ", Standard: " + registers[i].getLineMax());
				System.out.println("Number of customers served per hour in Line "
						+ i + ", Standard: " + registers[i].getServed() / sPH);
				System.out.println("Items processed per hour in Line "
						+ i + ", Standard: " + registers[i].getItemsProcessed() / sPH);
				System.out.println("Average free time for Line "
						+ i + ", Standard: " + registers[i].avgFreeTime());
			}
			//line skip for readability
			System.out.println();
			
			//calculates market totals for later display
			totalWait += registers[i].getWaitTime();
			totalFree += registers[i].getFreeTime();
			totalServed += registers[i].getServed();
			totalCustomers += (registers[i].getServed() + registers[i].getSize());
			totalItems += registers[i].getItemsProcessed();
			
		}
		//displays market totals as well as over free and wait times
		System.out.println("Overall average wait time: " + (double)totalWait / registers.length);
		System.out.println("Overall average free time: " + (double)totalFree / registers.length);
		System.out.println("Total customers served: " + totalServed);
		System.out.println("Total customers at store: " + totalCustomers);
		System.out.println("Total items processed: " + totalItems);
	}
	
}
