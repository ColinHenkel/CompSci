import java.util.*;

//_______________________________________
class ListOfEmployees {
	private ArrayList<Employee> list;

	public ListOfEmployees() {
		list = new ArrayList<>();
	}

	public void sortByName() {
		list.sort(new SortByName());
	}

	public void sortById() {
		list.sort(new SortById());
	}

	public void addEmployee(Employee e) {
		list.add(e);
	}

	public void printCheck(Employee e) {
		e.printCheck();
	}
}

//_______________________________________
public class Main {
	public static int menu() {
		int option;
		System.out.println("1 - Add a Salaried..");
		System.out.println("2 - Add an Hourly paid..");
		System.out.println("3 - Add a Manager..");
		System.out.println("4 - Sort Employees by name ..");
		System.out.println("5 - Sort Employees by id ..");
		System.out.println("6 - Print Check for an Employee .. ");
		System.out.println("0 - Exit");
// add the other options...
		System.out.print("Enter your choice:");
		option = (new Scanner(System.in)).nextInt();
		return option;
	}

	public static void main(String[] args) {
		ListOfEmployees company = new ListOfEmployees();
		int selection = menu();
		Employee e;
		String name;
		String id;
		double salary;
		Scanner myScan = new Scanner(System.in);
		while (true) {
			switch (selection) {
			case 0:
				System.out.println("Take Care!");
				break;
			case 1:
				System.out.print("Enter Name");
				name = myScan.nextLine();
				System.out.println("Enter Id: ");
				id = myScan.nextLine();
				System.out.print("Enter the Salary:");
				salary = myScan.nextDouble();
				company.addEmployee(new Salaried(name, id, salary));
				break;
//Add the other options...
			}// end of switch
			if (selection == 0)
				break;
			selection = menu();
		}
	}
}

//_________________________________
class SortByName implements Comparator<Employee> {
	public int compare(Employee e, Employee f) {
		return (e.getName()).compareTo(f.getName());
	}
}

class SortById implements Comparator<Employee> {
	@Override
	public int compare(Employee e, Employee f) {
		return (e.getId()).compareTo(f.getId());
	}
}

//_________________________________
class HourlyPaid extends Employee {
	private double hoursWorked;
	private double wage;

	@Override
	public void printDuties() {
		System.out.println("Doing Hourly Paid Stuff...");
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public HourlyPaid(String id, String name, double hoursWorked, double wage) {
		super(id, name);
		this.hoursWorked = hoursWorked;
		this.wage = wage;
	}

	public HourlyPaid() {
		super("ID NOT SET YET", "NAME NOT SET YET");
		this.wage = 0.0;
		this.hoursWorked = 0.0;
	}

	public String toString() {
		return super.toString() + "[ Wage: " + wage + " Hours Worked: " + hoursWorked + " ]";
	}

	@Override
	public void printCheck() {
		super.printCheck();
		System.out.println("Total Payment: " + hoursWorked * wage);
	}
}

//_________________________________
//_________________________________
final class Manager extends Salaried { // no class can extend Manager
	private double bonus;

	@Override
	public void printDuties() {
		System.out.println("Doing Manager Stuff...");
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	@Override
	public String toString() {
		return getId() + " " + getName() + " Bonus: " + bonus;
	}

	public Manager() {
		bonus = 0;
	}

	public Manager(String id, String name, double salary, double bonus) {
		super(id, name, salary);
		this.bonus = bonus;
	}

	@Override
	public void printCheck() {
		super.printCheck();
		System.out.println("___________________________");
		System.out.println("Bonus: " + bonus);
	}
}

//_________________________
class Salaried extends Employee {
	private double salary;

	@Override
	public void printDuties() {
		System.out.println("Doing Salaried stuff....");
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Salaried(String id, String name, double salary) {
		super(id, name);
		this.salary = salary;
	}

	public Salaried() {
		super();
		salary = -1;
	}

	public String toString() {
		return super.toString() + "[Salary: " + salary + "]";
	}

	public void printCheck() {
		super.printCheck();
		System.out.println("Salary :" + salary);
	}
}

//________________________________
interface ABC {
	abstract void method1();

	abstract int method2(String[] args);

	abstract void method3(int x);
}

abstract class Test extends Employee {
}

class Test2 extends Test {
	public void printDuties() {
	}
}

abstract class Employee implements Comparable<Employee> {
	private String id;
	private String name;

	@Override
	public int compareTo(Employee e) {
		return this.id.compareTo(e.id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee() {
		name = id = "EMPTY";
	}

	public String toString() {
		return "[ Name: " + name + ", ID: " + id + " ]";
	}

	public void printCheck() {
		System.out.println("\t\tName: " + name);
		System.out.println("\t\tID: " + id);
	}

	abstract public void printDuties();
}