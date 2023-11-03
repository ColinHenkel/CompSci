import java.util.*;
import java.text.DecimalFormat;

/* HW4 Colin Henkel */
public class HW {
	public static void main(String[] args) {
		Employee emp1 = new Employee(111, "Jimmy Dean", 5256.32, 0), 
				 emp2 = new Employee(598, "Jen Johnson", 47370, 5),
				 emp3 = new Employee(920, "Jan Jones", 47834.25, 1);
		
		System.out.println(emp1.equals(emp3));
		
		ArrayList<Employee> list = new ArrayList<>();
		
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
		
		// sort in ascending order
		Collections.sort(list);
		
		for (Employee e : list)
			System.out.println(e);
	}
}

class Employee implements Comparable<Employee> {
	private int id, numberOfDependents;
	private String name;
	private double salary;
	private double netSalary;
	
	// overriden equals method
	@Override
	public boolean equals(Object e) {
		if(this == e) 
			return true;
		if(e == null || getClass() != e.getClass())
			return false;
		
		Employee toCompare = (Employee) e;
		return Double.compare(this.netSalary, toCompare.netSalary) == 0;
	}
	
	// overriden compareTo method
	@Override
	public int compareTo(Employee e) {
		return Double.compare(this.netSalary, e.netSalary);
	}
	
	// setters and getters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfDependents() {
		return numberOfDependents;
	}
	public void setNumberOfDependents(int numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	// constructors
	public Employee(int id, String name, double salary, int numberOfDependents) {
		this.id = id;
		this.numberOfDependents = numberOfDependents;
		this.name = name;
		this.salary = salary;
		this.netSalary = (salary * 0.91) + (numberOfDependents * 0.01 * salary);
	}
	public Employee() {
		id = 0;
		numberOfDependents = 0;
		name = " ";
		salary = 0.0;
		this.netSalary = 0.0;
	}
	
	// toString method and decimal format for salary
	DecimalFormat df = new DecimalFormat("#0.00");
	
	@Override
	public String toString() {
		return "[" + id + ", " + name + ", " + df.format(netSalary) + "]";
	}
}
