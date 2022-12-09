package queuelabexecs;

import java.util.ArrayList;
public class Department {
	private ArrayList<Person> people;
	private String name;
	
	public Department(String name) {
		people = new ArrayList<>();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * has a person join the department and sets their seniority and salary to the default
	 * if the department is unemployment the leaves their salary as 0
	 * @param p
	 */
	public void join(Person p) {
		
		if (!people.isEmpty() && !name.equalsIgnoreCase("Unemployment"))
		{
			p.setSalary(40000);
			p.setSeniority(0);
			for (int i = 0; i < people.size(); i++)
			{
				people.get(i).promote();
			}
		}
		
		people.add(p);
	}
	
	/**
	 * searches for a person based on user given id and returns them
	 * returns null if they dont exist
	 * @param id
	 * @return
	 */
	public Person getEmployee(int id) {
		for (int i = 0; i < people.size(); i++)
		{
			if (people.get(i).getId() == id)
				return people.get(i);
		}
		
		return null;
	}
	
	/**
	 * searches for a person based on user given id, has them quit setting their salary and seniority to 0,
	 * and returns them returns null if they dont exist
	 * @param id
	 * @return
	 */
	public Person quit(int id) {
		for (int i = 0; i < people.size(); i++)
		{
			if (people.get(i).getId() == id)
			{
				people.get(i).setSeniority(0);
				people.get(i).setSalary(0);
				return people.remove(i);
			}
		}
		return null;
	}
	
	/**
	 * Prints the department and all people held within in order of seniority
	 */
	public void printDepartment() {
		System.out.println("Department: " + name + " ");
		
		for (Person p: people)
			System.out.print(p + " ");
		System.out.println();
	}
}
