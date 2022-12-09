package queuelabexecs;

public class Person {
	
	private int salary;
	private int seniority;
	private int id;
	
	/**
	 * Person constructor that sets seniority to 0 as default and salary to 40000 as default
	 * and reads in user input for the persons id
	 * @param id
	 */
	public Person(int id) {
		seniority = 0;
		salary = 40000;
		this.id = id;
	}
	
	/**
	 * promotes the employee increasing their seniority by 1 and salary by 5000
	 */
	public void promote() {
		seniority++;
		salary += 5000;
	}
	
	/**
	 * returns the persons salary
	 * @return
	 */
	public int getSalary() {
		return salary;
	}
	
	/**
	 * returns the persons seniority over others
	 * @return
	 */
	public int getSeniority() {
		return seniority;
	}
	
	/**
	 * returns the persons id
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}
	
	@Override
	public String toString() {
		return "Employee id: " + id + " Salary: " + salary ;
	}

}
