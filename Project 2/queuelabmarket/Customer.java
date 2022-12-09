package queuelabmarket;

public class Customer {

	private int items;
	
	/**
	 * Constructor for customer initialized with user determined number of items
	 * @param items
	 */
	public Customer(int items) {
		this.items = items;
	}
	
	//getter for number of items customer has
	public int getItemCount() {
		return items;
	}
	
	//setter fir the number of items customer has
	public void setItemCount(int items)
	{
		this.items = items;
	}
}
