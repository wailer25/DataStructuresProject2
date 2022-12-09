package queuelabexecs;

import java.util.Scanner;
public class Company {

	private Department[] departments;
	private int size;
	
	public Company() {
		departments = new Department[10];
		departments[0] = new Department("Unemployment");
		size = 1;
		
	}
	
	/**
	 * adds a new department to the array of departments titled with a user given name
	 * @param name
	 */
	public void add(String name) {
		if (size == departments.length)
		{
			Department[] tmp = new Department[size * 2];
			
			for (int i = 0; i < departments.length; i++)
			{
				tmp[i] = departments[i];
				departments = tmp;
			}
			
		}
		
		departments[size] = new Department(name);
		size++;
	}
	
	/**
	 * creates a person to join a user given department if it exists
	 * @param id
	 * @param department
	 */
	public void join(int id, String department)
	{
		for (int i = 0; i < size; i++)
		{
			if (departments[i].getName().equalsIgnoreCase(department))
				departments[i].join(new Person(id));
		}
	}
	
	
	/**
	 * has a person switch to another department and resets their salary and seniority to default
	 * if the employee and department exist
	 * @param id
	 * @param department
	 */
	public void change(int id, String department)
	{
		Person tmp = null;
		for (int i = 0; i < size; i++)
		{
			if (departments[i].getEmployee(id) != null)
				tmp = departments[i].quit(id);
		}
		
		if (tmp != null)
		{
			for (int i = 0; i < size; i++)
			{
				if (departments[i].getName().equalsIgnoreCase(department))
					departments[i].join(tmp);

			}
		}
		
	}
	
	/**
	 * prints all departments and people in array of departments
	 */
	public void printCompany() {
		for (int i = 0; i < size; i++)
		{
			departments[i].printDepartment();
		}
	}
	
	/**
	 * runs the program using Scanner for user input
	 */
	public void run() {
		boolean close = false;
		
		
		String input;
		Scanner sc = new Scanner(System.in);
		while (!close)
		{
			System.out.println("Current list of operating departments");
			printCompany();
			System.out.println("Enter p to generate new hire for an existing department"
					+ ", d to generate new department");
			System.out.println("q to have an existing employee quit, t to have an employee transfer departments"
					+ " or any other key to close the program");
			input = sc.nextLine();
			
			if (input.equalsIgnoreCase("p"))
			{
				System.out.println("Enter number id for new employee");
				input = sc.nextLine();
				int tmp = Integer.parseInt(input);
				System.out.println("Enter name of the department you wish for the employee to join");
				input = sc.nextLine();
				join(tmp, input);
				System.out.println("Employee " + tmp + " has joined department " + input);
			}
			else if (input.equalsIgnoreCase("d"))
			{
				System.out.println("Enter name for new department");
				input = sc.nextLine();
				add(input);
				System.out.println("Department " + input + " has been created");
			}
			else if (input.equalsIgnoreCase("t"))
			{
				System.out.println("Enter the id of the employee you wish to transfer");
				int tmp = Integer.parseInt(sc.nextLine());
				System.out.println("Enter the name of the department you wish to transfer to");
				input = sc.nextLine();
				change(tmp, input);
			}
			else if (input.equalsIgnoreCase("q"))
			{
				System.out.println("Enter id of employee who is quitting");
				input = sc.nextLine();
				change(Integer.parseInt(input), "Unemployment");
				System.out.println("Employee id " + input + " has now quit");
			}
			else
			{
				System.out.println("Goodbye");
				close = true;
			}
		}
	}
	
}
